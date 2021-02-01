import isNil from 'lodash/isNull';

const state = {
    globals: {
        alert:{
            type: null,
            message: null
        },
        loading: false,
    },
    view: {
        projectDialog: {
            show: false,
            data: null
        }
    }
}

const getters = {
    isAlert: state => {return !isNil(state.globals.alert.type) ? true : false},
    alertType: state => {return !isNil(state.globals.alert.type) ? state.globals.alert.type : ""} ,
    alertMessage: state => { return !isNil(state.globals.alert.message) ? state.globals.alert.message : "" },
    isLoading: state => { return state.globals.loading},

    projectDialogProperties: state => { return state.view.projectDialog }
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
    },

    setProjectDialogProperties({commit}, payload){
        if(payload.show){
            commit("SET_PROJECT_DIALOG_PROPERTIES", { show: payload.show, data: payload.data })
        } else {
            commit("SET_PROJECT_DIALOG_PROPERTIES", { show: false, data: null })
        }
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
    },

    SET_PROJECT_DIALOG_PROPERTIES(state, value){
        state.view.projectDialog = value
    }
}

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
};