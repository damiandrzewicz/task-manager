<template>
  <v-navigation-drawer app absolute clipped>
    <v-list dense nav>
      <v-list-item link v-for="link in links" :key="link.text" :to="link.to" exact>
          <v-list-item-icon>
              <v-icon>{{ link.icon }}</v-icon>
          </v-list-item-icon>
          <v-list-item-content>
              <v-list-item-title >{{ link.text }}</v-list-item-title>
          </v-list-item-content>
      </v-list-item>
    </v-list>
  </v-navigation-drawer>
</template>

<script>
export default {
  name: "NavMenu",
  data: () => ({
    links: [
      { text: "Projects", icon: "mdi-folder", to: { name: "Projects" } }
    ]
  })
  
}
</script>

<style>

</style>