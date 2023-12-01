import createPersistedState from 'vuex-persistedstate';

export default ({store}) => {
  createPersistedState({
    paths: ['isLoggedIn', 'userData', 'userName']
  })(store);
};
