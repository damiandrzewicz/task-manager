<template>
  <v-app-bar app clipped-left>
      <v-app-bar-nav-icon></v-app-bar-nav-icon>
      <v-toolbar-title>Task Manager</v-toolbar-title>
  </v-app-bar>
</template>

<script>
export default {
  name: "HeaderBar",
  computed: {
    getCurrentRouteName(){
      return this.$route.name
    },
    isProjectsRoute(){
      return this.getCurrentRouteName === "Projects"
    }
  },
}
</script>

<style>

</style>