<template>
  <div class="exchange-rate-container">
    <div class="controls">
      <div class="date-selector">
        <label for="start-date">Date:</label>
        <input type="date" id="start-date" v-model="startDate" min="2010-01-01">
      </div>

      <div class="currency-selector">
        <label for="from-currency">From:</label>
        <select id="from-currency" v-model="fromCurrency">
          <option value="KRW">KRW</option>
          <option value="USD">USD</option>
        </select>

        <label for="to-currency">To:</label>
        <select id="to-currency" v-model="toCurrency">
          <option value="KRW">KRW</option>
          <option value="USD">USD</option>
        </select>
      </div>

      <button @click="fetchRates">조회</button>
      <h2>환율: {{ rate }}</h2>
    </div>

    <div class="balance-section">
      <h2>잔고 확인</h2>
      <div class="balance">
        <p>현재 환율(KRW->USD): {{ currentRate }}</p>
        <p>KRW 잔고: {{ krwBalance }}원</p>
        <p>USD 잔고: {{ usdBalance }}$</p>
      </div>
    </div>

    <div class="exchange-section">
      <h2>환전 신청</h2>
      <div class="exchange-controls">
        <div class="amount-selector">
          <label for="exchange-amount">금액:</label>
          <input type="number" id="exchange-amount" v-model="exchangeAmount">
        </div>

        <div class="currency-selector">
          <label for="base-currency">기준 통화:</label>
          <select id="base-currency" v-model="baseCurrency">
            <option value="KRW">KRW</option>
            <option value="USD">USD</option>
          </select>

          <label for="target-currency">대상 통화:</label>
          <select id="target-currency" v-model="targetCurrency">
            <option value="KRW">KRW</option>
            <option value="USD">USD</option>
          </select>
        </div>

        <button @click="exchangeCurrency">환전 신청</button>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      startDate: '',
      fromCurrency: 'KRW',
      toCurrency: 'USD',
      rate: '조회 후 표시됩니다',
      useToKrwRate: '조회 후 표시됩니다',
      currentRate: 0,       // 현재 환율
      krwBalance: 1000000,  // KRW 잔고 (임시 값)
      usdBalance: 1000,     // USD 잔고 (임시 값)
      exchangeAmount: 0,    // 환전 금액
      baseCurrency: 'KRW',  // 기준 통화
      targetCurrency: 'USD' // 대상 통화
    };
  },
  mounted() {
    this.fetchRates();
    this.fetchBalance();
  },
  methods: {
    async fetchRates() {
      this.rate = "조회불가";
      this.useToKrwRate = "조회불가";

      const axiosModule = await import('axios');
      const axios = axiosModule.default;

      try {
        const response = await axios.post('https://dbspring.dongwoo.win/api/exchange/rate', {
          base_currency: this.fromCurrency,
          foreign_currency: this.toCurrency,
          created_at: this.startDate,
        });

        const accountData = response.data;
        console.log(accountData);

        this.rate = accountData;

        if (this.fromCurrency === 'KRW' && this.toCurrency === 'USD') {
          this.useToKrwRate = accountData;
        }

      } catch (error) {
        console.error(error);
      }

    },
    async fetchBalance(){
      const axiosModule = await import('axios');
      const axios = axiosModule.default;

      try {
        const response = await axios.post('https://dbspring.dongwoo.win/api/exchange', {
          account_id: this.$store.state.account_id,
        }, {
          headers: {
            'Content-Type': 'application/json'
          }
        });

        // 응답 처리
        const accountData = response.data;
        console.log(accountData);

        this.currentRate = accountData.current_exchange_rate;
        this.krwBalance = accountData.krw_Balance;
        this.usdBalance = accountData.usd_Balance;

      } catch (error) {
        console.error(error);
      }

    },
    async exchangeCurrency() {
      const axiosModule = await import('axios');
      const axios = axiosModule.default;

      try {
        const response = await axios.post('https://dbspring.dongwoo.win/api/exchange/exchange', {
          account_id: this.$store.state.account_id,
          base_amount: this.exchangeAmount,
          base_currency: this.baseCurrency,
          foreign_currency: this.targetCurrency,
        }, {
          headers: {
            'Content-Type': 'application/json'
          }
        });

        // 응답 처리
        const accountData = response.data;
        console.log(accountData);

        await this.fetchBalance();
        alert("환전 신청이 완료되었습니다!");

      } catch (error) {
        console.error(error);
        alert("환전 신청이 실패하었습니다!");
      }

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

.exchange-rate-container {
  background-color: black;
  color: white;
  font-family: 'Arial', sans-serif;
  padding: 20px;
  border-radius: 10px;
}

.controls, .balance-section, .exchange-section {
  background-color: #333;
  border-radius: 10px;
  padding: 15px;
  margin-bottom: 20px;
}

.date-selector, .currency-selector, .amount-selector {
  margin-bottom: 20px;
}

.date-selector label, .currency-selector label, .amount-selector label {
  margin-right: 10px;
  color: white;
}

.date-selector input, .currency-selector select, .amount-selector input, button {
  color: black;
  background-color: white;
  border: 1px solid grey;
  padding: 5px;
  border-radius: 5px;
}

button:hover {
  background-color: grey;
  color: white;
}

@media (min-width: 768px) {
  .exchange-rate-container {
    width: 50%;
    margin: 0 auto;
  }
}
</style>
