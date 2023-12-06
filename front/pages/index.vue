<template>
  <div class="z-0 bg-img section h-content">
    <div class="pt-16 pb-10 md:pt-24 md:pb-10 max-w-7xl mx-auto text-6xl md:text-9xl text-white text-center">
      <span class="pt-8 font-extrabold tracking-tight">데이타베이스 설계 1조</span>
    </div>

    <!-- 로그인 오류 메시지 -->
    <div v-if="loginError" class="text-center text-red-500 mt-5">
      <p>아이디 또는 비밀번호를 확인해주세요.</p>
    </div>

    <!-- 유효성 검사 오류 메시지 -->
    <div v-if="invalidCredentials" class="text-center text-red-500 mt-5">
      <p>아이디에는 특수문자를 포함할 수 없으며, 비밀번호는 !@#$를 제외한 특수문자를 포함할 수 없습니다.</p>
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
    <div v-if="isLoggedIn" class="text-center text-white mt-10">
      <p>안녕하세요, {{username}}님</p>
      <button @click="logout" class="bg-red-500 text-white p-2">로그아웃</button>
    </div>

    <!-- 계좌 비밀번호를 확인해주세요. -->
    <div v-if="accountLogginError" class="text-center text-red-500 mt-10">
      <p>계좌 비밀번호를 확인해주세요.(숫자 4자리)</p>
    </div>

    <!-- 로그인 후 계좌 선택표시 -->
    <div v-if="isLoggedIn" class="text-center text-white mt-10">
      <p>현재 선택된 계좌: {{selectedAccount}}</p>
      <select v-model="selectedAccount" @focus="loadAccounts" @change="accountSelected($event.target.value)">
        <option disabled value="">계좌를 선택하세요</option>
        <option v-for="account in accountNumbers" :key="account" :value="account">{{ account }}</option>
      </select>
      <div v-if="!accountLoggined">
        <input type="password" placeholder="계좌 비밀번호" class="text-black p-2" v-model="accountPassword" />
        <button @click="checkAccount" class="bg-blue-500 text-white p-2">조회</button>
      </div>
    </div>

    <!-- 조건부 네비게이션 버튼 -->
    <div v-if="isLoggedIn && accountLoggined" class="text-center mt-5">
      <button @click="goToOrder" class="bg-red-500 text-white p-2">주문하기</button>
      <button @click="checkBalance" class="bg-green-500 text-white p-2">잔고 확인</button>
      <button @click="checkTrade" class="bg-blue-500 text-white p-2">채결 확인</button>
      <button @click="checkExchangeRate" class="bg-yellow-500 text-white p-2">환율 확인</button>
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
      invalidCredentials: false, // 유효하지 않은 입력값에 대한 상태
      accountNumbers: [], // 계좌 번호 목록
      selectedAccount: '', // 선택된 계좌 번호
      accountPassword: '', // 계좌 비밀번호
      accountLoggined: false,
      accountLogginError: false
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
  mounted() {
    if(this.$store !== undefined){
      if(this.$store.state.accoutNumbers === []){
        this.logout();
        console.log(1)
        return
      }
      if (!this.$store.state.isLoggedIn) {
        this.logout();
        console.log(2)
        return;
      }
      this.isLoggedIn = true;
      this.accountLoggined = true;
      this.selectedAccount = this.$store.state.storedAccount;
    }else{
      this.logout()
    }
  },
  methods: {
    validateCredentials() {
      const idRegex = /[^\w]/; // 특수문자를 포함하지 않아야 함
      const passwordRegex = /[^!@#$\w]/; // !@#$와 문자/숫자만 허용
      return !idRegex.test(this.userAccountID) &&
          !passwordRegex.test(this.userAccountPassword);
    },
    async login() {
      if (!this.validateCredentials()) {
        this.invalidCredentials = true;
        return;
      }

      this.invalidCredentials = false;
      this.loginError = false;
      // Import axios and crypto-js modules
      const axiosModule = await import('axios');
      const CryptoJS = await import('crypto-js');
      const axios = axiosModule.default;

      try {
        // Concatenate "ABC" to the password and then hash it using MD5
        const hashedPassword = CryptoJS.MD5(this.userAccountPassword + "abc").toString();

        // Send the request with the hashed password
        const response = await axios.post('https://dbspring.dongwoo.win/api/login', {
          id: this.userAccountID,
          password_hash: hashedPassword
        }, {
          headers: {
            'Content-Type': 'application/json'
          }
        });

        // Handling the response
        const userData = response.data;
        // 예시 - {uid: 1, username: 'admin', accountNumbers: Array(0), accountTypes: Array(0)}
        console.log(userData)

        if (userData.username) {
          // Successful login
          this.loginError = false;
          await this.$store.dispatch('login', {
            userData: userData.userData, // Use user data from API response
            userName: userData.username
          });

          // fill accountNumbers - accountNumbers(accountTypes) 형태로 채워야함(index기준으로 두 배열을 합침)
          this.accountNumbers = userData.accountNumbers.map((accountNumber, index) => {
            return `${accountNumber}(${userData.accountTypes[index]})`;
          });

          this.$store.dispatch('setAccountNumbers', this.accountNumbers);

          // Clear the input fields
          this.userAccountID = '';
          this.userAccountPassword = '';
          console.log('Logged in as:', userData.username);
        } else {
          // Login failed
          this.loginError = true;
          console.log('Login failed:', userData.message);
        }
      } catch (error) {
        // Error handling
        this.loginError = true;
        console.error('Login error:', error);
      }
    },
    logout() {
      this.userAccountID = '';
      this.userAccountPassword = '';
      this.loginError = false;
      this.invalidCredentials = false;
      this.accountNumbers = [];
      this.selectedAccount = '';
      this.accountPassword = '';
      this.accountLoggined = false;
      this.accountLogginError = false;
      if (this.$store !== undefined) {
        // 로그아웃 상태를 Vuex 스토어에 저장합니다.
        this.$store.dispatch('logout');
      }
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
    checkTrade() {
      this.$router.push({ name: 'trade_history' });
      console.log('Checking trade_history');
    },

    async loadAccounts() {
        // Vuex 스토어에서 계좌 번호를 불러오는 로직
        this.accountNumbers = this.$store.state.accoutNumbers;

    },
    checkExchangeRate() {
      this.$router.push({ name: 'exchange_rate' });
      console.log('Checking exchange rate');
    },
    accountSelected(accountNumber) {
      this.accountLoggined = false;
      this.selectedAccount = accountNumber;
    },
    async checkAccount() {
      // passord 숫자 4자리
      const passwordRegex = /[0-9]{4}/;
      if (!passwordRegex.test(this.accountPassword)) {
        this.accountLogginError = true;
        return;
      }
      this.accountLogginError = false;

      const axiosModule = await import('axios');
      const CryptoJS = await import('crypto-js');
      const axios = axiosModule.default;

      // password hash
      const hashedPassword = CryptoJS.MD5(this.accountPassword + "abc").toString();

      try {
        const response = await axios.post('https://dbspring.dongwoo.win/api/account/login', {
          account_number: this.selectedAccount.split('(')[0],
          password_hash: hashedPassword
        }, {
          headers: {
            'Content-Type': 'application/json'
          }
        });

        // 응답 처리
        const accountData = response.data;
        console.log(accountData);

        if(accountData.status === 'activate') {
          this.accountLogginError = false;
          this.accountLoggined = true;

          const accountNumber = this.selectedAccount.split('(')[0];
          const accountType = this.selectedAccount.split('(')[1].split(')')[0];
          const account_id = accountData.account_id;

          this.$store.commit('setStoredAccount', {storedAccount: accountNumber, storedAccountType: accountType, storedAccountID: account_id});
          console.log('Account login success');
        } else {
          this.accountLogginError = true;
          console.log('Account login failed');
        }
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


