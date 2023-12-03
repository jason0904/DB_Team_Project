export const state = () => ({
  isLoggedIn: false,
  userData: null,
  userName: null,
  storedAccount: null
});

export const mutations = {
  setLoginState(state, isLoggedIn) {
    state.isLoggedIn = isLoggedIn;
  },
  setUserData(state, { userData, userName }) {
    state.userData = userData;
    state.userName = userName;
  },
  setStoredAccount(state, storedAccount) {
    state.storedAccount = storedAccount;
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
