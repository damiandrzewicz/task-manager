import isNil from 'lodash/isNull';

const state = {
    globals: {
        alert:{
            type: null,
            message: null
        },
        loading: false
    }
}

const getters = {
    isAlert: state => {return !isNil(state.globals.alert.type) ? true : false},
    alertType: state => {return !isNil(state.globals.alert.type) ? state.globals.alert.type : ""} ,
    alertMessage: state => { return !isNil(state.globals.alert.message) ? state.globals.alert.message : "" },
    isLoading: state => { return state.globals.loading},
}

const actions = {
    showAlert({commit}, payload){
        commit("SET_GLOBAL_ALERT_TYPE", payload.type);
        commit("SET_GLOBAL_ALERT_MESSAGE", payload.message);
    },
    removeAlert({commit}){
        commit("SET_GLOBAL_ALERT_TYPE", null);
        commit("SET_GLOBAL_ALERT_MESSAGE", null);
    },
    setLoading({commit}, value){
        commit("SET_LOADING", value)
    }
}

const mutations = {
    SET_GLOBAL_ALERT_TYPE(state, type){
        state.globals.alert.type = type;
    },
    SET_GLOBAL_ALERT_MESSAGE(state, message){
        state.globals.alert.message = message;
    },
    SET_LOADING(state, value){
        state.globals.loading = value;
    }
}

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
};