<template>
  <div class="stock-detail-container">
    <!-- 종목 기본 정보 -->
    <div class="stock-name">
      <h1 v-if="stockData.name">{{ stockData.name }}의 {{ this.$route.query.itemName }}({{ stockData.item_id }})에 대한 평가입니다.</h1>
      <div v-else>조회할 수 없습니다.</div>
    </div>

    <!-- 재무제표 -->
    <div class="financial-statements">
      <h2>재무제표</h2>
      <div class="financial-statements card">
        <table>
          <thead>
          <tr>
            <th>기간</th>
            <th>매출액</th>
            <th>순이익</th>
            <th>총자산</th>
            <th>총부채</th>
            <th>자본</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="data in financialData" :key="data.statement_id">
            <td>{{ data.report_period }}</td>
            <td>{{ data.revenue }}</td>
            <td>{{ data.net_income }}</td>
            <td>{{ data.total_assets }}</td>
            <td>{{ data.total_liabilities }}</td>
            <td>{{ data.equity }}</td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>

    <!-- 애널리스트 평가 -->
    <div class="analyst-evaluation">
      <h2>애널리스트 평가</h2>
      <div class="analyst-evaluation card">
        <table>
          <thead>
          <tr>
            <th>이름</th>
            <th>평가 등급</th>
            <th>목표 주가</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="analyst in analystsData" :key="analyst.analyst_id">
            <td>{{ analyst.name }}</td>
            <td>{{ analyst.analyst_rating }}</td>
            <td>{{ analyst.target_price }}</td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>

    <!-- 애널리스트 상세 정보 -->
    <div class="analyst-details">
      <h2>애널리스트 상세 정보</h2>
      <div class="analyst-details card">
        <table>
          <thead>
          <tr>
            <th>이름</th>
            <th>회사</th>
            <th>전문 분야</th>
            <th>연락처</th>
            <th>보고 날짜</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="analyst in analystsData" :key="analyst.analyst_id">
            <td>{{ analyst.name }}</td>
            <td>{{ analyst.firm }}</td>
            <td>{{ analyst.expertise_area }}</td>
            <td>{{ analyst.contact_info }}</td>
            <td>{{ analyst.report_date.slice(0, 10) }}</td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      stockData: {
        name: '',
        item_id: null
      },
      financialData: [],
      analystsData: []
    };
  },
  created() {
    this.fetchData();
  },
  methods: {
    async fetchData() {
      const axiosModule = await import('axios');
      const axios = axiosModule.default;
      try {
        const response = await axios.post('https://dbspring.dongwoo.win/api/item/info', {
          item_id: this.$route.query.itemId,
          report_period: '2022-Q4'
        }, {
          headers: {
            'Content-Type': 'application/json'
          }
        });
        console.log(response.data);
        this.stockData.name = response.data.name;
        this.stockData.item_id = response.data.item_id;
        this.financialData = [response.data];
        this.analystsData = [response.data];
      } catch (error) {
        console.error(error);
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

.stock-detail-container {
  background-color: #212529;
  color: #f8f9fa;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.5);
  max-width: 800px;
  margin: auto;
}

.card {
  background: #343a40;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.5);
  padding: 20px;
  margin-bottom: 20px;
}

.card table {
  width: 100%;
  border-collapse: collapse;
  margin-bottom: 20px;
}

.card th, .card td {
  text-align: left;
  padding: 8px;
  border-bottom: 1px solid #dee2e6;
}

.card th {
  background-color: #343a40;
  color: #f8f9fa;
}

.card td {
  background-color: #212529;
  color: #f8f9fa;
}

.stock-name h1, .financial-statements h2, .analyst-evaluation h2, .analyst-details h2 {
  font-size: 1.5em;
  color: #ffffff;
  margin-bottom: 0.5em;
}

@media (max-width: 768px) {
  .stock-detail-container {
    padding: 10px;
  }

  .card {
    padding: 15px;
  }

  .stock-name h1 {
    font-size: 1.5em;
  }
}
</style>
