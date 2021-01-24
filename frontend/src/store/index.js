import Vue from 'vue'
import Vuex from 'vuex'

// Domain
import projectsStore from "@/store/modules/domain/projectsStore"

// App 
import errorStore from "@/store/modules/app/errorStore"

Vue.use(Vuex)

const debug = process.env.NODE_ENV !== 'production'

export default new Vuex.Store({
  modules: {
    errorStore,

    projectsStore
  },
  strict: debug
})
