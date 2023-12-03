<template>
  <div class="z-0 bg-img section h-content">
    <div class="pt-16 pb-10 md:pt-24 md:pb-10 max-w-7xl mx-auto text-6xl md:text-9xl text-white text-center">
      <span class="pt-8 font-extrabold tracking-tight">데이타베이스 설계 1조</span>
    </div>

    <!-- 로그인 오류 메시지 -->
    <div v-if="loginError" class="text-center text-red-500 mt-5">
      <p>아이디 또는 비밀번호를 확인해주세요.</p>
    </div>

    <!-- 조건부 로그인 폼 -->
    <div v-if="!isLoggedIn" class="text-center mt-10">
      <form @submit.prevent="login">
        <input type="text" placeholder="아이디" class="text-black p-2" v-model="userAccountID" />
        <input type="password" placeholder="비밀번호" class="text-black p-2" v-model="userAccountPassword" />
        <button type="submit" class="bg-blue-500 text-white p-2">로그인</button>
      </form>
    </div>

    <!-- 로그인 후 표시 -->
    <div v-if="isLoggedIn" class="text-center text-white mt- 10">
      <p>안녕하세요, {{username}}님</p>
      <button @click="logout" class="bg-red-500 text-white p-2">로그아웃</button>
    </div>

    <!-- 로그인 후 계좌 선택표시 -->
    <!-- 드롭다운으로 계좌 선택, 별도 버튼 없이 선택만으로 적용 -->
    <div v-if="isLoggedIn" class="text-center text-white mt- 10">
      <p>계좌를 선택하세요</p>
      <select v-model="selectedAccount" @change="accountSelected($event.target.value)">
        <option disabled value="">계좌를 선택하세요</option>
        <option v-for="account in accountNumbers" :key="account" :value="account">{{ account }}</option>
      </select>
      <!-- 계좌 비밀번호(this.accountPassword에 저장) -->
      <div v-if="!accountLoggined">
        <input type="password" placeholder="계좌 비밀번호" class="text-black p-2" v-model="accountPassword" />
        <button @click="checkAccount" class="bg-blue-500 text-white p-2">조회</button>
      </div>
    </div>

    <!-- 조건부 네비게이션 버튼 -->
    <div v-if="isLoggedIn && accountLoggined" class="text-center mt-5">
      <button @click="goToOrder" class="bg-red-500 text-white p-2">주문하기</button>
      <button @click="checkBalance" class="bg-green-500 text-white p-2">잔고 확인</button>
      <button @click="checkExchangeRate" class="bg-blue-500 text-white p-2">환율 확인</button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      userAccountID: '',
      userAccountPassword: '',
      loginError: false, // 로그인 오류 메시지 상태
      accountNumbers: ['1234567890(일반)', '0987654321(금)', '1357924680(일반)'], // 계좌 번호 목록
      selectedAccount: '', // 선택된 계좌 번호
      accountPassword: '', // 계좌 비밀번호
      accountLoggined: false,
      accountType: '',
      typeAccount: ''
    };
  },
  computed: {
    // Vuex 스토어 상태 참조
    isLoggedIn() {
      return this.$store.state.isLoggedIn;
    },
    username() {
      return this.$store.state.userName;
    },
    userID() {
      return this.$store.state.userData;
    }
  },
  created() {
    let accountNumber = this.$store.state.storedAccount;
    if (accountNumber === null){
      this.accountLoggined = false;
    } else {
      this.accountLoggined = true;
      this.selectedAccount = accountNumber;
    }
    //계정 로그인이 되어있으면 계좌 로그인이 되었는지 여부를 판단하고 로그인이 되어있으면 비밀번호 조회버튼이 뜨지않는다.
    //계좌를 새로 선택하면 비밀번호 조회버튼이 뜬다.
    //계정이 로그인 되어있지 않으면 계좌 로그인 창이 뜨지않는다.
    //계정이 로그인되어있고 게좌 로그인이 되어있지않으면 계좌 비밀번호 조회 버튼이 뜬다.
  },
  methods: {
    async login() {
      const axiosModule = await import('axios');
      const axios = axiosModule.default;

      try {
        const response = await axios.post('https://dbspring.dongwoo.win/api/login', {
          id: this.userAccountID,
          password_hash: this.userAccountPassword
        }, {
          headers: {
            'Content-Type': 'application/json'
          }
        });

        // 응답 처리
        const userData = response.data;
        // API 응답 예시
        // Login response: {uid: 1, username: 'admin', usertype: 'personal', account_status: 'active', created_at: '2019-01-01T00:00:00.000+00:00'}

        if (userData.account_status === 'active') { // API 응답에서 성공 여부를 확인하는 조건
          // 로그인 처리
          this.loginError = false;
          this.$store.dispatch('login', {
            userData: userData.userData, // API 응답에서 사용자 데이터 사용
            userName: userData.username
          });

          this.userAccountID = '';
          this.userAccountPassword = '';
          console.log('Logged in as:', userData.username);
        } else {
          this.loginError = true;
          console.log('Login failed:', userData.message);
        }
      } catch (error) {
        // 에러 처리
        this.loginError = true;
        console.error('Login error:', error);
      }
    },
    logout() {
      // 로그아웃 상태를 Vuex 스토어에 저장합니다.
      this.$store.dispatch('logout');
      this.$store.commit('setStoredAccount', null);
      console.log('Logged out');
    },
    goToOrder() {
      this.$router.push({ name: 'find_item' });
      console.log('Redirecting to order page');
    },
    checkBalance() {
      this.$router.push({ name: 'balance' });
      console.log('Checking balance');
    },
    checkExchangeRate() {
      this.$router.push({ name: 'exchange_rate' });
      console.log('Checking exchange rate');
    },
    accountSelected(accountNumber) {
      //accountNumber 는 accountAccount(typeAccount) 형식이므로 (와 )를 기준으로 나누어 selectedAccount와 typeAccount에 저장한다.
      this.accountLoggined = false;

      let accountNumberSplit = accountNumber.split('(');
      this.selectedAccount = accountNumberSplit[0];
      this.typeAccount = accountNumberSplit[1].split(')')[0];
    },
    async checkAccount() {
      console.log(this.accountPassword);
      const axiosModule = await import('axios');
      const axios = axiosModule.default;

      try {
        const response = await axios.post('https://dbspring.dongwoo.win/api/check_account', {
          id: this.userID,
          account_number: this.selectedAccount,
          account_password: this.accountPassword
        }, {
          headers: {
            'Content-Type': 'application/json'
          }
        });

        // 응답 처리
        const accountData = response.data;
        // API 응답 예시

        this.$store.commit('setStoredAccount', {
          storedAccount: this.selectedAccount,
          typeAccount: this.typeAccount
        });
        this.accountPassword = '';
      } catch (error) {
        // 에러 처리
        console.error('Account check error:', error);
      }
    }
  }
}
</script>
<style>
body, .bg-img, .section, .h-content {
  background-color: #000000;
  min-height: 100vh;
}
.bg-img, .section, .h-content {
  position: relative;
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
}
input[type="text"], input[type="password"] {
  margin: 0.5rem;
  padding: 0.5rem;
  border-radius: 0.25rem;
  border: 1px solid #ccc;
}

button {
  margin: 0.5rem;
  padding: 0.5rem 1rem;
  border-radius: 0.25rem;
  border: none;
  cursor: pointer;
}
button:hover {
  opacity: 0.9;
}
select {
  margin: 0.5rem;
  padding: 0.5rem;
  border-radius: 0.25rem;
  border: 1px solid #ccc;
  background-color: white; /* 배경 색상 변경 */
  color: black; /* 글씨 색상 변경 */
  cursor: pointer;
}

select:hover {
  opacity: 0.9;
}
</style>

