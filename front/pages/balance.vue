<template>
  <div class="account-balance-container">
    <!-- 원화(KRW) 및 미국 달러(USD) 정보 섹션 -->
    <div class="currency-section">
      <h2>계좌 잔액</h2>
      <div v-if="accountData" class="account-details">
        <div class="balance-info">
          <span>원화 예수금: {{ formatCurrency(accountData.krw_Balance, 'KRW') }}</span>
          <span>미국 달러 예수금: {{ formatCurrency(accountData.usd_Balance, 'USD') }}</span>
        </div>
      </div>
      <h2>총 수익율</h2>
      <div v-if="accountData" class="account-details">
        <div class="balance-info">
          <span>수익률: {{ returnRate }}</span>
        </div>
      </div>
    </div>

    <!-- 주식 정보 섹션 -->
    <div class="currency-section stock-section">
      <h2>보유 종목</h2>
      <div v-if="stockData" class="stock-list">
        <div class="stock-header">
          <span>종목명</span>
          <span>평가손익</span>
          <span>수량</span>
          <span>평가금액</span>
          <span>현재가격</span>
        </div>
        <div class="stock-items">
          <div class="stock-item" v-for="stock in stockData" :key="stock.id">
            <span>{{ stock.name }}</span>
            <span>{{ stock.profit_loss | numeralFormat }}</span>
            <span>{{ stock.quantity }}</span>
            <span>{{ stock.current_value | numeralFormat }}</span>
            <span>{{ stock.current_price | numeralFormat }}</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import axios from 'axios';

export default {
  data() {
    return {
      accountData: null,
      stockData: null,
      returnRate: null,
    };
  },
  mounted() {
    this.fetchData();
  },
  methods: {
    async fetchData() {
      try {
        const response = await axios.post('https://dbspring.dongwoo.win/api/balance', {
          account_id: this.$store.state.account_id,
        }, {
          headers: {
            'Content-Type': 'application/json'
          }
        });

        const data = response.data;

        this.accountData = {
          krw_Balance: data.krw_Balance,
          usd_Balance: data.usd_Balance
        };

        this.stockData = data.itemPortfolios.map(item => ({
          id: 'stock' + item.item_id,
          name: item.name,
          profit_loss: item.profit_loss,
          quantity: item.quantity,
          current_value: item.current_price * item.quantity,
          current_price: item.current_price // 추가된 현재가격 필드
        }));
        this.returnRate = data.return_rate;
      } catch (error) {
        console.error(error);
      }
    },
    formatCurrency(value, currencyType) {
      let formattedValue;
      if (currencyType === 'USD') {
        formattedValue = new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD' }).format(value);
      } else {
        formattedValue = new Intl.NumberFormat('ko-KR', { style: 'currency', currency: 'KRW' }).format(value);
      }
      return formattedValue;
    }
  },
  filters: {
    numeralFormat(value) {
      return new Intl.NumberFormat().format(value);
    }
  }
};
</script>
<style>
body {
  background-color: #000000;
  margin: 0;
  padding: 0;
  min-height: 100vh;
}

.account-balance-container {
  color: #fff;
  padding: 20px;
}

.currency-section {
  background-color: #333;
  border-radius: 10px;
  padding: 15px;
  margin-bottom: 20px;
}

.account-details .balance-info {
  margin-bottom: 10px;
}

.stock-list .stock-header, .stock-list .stock-item {
  display: grid;
  grid-template-columns: repeat(5, 1fr); /* 4개의 열을 동일한 크기로 설정 */
  gap: 10px;
}

.stock-list .stock-header {
  margin-bottom: 10px;
  font-weight: bold;
}

.stock-list .stock-item {
  align-items: center;
  padding: 5px;
  background-color: #444;
  border-radius: 5px;
}
</style>