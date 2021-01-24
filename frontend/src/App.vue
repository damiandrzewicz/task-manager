<template>
  <v-app>
    <HeaderBar/>
    <NavMenu />
    

    <!-- Sizes your content based upon application components -->
    <v-main>
      <!-- Provides the application the proper gutter -->
      <v-container fluid>
        <router-view></router-view>
      </v-container>

      

    </v-main>

    <GlobalAlert v-if="isError"/>
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
    isError(){
        return this.$store.getters["errorStore/isError"];
      }
  }
};
</script>
