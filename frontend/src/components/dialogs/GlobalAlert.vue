<template>
  <div class="d-flex justify-center alert" style="z-index: 100;">
    <v-alert
        border="left"   
        :type="errorType"
        transition="slide-y-reverse-transition"
        max-width="800"
        @click="removeError"
    >
      {{ this.errorTypeView + ": " + this.errorMessage }}
    </v-alert>
  </div>

</template>

<script>
import isNil from 'lodash/isNull';
import {capitalize} from "@/utils/misc"

export default {
    name: "GlobalAlert",
    computed:{
      errorType(){
        let value = this.$store.getters["appStore/alertType"];
        if(!isNil(value)){
          return value;
        } else {
          return "";
        }
      },
      errorTypeView(){
        return capitalize(this.errorType);
      },
      errorMessage(){
        let value = this.$store.getters["appStore/alertMessage"];
        if(!isNil(value)){
          return capitalize(value)
        } else {
          return "";
        }
      },
    },
    methods: {
      removeError(){
        this.$store.dispatch("appStore/removeAlert");
      },
    },
    mounted(){
       setTimeout(this.removeError, 5000);
    }
}
</script>

<style>

</style>