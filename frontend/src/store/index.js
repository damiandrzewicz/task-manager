import Vue from 'vue'
import Vuex from 'vuex'

// Domain
import projectsStore from "@/store/modules/domain/projectsStore"

// App 
import appStore from "@/store/modules/app/appStore"

Vue.use(Vuex)

const debug = process.env.NODE_ENV !== 'production'

export default new Vuex.Store({
  modules: {
    appStore,

    projectsStore
  },
  strict: debug
})
