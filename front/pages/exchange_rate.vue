<template>
  <div class="exchange-rate-container">
    <div class="controls">
      <div class="date-selector">
        <label for="start-date">Start Date:</label>
        <input type="date" id="start-date" v-model="startDate" min="2010-01-01">

        <label for="end-date">End Date:</label>
        <input type="date" id="end-date" v-model="endDate" min="2010-01-01">
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
    </div>

    <div class="daily-rates" v-if="showRates">
      <h2>일별시세</h2>
      <div class="rates-header">
        <span>Date</span>
        <span>Standard Rate</span>
        <span>Change</span>
        <span>Change Rate</span>
      </div>
      <ul>
        <li v-for="rate in rates" :key="rate.date">
          <span>{{ rate.date }}</span>
          <span>{{ rate.standardRate }}</span>
          <span :class="rate.change.startsWith('+') ? 'up' : 'down'">{{ rate.change }}</span>
          <span :class="rate.change.startsWith('+') ? 'up' : 'down'">{{ rate.changeRate }}%</span>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      startDate: '',
      endDate: '',
      fromCurrency: 'KRW',
      toCurrency: 'USD',
      rates: [],
      showRates: false,
    };
  },
  methods: {
    fetchRates() {
      // 여기에 API 호출 또는 데이터 로딩 로직을 추가합니다.
      // 예시를 위해 임시 데이터를 사용합니다.
      this.rates = [
        // 임시 데이터
        { date: '2023-11-27', standardRate: '1,300', change: '+5', changeRate: 0.4 },
        { date: '2023-11-26', standardRate: '1,295', change: '-5', changeRate: -0.4 },
        { date: '2023-11-25', standardRate: '1,300', change: '+5', changeRate: 0.4 },
        { date: '2023-11-24', standardRate: '1,295', change: '-5', changeRate: -0.4 },
        { date: '2023-11-23', standardRate: '1,300', change: '+5', changeRate: 0.4 },
        { date: '2023-11-22', standardRate: '1,295', change: '-5', changeRate: -0.4 },
        { date: '2023-11-21', standardRate: '1,300', change: '+5', changeRate: 0.4 },
        { date: '2023-11-20', standardRate: '1,295', change: '-5', changeRate: -0.4 },
        { date: '2023-11-19', standardRate: '1,300', change: '+5', changeRate: 0.4 },
        { date: '2023-11-18', standardRate: '1,295', change: '-5', changeRate: -0.4 },
        { date: '2023-11-17', standardRate: '1,300', change: '+5', changeRate: 0.4 },
        { date: '2023-11-16', standardRate: '1,295', change: '-5', changeRate: -0.4 },
        { date: '2023-11-15', standardRate: '1,300', change: '+5', changeRate: 0.4 },
        { date: '2023-11-14', standardRate: '1,295', change: '-5', changeRate: -0.4 },
        { date: '2023-11-13', standardRate: '1,300', change: '+5', changeRate: 0.4 },
        { date: '2023-11-12', standardRate: '1,295', change: '-5', changeRate: -0.4 },

        // 추가 데이터...
      ];
      this.showRates = true;
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

.controls, .daily-rates {
  background-color: #333; /* 박스의 배경색 */
  border-radius: 10px; /* 둥근 모서리 */
  padding: 15px; /* 안쪽 여백 */
  margin-bottom: 20px; /* 박스 간 간격 */
}

.date-selector, .currency-selector {
  margin-bottom: 20px;
}

.date-selector label, .currency-selector label {
  margin-right: 10px;
  color: white;
}

.date-selector input, .currency-selector select, button {
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

.daily-rates h2 {
  margin-bottom: 10px;
}

.rates-header {
  display: flex;
  font-weight: bold;
}

.daily-rates ul {
  list-style-type: none;
  padding: 0;
}

.daily-rates li {
  display: flex;
  margin-bottom: 5px;
}

.daily-rates span {
  margin-right: 10px;
  min-width: 100px;
}

.up {
  color: red;
}

.down {
  color: blue;
}

@media (min-width: 768px) {
  .exchange-rate-container {
    width: 50%;
    margin: 0 auto;
  }
}
</style>

