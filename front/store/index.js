export const state = () => ({
  isLoggedIn: false,
  userData: null,
  userName: null,
  storedAccount: '',
  typeAccount: null,
  account_id: '',
  accoutNumbers: []
});

export const mutations = {
  setLoginState(state, isLoggedIn) {
    state.isLoggedIn = isLoggedIn;
  },
  setUserData(state, { userData, userName }) {
    state.userData = userData;
    state.userName = userName;
  },
  setStoredAccount(state, { storedAccount, typeAccount, account_id }) {
    state.storedAccount = storedAccount;
    state.typeAccount = typeAccount;
    state.account_id = account_id;
  },
  setAccountNumbers(state, accoutNumbers) {
    state.accoutNumbers = accoutNumbers;
  }
};


export const actions = {
  login({ commit }, { userData, userName }) {
    commit('setLoginState', true);
    commit('setUserData', { userData, userName });
  },
  logout({ commit }) {
    commit('setLoginState', false);
    commit('setUserData', { userData: null, userName: null });
  },
    setStoredAccount({ commit }, { storedAccount, typeAccount, account_id }) {
        commit('setStoredAccount', { storedAccount, typeAccount, account_id });
    },
    setAccountNumbers({ commit }, accoutNumbers) {
        commit('setAccountNumbers', accoutNumbers);
    }
};
