<template>
  <div class="account-balance-container">
    <div class="account-selector">
      <select v-model="selectedAccount" class="dropdown">
        <option disabled value="">계좌를 선택해주세요</option>
        <option v-for="account in accounts" :key="account.id" :value="account.id">{{ account.name }}</option>
      </select>
      <button @click="fetchAccountData">조회</button>
    </div>

    <div v-if="accountData" class="account-details">
      <div class="profit-loss" :class="{'positive': accountData.profitLoss > 0, 'negative': accountData.profitLoss < 0}">
        평가손익: {{ accountData.profitLoss | currency }} ({{ accountData.profitLossPercent }}%)
      </div>
      <div class="balance-info">
        <span>평가금액: {{ accountData.totalValue | currency }}</span>
        <span>예수금: {{ accountData.deposit | currency }}</span>
      </div>
      <div class="balance-info">
        <span>매입금액: {{ accountData.totalCost | currency }}</span>
        <span>D+2: {{ accountData.d2Amount | currency }}</span>
      </div>
    </div>

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
          <span>{{ stock.profitLoss | currency }}</span>
          <span>{{ stock.returnRate }}%</span>
          <span>{{ stock.quantity }}</span>
          <span>{{ stock.currentValue | currency }}</span>
          <span>{{ stock.weight }}%</span>
          <span>{{ stock.purchasePrice | currency }}</span>
          <span>{{ stock.purchaseAmount | currency }}</span>
          <span>{{ stock.currentPrice | currency }}</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      selectedAccount: '',
      accounts: [
        { id: 'acc1', name: '계좌 1' },
        { id: 'acc2', name: '계좌 2' },
        // 더 많은 계좌들...
      ],
      accountData: null,
      stockData: null,
    };
  },
  methods: {
    fetchAccountData() {
      // 더미 데이터 설정
      this.accountData = {
        profitLoss: 50000,
        profitLossPercent: 5.0,
        totalValue: 1050000,
        totalCost: 1000000,
        deposit: 500000,
        d2Amount: 200000,
      };
      this.stockData = [
        { id: 'stock1', name: '주식 A', profitLoss: 30000, returnRate: 3, quantity: 5, currentValue: 600000, weight: 60, purchasePrice: 120000, purchaseAmount: 600000, currentPrice: 125000, previousPrice: 120000 },
        // 더 많은 주식들...
      ];
    }
  },
  filters: {
    currency(value) {
      return new Intl.NumberFormat('ko-KR', { style: 'currency', currency: 'KRW' }).format(value);
    }
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

.account-balance-container {
  background-color: #000;
  color: #fff;
  padding: 20px;
}

.account-selector .dropdown {
  color: black;
  background-color: white;
  border: 1px solid #ddd;
  padding: 5px 10px;
  margin-right: 10px;
}

.account-selector button {
  padding: 5px 10px;
  border: 1px solid #ddd;
  background-color: #fff;
  cursor: pointer;
}

.account-selector button:hover {
  background-color: #eee;
}

.account-details .profit-loss {
  font-weight: bold;
  margin-bottom: 10px;
}

.account-details .positive {
  color: red;
}

.account-details .negative {
  color: blue;
}

.balance-info {
  margin-bottom: 10px;
}

.stock-header, .stock-item {
  display: grid;
  grid-template-columns: repeat(9, 1fr); /* 6개의 컬럼으로 나눕니다 */
  gap: 10px;
}

.stock-header > span, .stock-item > span {
  text-align: center; /* 내용을 가운데 정렬 */
}

button {
  background-color: white;
  color: black;
  border: 1px solid grey;
  padding: 10px 15px;
  border-radius: 5px;
  cursor: pointer;
}
</style>
