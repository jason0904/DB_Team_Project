<template>
  <div class="api-container">
    <h1>사용자 정보</h1>

    <div v-if="userInfo">
      <p><strong>주소:</strong> {{ userInfo.address }}</p>
      <p><strong>우편번호:</strong> {{ userInfo.postal_code }}</p>
      <p><strong>국가:</strong> {{ userInfo.country }}</p>
      <p><strong>이메일:</strong> {{ userInfo.email }}</p>
      <p><strong>전화번호:</strong> {{ userInfo.phone }}</p>
      <p><strong>나이:</strong> {{ userInfo.age }}</p>
      <p><strong>성별:</strong> {{ userInfo.sex }}</p>
      <p><strong>부가 연락처:</strong> {{ userInfo.contactValue }} ({{ userInfo.contactType }})</p>
    </div>

    <div v-else>
      <p>사용자 정보를 불러오는 중...</p>
    </div>
  </div>
</template>
<script>
import { mapState } from 'vuex';

export default {
  data() {
    return {
      userInfo: []
    };
  },
  mounted() {
    this.fetchUserInfo();
  },
  methods: {
    async fetchUserInfo() {
      const axiosModule = await import('axios');
      const axios = axiosModule.default;

      try {
        const response = await axios.post('https://dbspring.dongwoo.win/api/info', {
          uid: this.$store.state.userData
        }, {
          headers: {
            'Content-Type': 'application/json'
          }
        });

        // 응답 처리
        const accountData = response.data;
        console.log(accountData);
        // accountData에서 데이터를 꺼내 userInfo에 할당
        this.userInfo = accountData;
      } catch (error) {
        console.error(error);
      }
    }
  }
}
</script>
<style>
.api-container {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  text-align: center;
  background-color: #f5f5f5; /* 회색 배경 */
  border-radius: 10px; /* 둥근 모서리 */
}

.api-container p {
  text-align: left;
  color: white; /* 흰색 텍스트 */
  background-color: #757575; /* 회색 박스 */
  padding: 10px;
  border-radius: 5px; /* 둥근 모서리 */
}
</style>