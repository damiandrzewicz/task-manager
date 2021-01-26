<template>
  <v-app>
    <HeaderBar/>
    <NavMenu />
  
    <!-- Sizes your content based upon application components -->
    <v-main>
      <!-- Provides the application the proper gutter -->
      <v-container fluid>

        <!-- Loading circle  -->
        <v-overlay v-if="isLoading" :absolute="true" >
          <v-progress-circular :size="80" color="primary" indeterminate></v-progress-circular>
        </v-overlay>

        <router-view></router-view>
      </v-container>
    </v-main>

    <GlobalAlert v-if="isAlert"/>
  </v-app>
</template>

<script>
import HeaderBar from "@/components/nav/HeaderBar.vue"
import NavMenu from "@/components/nav/NavMenu.vue"
import GlobalAlert from "@/components/dialogs/GlobalAlert"

export default {
  name: 'App',

  components: {
    HeaderBar,
    NavMenu,
    GlobalAlert
  },

  data: () => ({
    
  }),
  computed: {
    isAlert(){
      return this.$store.getters["appStore/isAlert"];
    },
    isLoading(){
      let value =  this.$store.getters["appStore/isLoading"]
      console.log(value)
      return value;
    }
  }
};
</script>
