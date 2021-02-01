<template>
  <v-app>
    <HeaderBar/>
    <NavMenu />

    <!-- Hidden dialogs -->
    <ProjectDialog 
      v-if="getProjectDialogProperties.show"
      :show="getProjectDialogProperties.show" 
      :mode="getProjectDialogProperties.data.mode" 
      :projectId="getProjectDialogProperties.data.projectId" 
    />
  
    <!-- Sizes your content based upon application components -->
    <v-main>
      <!-- Provides the application the proper gutter -->
      <v-container fluid >

        <!-- Loading circle  -->
        <v-overlay v-if="isLoading" :absolute="true" color="transparent">
          <v-progress-circular :size="80" color="primary" indeterminate></v-progress-circular>
        </v-overlay>

        <router-view></router-view>
      </v-container>
      <GlobalAlert v-if="isAlert"/>
    </v-main>

    
  </v-app>
</template>

<script>
import HeaderBar from "@/components/nav/HeaderBar.vue"
import NavMenu from "@/components/nav/NavMenu.vue"
import GlobalAlert from "@/components/dialogs/GlobalAlert"

import ProjectDialog from "@/components/project/ProjectDialog"

export default {
  name: 'App',

  components: {
    HeaderBar,
    NavMenu,
    GlobalAlert,
    ProjectDialog
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
    },

    
    getProjectDialogProperties(){
      return this.$store.getters["appStore/projectDialogProperties"]
    },
  }
};
</script>

<style>

</style>
