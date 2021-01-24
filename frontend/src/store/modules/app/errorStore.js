import isNil from 'lodash/isNull';

const state = {
    globalError: {
        type: null,
        message: null
    }
}

const getters = {
    isError: state => {return !isNil(state.globalError.type) ? true : false},
    errorType: state => {return !isNil(state.globalError.type) ? state.globalError.type : ""} ,
    errorMessage: state => { return !isNil(state.globalError.message) ? state.globalError.message : "" },
}

const actions = {
    showError({commit}, payload){
        commit("SET_GLOBAL_ERROR_TYPE", payload.type);
        commit("SET_GLOBAL_ERROR_MESSAGE", payload.message);
    },
    removeError({commit}){
        commit("SET_GLOBAL_ERROR_TYPE", null);
        commit("SET_GLOBAL_ERROR_MESSAGE", null);
    }
}

const mutations = {
    SET_GLOBAL_ERROR_TYPE(state, type){
        state.globalError.type = type;
    },
    SET_GLOBAL_ERROR_MESSAGE(state, message){
        state.globalError.message = message;
    }
}

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
};