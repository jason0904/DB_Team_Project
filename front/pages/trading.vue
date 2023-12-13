<template>
  <div id="app">
    <div class="content-wrapper">
      <!-- 종목명, 현재가 -->
      <div class="stock-info">
        <h2 class="opacity-itemname" @click="goToStockDetail">
          {{itemName}}({{ itemID }})
        </h2>
        <p>현재가격: {{ currentPrice }}원</p>
      </div>
      <!-- 호가창 -->
      <div class="order-book">
        <div class="price-level" v-for="level in fullOrderBook" :key="level.price" :class="{'upper-limit': level.price === upperLimit, 'lower-limit': level.price === lowerLimit}">
          <div class="price-quantity">
            <div
                class="price"
                :style="{ color: level.price > currentPrice ? 'red' : (level.price < currentPrice ? 'blue' : 'white') }"
                @click="updateDesiredPrice(level.price)"
            >
              {{ level.price }}
            </div>
            <div class="quantity">{{ level.quantity }}</div>
          </div>
        </div>
      </div>

      <!-- 주문창 -->
      <div class="trade-execution">
        <!-- 매수 매도 선택 -->
        <div class="trade-type">
          <button :class="{ active: tradeType === 'buy' }" @click="toggleTradeType('buy')">매수</button>
          <button :class="{ active: tradeType === 'sell' }" @click="toggleTradeType('sell')">매도</button>
          <button :class="{ active: tradeType === 'modify' }" @click="toggleTradeType('modify')">정정</button>
        </div>

        <!-- 지정가/시장가 선택 -->
        <div class="price-type" v-if="tradeType !== 'modify'">
          <button :class="{ active: priceType === 'limit' }" @click="togglePriceType('limit')">지정가</button>
          <button :class="{ active: priceType === 'market' }" @click="togglePriceType('market')">시장가</button>
        </div>

        <!-- 원 주문 번호 표시 -->
        <div v-if="tradeType === 'modify'">
          <div>원 주문 번호: {{ originalOrderNumber }}</div>
        </div>

        <!-- 지정가 입력 -->
        <div v-if="priceType === 'limit' && tradeType !== 'modify'">
          <input type="number" v-model="limitPrice" placeholder="가격을 입력하세요">
        </div>

        <!-- 주문 물량 입력 -->
        <div>
          <input type="number" v-model="orderQuantity" placeholder="물량을 입력하세요">
        </div>

        <!-- 구매 가능 주수 및 총 주문 금액 표시 -->
        <div >
          <div v-if="tradeType !== 'modify'">구매 가능 주수: {{ availableShares }}주</div>
          <div v-if="priceType === 'limit' && tradeType !== 'modify'">총 주문 금액: {{ roundedTotalPrice }}원</div>
          <div v-if="priceType === 'market' && tradeType !== 'modify'">최대 주문 금액: {{ roundedTotalPrice }}원</div>
        </div>

        <!-- 정정 주문 선택 -->
        <div v-if="tradeType === 'modify'" class="modify-list">
          <div class="modify-header">
            <span>종목명</span>
            <span>매매구분</span>
            <span>주문단가</span>
            <span>미채결량</span>
            <span>주문시간</span>
            <span>원주문번호</span>
          </div>
          <div class="modify-items">
            <div class="modify-item" v-for="stock in modifyData" :key="stock.id" @click="modifyClick(stock)">
              <span>{{ stock.name }}</span>
              <span>{{ stock.orderType }}</span>
              <span>{{ stock.orderPrice }}</span>
              <span>{{ stock.orderQuantity }}</span>
              <span>{{ stock.time }}</span>
              <span>{{ stock.orderNumber }}</span>
            </div>
          </div>
        </div>

        <!-- 주문 버튼 -->
        <div>
          <button v-if="tradeType !== 'modify'" @click="placeOrder">구매하기</button>
          <button v-if="tradeType === 'modify'" @click="placeModify('modify')">정정하기</button>
          <button v-if="tradeType === 'modify'" @click="placeModify('cancel')">취소하기</button>
        </div>

        <!-- 주문 상태 메시지 -->
        <div v-if="orderStatus === 'error'" :class="`order-status ${orderStatus}`">
          {{ orderStatusMessage }}
        </div>
      </div>
    </div>
  </div>
</template>


<script>
export default {
  data() {
    return {
      fullOrderBook: [],
      modifyData: [],
      upperLimit: 125000, // 상한가
      lowerLimit: 75000,  // 하한가
      tradeType: 'buy', // 매수 or 매도
      priceType: 'limit', // 지정가 or 시장가
      limitPrice: null, // 지정가
      orderQuantity: 0, // 주문 수량
      availableShares: 0, // 사용자가 구매할 수 있는 주식 수
      totalPrice: 0, // 원래 계산된 총 주문 금액
      currentPrice: 114000, // 현재 가격
      desiredPrice: null, // 희망 가격
      itemName: "null", // 종목명
      itemID: 0, // 종목 ID
      originalOrderNumber: '', // 원 주문 번호
      orderStatus: false, // 주문 상태 (성공, 실패)
      orderStatusMessage: '', // 주문 상태 메시지
    };
  },
  created() {
    // URL 쿼리 파라미터에서 itemId 값을 추출하여 itemID에 할당
    this.itemID = this.$route.query.itemId || this.itemID;
    this.itemName = this.$route.query.itemName || this.itemName;
    this.originalOrderNumber = '';
    // API 호출 로직 - 종목명, 현재가
    this.fetchItemInfo();
  },
  computed: {
    roundedTotalPrice() {
      if (this.priceType === 'limit') {
        return this.totalPrice;
      }
      else if(this.priceType === 'market') {
        return this.orderQuantity * this.upperLimit;
      }
    }
  },
  methods: {
    async fetchItemInfo() {
      const axiosModule = await import('axios');
      const axios = axiosModule.default;

      try {
        const response = await axios.post('https://dbspring.dongwoo.win/api/order', {
          account_id: this.$store.state.account_id,
          item_id: this.itemID,
        }, {
          headers: {
            'Content-Type': 'application/json'
          }
        });

        const accountData = response.data;
        console.log(accountData);
      } catch (error) {
        console.error(error);
      }
    },
    calculateTotalPrice() {
      if (this.priceType === 'limit') {
        this.totalPrice = this.limitPrice * this.orderQuantity;
      } else {
        // 시장가 로직 (여기에 구현)
      }
    },
    async placeOrder() {
      const axiosModule = await import('axios');
      const axios = axiosModule.default;

      try {
        const response = await axios.post('https://dbspring.dongwoo.win/api/order/order', {
          account_id: this.$store.state.account_id,
          item_id: this.itemID,
          purchase_type: this.tradeType+'order',
          quantity: this.orderQuantity,
          order_type: "limit",
          limit_price: this.limitPrice,
        }, {
          headers: {
            'Content-Type': 'application/json'
          }
        });

        this.orderStatus = 'success';
        this.orderStatusMessage = '주문이 성공적으로 처리되었습니다.';

      } catch (error) {
        console.error(error);
        this.orderStatus = 'error';
        this.orderStatusMessage = '주문 처리 중 오류가 발생했습니다.';
      }
    },
    async placeModify(type) {
      const axiosModule = await import('axios');
      const axios = axiosModule.default;
      const url = type === 'modify' ? 'https://dbspring.dongwoo.win/api/order/amend' : 'https://dbspring.dongwoo.win/api/order/cancel';
      let payload = {};

      if (type === 'modify') {
        payload = {
          order_id: this.originalOrderNumber,
          quantity: this.orderQuantity
        };
      }else {
        payload = {
          order_id: this.originalOrderNumber,
        };
      }

      try {
        const response = await axios.post(url, payload, {
          headers: {
            'Content-Type': 'application/json'
          }
        });
        console.log(response.data);
        this.orderStatusMessage = type === 'modify' ? '정정 주문이 성공적으로 처리되었습니다.' : '취소 주문이 성공적으로 처리되었습니다.';
        await this.fetchModifyData();
      } catch (error) {
        console.error(error);
        this.orderStatus = 'error';
        this.orderStatusMessage = type === 'modify' ? '정정 주문 처리 중 오류가 발생했습니다.' : '취소 주문 처리 중 오류가 발생했습니다.';
      }
    },
    toggleTradeType(type) {
      this.tradeType = type;
      if(type === 'modify') {
        this.fetchModifyData();
      }
    },
    togglePriceType(type) {
      this.priceType = type;
    },
    updateDesiredPrice(price) {
      if (this.priceType === 'limit') {
        this.limitPrice = price;
      }
      this.desiredPrice = price;
    },
    goToStockDetail() {
      this.$router.push({ name: 'stock_detail',  query: { itemId: this.itemID, itemName: this.itemName }});
      console.log('Redirecting to stock_detail page');
    },
    modifyClick(stock) {
      this.limitPrice = stock.orderPrice;
      this.orderQuantity = stock.orderQuantity;
      this.originalOrderNumber = stock.orderNumber;
    },
    async fetchModifyData() {
      this.modifyData = [];
      const axiosModule = await import('axios');
      const axios = axiosModule.default;

      try {
        const response = await axios.post('https://dbspring.dongwoo.win/api/order/pendings', {
          account_id: this.$store.state.account_id,
        }, {
          headers: {
            'Content-Type': 'application/json'
          }
        });

        const accountData = response.data;
        console.log(accountData);
        for (let i = 0; i < accountData.length; i++) {
          this.modifyData.push({
            name: this.itemName,
            orderType: accountData[i].purchase_type === 'buyorder' ? '매수' : '매도',
            orderPrice: accountData[i].limit_price,
            orderQuantity: accountData[i].quantity,
            time: accountData[i].created_at.slice(0, 10),
            orderNumber: accountData[i].order_id,
          });
        }
      } catch (error) {
        console.error(error);
      }
    }
  },
  watch: {
    limitPrice() {
      this.calculateTotalPrice();
    },
    orderQuantity() {
      this.calculateTotalPrice();
    }
  },
  mounted() {
    this.fetchItemInfo();
  }
};
</script>


<style>
body {
  background-color: #000000; /* Black color */
  margin: 0; /* Removes default margin */
  padding: 0; /* Removes default padding */
  min-height: 100vh; /* 100% of the viewport height */
}


/* 전체 앱의 기본 스타일 */
#app {
  background-color: #000000;
  color: #ffffff;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  padding: 20px;
}

/* 콘텐츠 래퍼 스타일 - 여기에 마진 추가 */
.content-wrapper {
  display: flex;
  margin: 0 auto; /* 전체 내용 주위에 마진 추가 */
  padding: 20px; /* 래퍼 내부에 패딩 추가 */
  max-width: 1200px; /* 최대 너비 설정 (필요에 따라 조절) */
}

/* 호가창 스타일 - 여기에 오른쪽 마진 추가 */
.order-book {
  flex: 3;
  margin-right: 20px; /* 호가창과 주문창 사이 공간 추가 */
  max-height: 400px;
  overflow-y: scroll;
  background-color: #121212;
  border: 1px solid #333333;
  border-radius: 10px;
  /* 기존 스타일을 유지하면서 추가 */
}

/* 호가 레벨 스타일 */
.price-level {
  background-color: #252525;
  border: 1px solid #333333;
  padding: 10px;
  margin-bottom: 5px;
  border-radius: 5px;
}

.price-quantity {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.price {
  font-weight: bold;
  cursor: pointer;
}

.quantity {
  color: #ffffff;
}

.upper-limit {
  background-color: #ff4444;
}

.lower-limit {
  background-color: #4444ff;
}

/* 주문창 스타일 - 여기에 왼쪽 마진 추가 */
.trade-execution {
  flex: 5;
  margin-left: 20px; /* 주문창과 호가창 사이 공간 추가 */
  border: 1px solid #333333;
  background-color: #1a1a1a;
  padding: 20px;
  border-radius: 10px;
  /* 기존 스타일을 유지하면서 추가 */
}

/* 버튼 스타일 */
.trade-type button, .price-type button, button {
  border: 1px solid #555555;
  background-color: #222222;
  color: #ddd;
  padding: 10px;
  margin: 10px 0;
  border-radius: 5px;
  cursor: pointer;
}

.trade-type button.active, .price-type button.active {
  background-color: #4CAF50; /* 클릭 시 색상 변경 */
}

/* 구매하기 버튼 스타일 */
button {
  background-color: #4CAF50;
  color: white;
  cursor: pointer;
  transition: background-color 0.3s ease;
  border-radius: 10px; /* 모서리 둥글게 */
}

/* 입력 필드 스타일 */
input[type='number'] {
  width: 100%;
  border: 1px solid #555555;
  background-color: #222222;
  color: #ddd;
  padding: 10px;
  margin: 10px 0;
  border-radius: 5px;
}

/* 종목 정보 스타일 */
.stock-info {
  color: #ffffff;
  text-align: center;
  margin-bottom: 20px;
}

.stock-info h2 {
  margin: 0;
  padding: 0;
  font-size: 24px;
}

.stock-info p {
  margin: 0;
  font-size: 18px;
}

/* 반응형 디자인 조정 */
@media (max-width: 600px) {
  .content-wrapper {
    flex-direction: column;
  }
  .order-book, .trade-execution {
    margin: 0 auto;
  }
}

.modify-header, .modify-item {
  display: grid;
  grid-template-columns: repeat(6, 1fr); /* 6개의 컬럼으로 나눕니다 */
  gap: 10px;
}

.modify-header > div, .modify-item > div {
  text-align: center; /* 내용을 가운데 정렬 */
}

.opacity_itemname:hover {
  opacity: 0.8;
}
</style>