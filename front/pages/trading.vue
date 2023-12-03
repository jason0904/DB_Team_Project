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
        </div>

        <!-- 지정가/시장가 선택 -->
        <div class="price-type">
          <button :class="{ active: priceType === 'limit' }" @click="togglePriceType('limit')">지정가</button>
          <button :class="{ active: priceType === 'market' }" @click="togglePriceType('market')">시장가</button>
        </div>

        <!-- 지정가 입력 -->
        <div v-if="priceType === 'limit'">
          <input type="number" v-model="limitPrice" placeholder="가격을 입력하세요">
        </div>

        <!-- 주문 물량 입력 -->
        <input type="number" v-model="orderQuantity" placeholder="물량을 입력하세요">

        <!-- 구매 가능 주수 및 총 주문 금액 표시 -->
        <div>
          <div>구매 가능 주수: {{ availableShares }}주</div>
          <div v-if="priceType === 'limit'">총 주문 금액: {{ roundedTotalPrice }}원</div>
          <div v-else>최대 주문 금액: {{ roundedTotalPrice }}원</div>
        </div>

        <!-- 구매하기 버튼 -->
        <button @click="placeOrder">구매하기</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      fullOrderBook: [
        { price: 125000, quantity: 50 }, // 상한가
        { price: 124500, quantity: 100 },
        { price: 124000, quantity: 200 },
        { price: 123500, quantity: 300 },
        { price: 123000, quantity: 400 },
        { price: 122500, quantity: 500 },
        { price: 122000, quantity: 600 },
        { price: 121500, quantity: 700 },
        { price: 121000, quantity: 800 },
        { price: 120500, quantity: 900 },
        { price: 120000, quantity: 1000 },
        { price: 119500, quantity: 1100 },
        { price: 119000, quantity: 1200 },
        { price: 118500, quantity: 1300 },
        { price: 118000, quantity: 1400 },
        { price: 117500, quantity: 1500 },
        { price: 117000, quantity: 1600 },
        { price: 116500, quantity: 1700 },
        { price: 116000, quantity: 1800 },
        { price: 115500, quantity: 1900 },
        { price: 115000, quantity: 2000 },
        { price: 114500, quantity: 2100 },
        { price: 114000, quantity: 2200 },
        { price: 113500, quantity: 2300 },
        { price: 113000, quantity: 2400 },
        { price: 112500, quantity: 2500 },
        { price: 112000, quantity: 2600 },
        { price: 111500, quantity: 2700 },
        { price: 111000, quantity: 2800 },
        { price: 110500, quantity: 2900 },
        { price: 110000, quantity: 3000 },
        { price: 109500, quantity: 3100 },
        { price: 109000, quantity: 3200 },
        { price: 108500, quantity: 3300 },
        { price: 108000, quantity: 3400 },
        { price: 107500, quantity: 3500 },
        { price: 107000, quantity: 3600 },
        { price: 106500, quantity: 3700 },
        // ... 중간 호가들 ...
        { price: 75000, quantity: 400 }  // 하한가
      ],
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
    };
  },
  created() {
    // URL 쿼리 파라미터에서 itemId 값을 추출하여 itemID에 할당
    this.itemID = this.$route.query.itemId || this.itemID;
    // API 호출 로직 - 종목명, 현재가
  },
  computed: {
    roundedTotalPrice() {
      return Math.ceil(this.totalPrice / 100) * 100; // 총 주문 금액을 100원 단위로 올림
    }
  },
  methods: {
    fetchItemInfo() {
      // 종목 정보 가져오기 로직 (여기에 백엔드 통신 코드 작성)
    },
    fetchOrderBook() {
      // 호가 정보 가져오기 로직 (여기에 백엔드 통신 코드 작성)
    },
    calculateTotalPrice() {
      if (this.priceType === 'limit') {
        this.totalPrice = this.limitPrice * this.orderQuantity;
      } else {
        // 시장가 로직 (여기에 구현)
      }
    },
    placeOrder() {
      // 주문 로직 (여기에 백엔드 통신 코드 작성)
    },
    toggleTradeType(type) {
      this.tradeType = type;
    },
    togglePriceType(type) {
      this.priceType = type;
    },
    updateDesiredPrice(price) {
      // 지정가가 선택되어 있을 경우, 지정가 입력란에 가격 설정
      if (this.priceType === 'limit') {
        this.limitPrice = price;
      }
      this.desiredPrice = price;
    },
    goToStockDetail() {
      this.$router.push({ name: 'stock_detail' });
      console.log('Redirecting to stock_detail page');
    },
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
    this.fetchOrderBook();
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

.opacity_itemname:hover {
  opacity: 0.8;
}
</style>