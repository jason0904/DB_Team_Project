<template>
  <div class="account-balance-container">
    <!-- 한국 원화(KRW) 정보 섹션 -->
    <div class="currency-section krw-section">
      <h2>KRW</h2>
      <div v-if="accountData" class="account-details">
        <div class="profit-loss" :class="{'positive': accountData.profitLoss > 0, 'negative': accountData.profitLoss < 0}">
          평가손익: {{ formatCurrency(accountData.profitLoss, 'KRW') }}
        </div>
        <div class="balance-info">
          <span>평가금액: {{ formatCurrency(accountData.totalValue, 'KRW') }}</span>
          <span>예수금: {{ formatCurrency(accountData.deposit, 'KRW') }}</span>
          <span>매입금액: {{ formatCurrency(accountData.totalCost, 'KRW') }}</span>
        </div>
      </div>
    </div>

    <!-- 미국 달러(USD) 정보 섹션 -->
    <div class="currency-section usd-section">
      <h2>USD</h2>
      <div v-if="accountData" class="account-details">
        <div class="profit-loss" :class="{'positive': accountData.profitLoss > 0, 'negative': accountData.profitLoss < 0}">
          평가손익: {{ formatCurrency(accountData.profitLoss, 'USD') }}
        </div>
        <div class="balance-info">
          <span>평가금액: {{ formatCurrency(accountData.totalValue, 'USD') }}</span>
          <span>예수금: {{ formatCurrency(accountData.deposit, 'USD') }}</span>
          <span>매입금액: {{ formatCurrency(accountData.totalCost, 'USD') }}</span>
        </div>
      </div>
    </div>

    <!-- 주식 정보 섹션 -->
    <div class="currency-section stock-section">
      <h2>보유 종</h2>
      <div v-if="stockData" class="stock-list">
        <div class="stock-header">
          <span>종목명</span>
          <span>평가손익</span>
          <span>수익률</span>
          <span>보유수량</span>
          <span>평가금액</span>
          <span>비중</span>
          <span>매입단가</span>
          <span>매수금액</span>
          <span>현재가</span>
        </div>
        <div class="stock-items">
          <div class="stock-item" v-for="stock in stockData" :key="stock.id">
            <span>{{ stock.name }}</span>
            <span>{{ formatCurrency(stock.profitLoss, 'KRW') }}</span>
            <span>{{ stock.returnRate }}%</span>
            <span>{{ stock.quantity }}</span>
            <span>{{ formatCurrency(stock.currentValue, 'KRW') }}</span>
            <span>{{ stock.weight }}%</span>
            <span>{{ formatCurrency(stock.purchasePrice, 'KRW') }}</span>
            <span>{{ formatCurrency(stock.purchaseAmount, 'KRW') }}</span>
            <span>{{ formatCurrency(stock.currentPrice, 'KRW') }}</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      conversionRate: 0.00083, // 예시 환율, 1 KRW = 0.00083 USD
      accountData: null,
      stockData: null,
    };
  },
  mounted() {
    this.fetchData();
  },
  methods: {
    fetchAccountData() {
      // 계좌 정보를 위한 더미 데이터
      this.accountData = {
        profitLoss: 120000,
        totalValue: 2400000,
        deposit: 500000,
        totalCost: 1900000
      };
    },
    fetchStockData() {
      // 주식 정보를 위한 더미 데이터
      this.stockData = [
        {
          id: 'stock1',
          name: '삼성전자',
          profitLoss: 50000,
          returnRate: 2.5,
          quantity: 100,
          currentValue: 1500000,
          weight: 60,
          purchasePrice: 14000,
          purchaseAmount: 1400000,
          currentPrice: 15000,
        },
        {
          id: 'stock2',
          name: '현대자동차',
          profitLoss: 30000,
          returnRate: 1.2,
          quantity: 50,
          currentValue: 600000,
          weight: 25,
          purchasePrice: 11500,
          purchaseAmount: 575000,
          currentPrice: 12000,
        },
        {
          id: 'stock3',
          name: '제이슨전자',
          profitLoss: -10000,
          returnRate: -0.5,
          quantity: 10,
          currentValue: 100000,
          weight: 5,
          purchasePrice: 110000,
          purchaseAmount: 1100000,
          currentPrice: 10000,
        },
        {
          id: 'stock4',
          name: '제이슨정전자',
          profitLoss: 20000,
          returnRate: 1.0,
          quantity: 20,
          currentValue: 400000,
          weight: 10,
          purchasePrice: 20000,
          purchaseAmount: 400000,
          currentPrice: 20000,
        },
      ];
    },
    async fetchData() {

    },
    formatCurrency(value, currencyType) {
      let formattedValue;
      if (currencyType === 'USD') {
        const usdValue = value * this.conversionRate;
        formattedValue = new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD' }).format(usdValue);
      } else {
        formattedValue = new Intl.NumberFormat('ko-KR', { style: 'currency', currency: 'KRW' }).format(value);
      }
      return formattedValue;
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

.account-details .profit-loss {
  font-weight: bold;
  margin-bottom: 10px;
}

.account-details .positive { color: red; }
.account-details .negative { color: blue; }

.balance-info {
  margin-bottom: 10px;
}

.stock-list .stock-header, .stock-list .stock-item {
  display: grid;
  grid-template-columns: repeat(9, 1fr); /* 10개의 열을 동일한 크기로 설정 */
  gap: 10px; /* 각 항목 사이의 간격 */
}

.stock-list .stock-header {
  margin-bottom: 10px; /* 헤더와 아이템 사이의 간격 */
  font-weight: bold; /* 헤더 텍스트 굵게 */
}

.stock-list .stock-item {
  align-items: center; /* 항목들을 가운데 정렬 */
  padding: 5px; /* 패딩 설정 */
  background-color: #444; /* 배경색 설정 */
  border-radius: 5px; /* 둥근 모서리 */
}
</style>

