export const state = () => ({
  isLoggedIn: false,
  userData: null,
  userName: null,
  storedAccount: null,
  typeAccount: null
});

export const mutations = {
  setLoginState(state, isLoggedIn) {
    state.isLoggedIn = isLoggedIn;
  },
  setUserData(state, { userData, userName }) {
    state.userData = userData;
    state.userName = userName;
  },
  setStoredAccount(state, { storedAccount, typeAccount }) {
    state.storedAccount = storedAccount;
    state.typeAccount = typeAccount;
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
  }
};
