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
  </v-app>
</template>

<script>
import HeaderBar from "@/components/nav/HeaderBar.vue"
import NavMenu from "@/components/nav/NavMenu.vue"

export default {
  name: 'App',

  components: {
    HeaderBar,
    NavMenu,

  },

  data: () => ({
    
  }),
};
</script>
