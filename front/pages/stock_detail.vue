<template>
    <div class="stock-detail-container">
      <!-- 종목 기본 정보 -->
      <div class="stock-name">
        <h1>{{ stockData.name }} ({{ stockData.item_id }})</h1>
      </div>
      <div class="stock-basic-info card">
        <table>
          <tr>
            <th>CEO</th>
            <td>{{ stockData.CEO }}</td>
          </tr>
          <tr>
            <th>업종 / 산업군</th>
            <td>{{ stockData.sector }} / {{ stockData.industry }}</td>
          </tr>
          <tr>
            <th>PER / EPS</th>
            <td>{{ stockData.PER }} / {{ stockData.EPS }}</td>
          </tr>
          <tr>
            <th>배당 수익률</th>
            <td>{{ stockData.dividend_yield }}%</td>
          </tr>
          <tr>
            <th>배당금 지급일</th>
            <td>{{ formatDate(stockData.dividend_payment_date) }}</td>
          </tr>
          <tr>
            <th>시가총액</th>
            <td>{{ stockData.market_cap }}</td>
          </tr>
          <tr>
            <th>거래량</th>
            <td>{{ stockData.volume }}</td>
          </tr>
        </table>
      </div>
  
      <!-- 재무제표 -->
      <div class="financial-statements">
        <h2>재무제표</h2>
      </div>
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
            <tr v-for="data in financialData" :key="data.id">
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
      
      <!-- 애널리스트 평가 -->
      <div class="analyst-evaluation">
        <h2>애널리스트 평가</h2>
      </div>
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
            <tr v-for="analyst in analystsData" :key="analyst.id">
              <td>
                <span @mouseover="showTooltip(analyst)" @mouseleave="hideTooltip">
                  {{ analyst.name }}
                </span>
                <div v-if="tooltip.visible && tooltip.analystId === analyst.id" class="tooltip">
                  <p>회사: {{ analyst.company }}</p>
                  <p>전문분야: {{ analyst.specialty }}</p>
                  <p>이메일: {{ analyst.email }}</p>
                </div>
              </td>
              <td>{{ analyst.analyst_rating }}</td>
              <td>{{ analyst.target_price }}</td>
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
        tooltip: {
        visible: false,
        analystId: null
        },
        stockData: {
          name: 'test', // 종목명
          item_id: 123, // 종목 ID
          CEO: '동우', // CEO 이름
          sector: 'polar', // 업종
          industry: 'bear', // 산업군
          dividend_yield: 99, // 배당수익률
          dividend_payment_date: new Date(), // 배당금 지급일
          volume: 0, // 거래량
          market_cap: -3, // 시가총액
          PER: 12.5, // 주가수익비율
          EPS: 3, // 주당순이익
        },
        financialData: [
        {
          id: 1,
          report_period: '2021 Q1',
          revenue: '10B',
          net_income: '1B',
          total_assets: '50B',
          total_liabilities: '30B',
          equity: '20B'
        },
        {
          id: 2,
          report_period: '2021 Q2',
          revenue: '10B',
          net_income: '1B',
          total_assets: '50B',
          total_liabilities: '30B',
          equity: '20B'
        },
        {
          id: 3,
          report_period: '2021 Q3',
          revenue: '10B',
          net_income: '1B',
          total_assets: '50B',
          total_liabilities: '30B',
          equity: '20B'
        },
        // 여기에 더 많은 가짜 데이터 객체를 추가할 수 있습니다.
      ],
      analystsData: [
      {
        id: 1,
        name: '홍길동',
        analyst_rating: '매수',
        target_price: '100,000원',
        company: 'ABC 증권',
        specialty: '기술 산업',
        email: 'honggildong@abcsecurities.com'
      },
      {
        id: 2,
        name: '고지흔',
        analyst_rating: '떡상',
        target_price: '990,000원',
        company: 'XYZ 자산관리',
        specialty: '소비재 산업',
        email: 'kojihun@xyzwealth.com'
      },
        // 여기에 더 많은 가짜 데이터 객체를 추가할 수 있습니다.
      ]
      };
    },
    methods: {
      formatDate(date) {
        return date ? new Date(date).toLocaleDateString() : '';
      },
      showTooltip(analyst) {
      this.tooltip.visible = true;
      this.tooltip.analystId = analyst.id;
      },
      hideTooltip() {
        this.tooltip.visible = false;
      },
    }
      // 여기에 데이터를 로드하는 메소드 추가...
  };
  </script>
  <style>
  body {
    background-color: #000000; /* Black color */
    margin: 0; /* Removes default margin */
    padding: 0; /* Removes default padding */
    min-height: 100vh; /* 100% of the viewport height */
  }
  </style>  
  <style scoped>

  .stock-detail-container {
    background-color: #212529; /* 전체 배경을 검은색으로 설정 */
    color: #f8f9fa; /* 텍스트 색상을 밝은 회색으로 설정 */
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.5);
    max-width: 800px;
    margin: auto;
  }
  
  .card {
    background: #343a40; /* 카드 배경색을 조금 더 밝은 회색으로 설정 */
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.5);
    padding: 20px;
    margin-bottom: 20px;
  }
  .card table {
  width: 100%; /* 테이블의 너비를 카드 너비에 맞춤 */
  border-collapse: collapse; /* 테이블의 경계선을 겹치게 함 */
  margin-bottom: 20px; /* 테이블 아래의 여백 */
  }

  .card th, .card td {
    text-align: left; /* 텍스트를 왼쪽으로 정렬 */
    padding: 8px; /* 셀 내부의 여백 */
    border-bottom: 1px solid #dee2e6; /* 하단 경계선 추가 */
  }

  .card th {
    background-color: #343a40; /* 헤더 셀의 배경색 */
    color: #f8f9fa; /* 헤더 셀의 텍스트 색상 */
  }

  .card td {
    background-color: #212529; /* 데이터 셀의 배경색 */
    color: #f8f9fa; /* 데이터 셀의 텍스트 색상 */
  }
  
  .stock-name h1 {
    font-size: 2em; /* 종목명을 더 크게 설정 */
    color: #ffffff; /* 종목명 텍스트 색상을 흰색으로 설정 */
    margin-bottom: 0.5em;
  }
  .financial-statements h2, .analyst-evaluation h2{
    font-size: 1.5em; /* 종목명을 더 크게 설정 */
    color: #ffffff; /* 종목명 텍스트 색상을 흰색으로 설정 */
    margin-bottom: 0.5em;
  }
  
  .stock-basic-info p, .financial-statements p, .analyst-evaluation p {
    font-size: 0.9em; /* 나머지 텍스트 사이즈를 약간 줄임 */
    color: #dee2e6; /* 문단 색상을 밝은 회색으로 설정 */
  }
  
  /* 반응형 디자인을 위한 미디어 쿼리 */
  @media (max-width: 768px) {
    .stock-detail-container {
      padding: 10px;
    }
  
    .card {
      padding: 15px;
    }
  
    .stock-basic-info h1 {
      font-size: 1.5em; /* 모바일 화면에서는 종목명을 약간 작게 설정 */
    }
  }
  .tooltip {
    position: absolute;
    background-color: #333; /* 어두운 배경색 */
    color: #fff; /* 밝은 글자색 */
    text-align: left;
    padding: 10px; /* 넉넉한 패딩 */
    border: 1px solid #dcdcdc; /* 경계선 색상 */
    border-radius: 5px;
    font-size: 0.9em; /* 적당한 글자 크기 */
    line-height: 1.4; /* 줄 간격 */
    z-index: 1000; /* 다른 요소들 위에 보이도록 z-index 설정 */
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* 약간의 그림자 효과 */

    max-width: 250px; /* 툴팁의 최대 너비 */
  }
  </style>
  
  
  