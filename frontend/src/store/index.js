import { createStore } from 'vuex'
// import axios from 'axios'
// import router from '@/router'
import createPersistedState from "vuex-persistedstate"

// const API_URL = "http://70.12.246.214/api"

const store = createStore({
  plugins: [createPersistedState()],
  state: {
  },
  getters: {
  },
  mutations: {
  },
  actions: {
  },
  modules: {
  }
})

export default store