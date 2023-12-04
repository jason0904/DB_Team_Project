<template>
  <div class="account-container">
    <!-- 조회 기간 선택 섹션 -->
    <div class="date-selector">
      <h2>조회 기간 선택</h2>
      <label for="start-date">시작일:</label>
      <input type="date" id="start-date" v-model="startDate" @change="checkDate">

      <label>조회 범위:</label>
      <button v-for="range in dateRanges" :key="range" @click="setEndDate(range)">{{ range }}</button>

      <p v-if="dateError" class="error-message">{{ dateError }}</p>
    </div>

    <!-- 계좌 정보 섹션 -->
    <div class="account-info">
      <h2>계좌 정보</h2>
      <p>계좌 번호: {{ accountNumber }}</p>
      <p>계좌 타입: {{ accountType }}</p>
    </div>

    <!-- 체결 기록 섹션 -->
    <div class="transaction-records">
      <h2 style="margin-bottom: 5px;">체결 기록</h2>
      <table>
        <thead>
        <tr>
          <th>주문번호</th>
          <th>체결유형</th>
          <th>체결수량</th>
          <th>체결가</th>
          <th>체결일시</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="record in transactionRecords" :key="record.orderNumber">
          <td>{{ record.orderNumber }}</td>
          <td>{{ record.transactionType }}</td>
          <td>{{ record.quantity }}</td>
          <td>{{ record.price }}</td>
          <td>{{ record.date }}</td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      startDate: '',
      endDate: '',
      dateError: '',
      dateRanges: ['1일', '1주', '2주', '1달'],
      accountNumber: '123456789',
      accountType: '저축 계좌',
      transactionRecords: [
        { orderNumber: '001', transactionType: '매수', quantity: '100', price: '50000', date: '2023-12-01' },
        { orderNumber: '002', transactionType: '매도', quantity: '50', price: '52000', date: '2023-12-02' },
        // 추가 더미 데이터...
      ],
    };
  },
  computed: {
  },
  methods: {
    //api 호출
    checkDate() {
      // 날짜 유효성 검사 로직...
    },
    setEndDate(range) {
      // 날짜 설정 로직...
    }
  }
};
</script>

<style>
body {
  font-family: 'Arial', sans-serif;
  background-color: #000; /* 검정색 배경 */
}

.account-container {
  width: 80%;
  margin: 0 auto;
  padding: 20px;
}

.date-selector, .account-info, .transaction-records {
  background-color: #555; /* 진한 회색 배경 */
  border-radius: 10px;
  padding: 15px;
  margin-bottom: 20px;
}

.date-selector label, .date-selector input, .date-selector button {
  margin-right: 10px;
  padding: 5px;
  border-radius: 5px;
  border: 1px solid #ddd;
}

.date-selector button {
  background-color: #f5f5f5;
  cursor: pointer;
}

.date-selector button:hover {
  background-color: #e0e0e0;
}

.error-message {
  color: red;
  margin-top: 10px;
}

.transaction-records table {
  width: 100%;
  border-collapse: collapse;
}

.transaction-records th, .transaction-records td {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: left;
}

.transaction-records th {
  background-color: #4CAF50;
  color: white;
}

.transaction-records tr:nth-child(even) {
  background-color: #f2f2f2;
}

@media (min-width: 768px) {
  .account-container {
    width: 50%;
    margin: 0 auto;
  }
}
</style>
