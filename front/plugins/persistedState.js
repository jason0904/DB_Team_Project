import createPersistedState from 'vuex-persistedstate';

export default ({store}) => {
  createPersistedState({
    paths: ['isLoggedIn', 'userData', 'userName', 'storedAccount', 'typeAccount', 'account_id', 'accoutNumbers'],
    storage: window.localStorage,
  })(store);
};
