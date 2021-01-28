import store from "@/store"

export function loadingOn(){
    store.dispatch("appStore/setLoading", true)
}

export function loadingOff(){
    store.dispatch("appStore/setLoading", false)
}

export function alert(type, message){
    store.dispatch("appStore/showAlert", {type: type, message: message})
}