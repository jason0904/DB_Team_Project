<template>
  <div class="account-container">
    <!-- 조회 기간 선택 섹션 -->
    <div class="date-selector">
      <h2>조회 일 선택</h2>
      <label for="start-date">조회일:</label>
      <input type="date" id="start-date" v-model="startDate" @change="checkDate">

      <p v-if="dateError" class="error-message">{{ dateError }}</p>
    </div>

    <!-- 계좌 정보 섹션 -->
    <div class="account-info">
      <h2>계좌 정보</h2>
      <p>계좌 번호: {{ accountNumber }}</p>
      <p>계좌 유형: {{ accountType }}</p>
    </div>

    <!-- 체결 기록 섹션 -->
    <div class="transaction-records">
      <h2 style="margin-bottom: 5px;">체결 기록</h2>
      <table>
        <thead>
        <tr>
          <th>주문번호</th>
          <th>종목코드</th>
          <th>체결유형</th>
          <th>주문상태</th>
          <th>체결일시</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="record in transactionRecords" :key="record.orderNumber">
          <td>{{ record.orderNumber }}</td>
          <td>{{ record.itemCode }}</td>
          <td>{{ record.transactionType }}</td>
          <td>{{ record.orderStatus }}</td>
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
      dateError: '',
      accountNumber: '',
      accountType: '',
      transactionRecords: [
      ],
    };
  },
  mounted() {
    this.accountNumber = this.$store.state.storedAccount;
    this.accountType = this.$store.state.typeAccount;
  },
  computed: {
  },
  methods: {
    async fetchRecords() {
      const axiosModule = await import('axios');
      const axios = axiosModule.default;

      try {
        const response = await axios.post('https://dbspring.dongwoo.win/api/order/log', {
          account_id: this.$store.state.account_id,
          date: this.startDate
        }, {
          headers: {
            'Content-Type': 'application/json'
          }
        });

        // 응답 처리
        const accountData = response.data;
        console.log(accountData);
        // accountData에서 데이터를 꺼내 transactionRecords에 할당 transactionRecords.orderNumber, transactionRecords.transactionType, transactionRecords.date
        // 반복문 사용
        for (let i = 0; i < accountData.length; i++) {
          this.transactionRecords.push({
            orderNumber: accountData[i].order_id,
            itemCode: accountData[i].item_id,
            transactionType: accountData[i].purchase_type,
            orderStatus: accountData[i].order_status,
            date: accountData[i].success_at.slice(0,10)||"미체결",
          });
        }
      } catch (error) {
        console.error(error);
      }
    },
    checkDate() {
      // 날짜 유효성 검사 로직 - startDate가 오늘 날짜보다 미래인지 확인
      const today = new Date();
      const selectedDate = new Date(this.startDate);
      if (selectedDate > today) {
        this.dateError = '오늘 날짜보다 미래일 수 없습니다.';

      } else {
        this.dateError = '';
        this.fetchRecords();
      }
    },
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
