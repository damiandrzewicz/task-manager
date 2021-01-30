import store from "@/store"
import Vue from "vue"
import isNil from 'lodash/isNil';

export function loadingOn(){
    store.dispatch("appStore/setLoading", true)
}

export function loadingOff(){
    store.dispatch("appStore/setLoading", false)
}

export function alert(type, message){
    store.dispatch("appStore/showAlert", {type: type, message: message})
}

export function getRouteProjectId(){
    const id = Vue.$route.params.projectId;
    return !isNil(id) ? parseInt(id, 10) : null
}