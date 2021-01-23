<template>
  <div>
    <!-- Header  -->
    <h1 class="mx-12">Projects</h1>

    <v-container>
      <!-- List  -->
      <div v-if="isLoading">
        loading ...
      </div>
      <div v-else-if="isErrored">
        errored ...
      </div>
      <div v-else>
        <div v-for="project in getRootProjects" :key="project.id">
          <ProjectCard :project="project"/>
        </div>
        
      </div>
      
    </v-container>


  </div>
</template>

<script>
import ProjectCard from "@/components/project/ProjectCard.vue"

export default {
  name: "Projects",
  components: {
    ProjectCard
  },
  data: () => ({
    loading: false,
    errored: false
  }),
  created(){
    this.$log.info("created");
    this.$store.dispatch("projectsStore/loadRootProjects");
  },
  mounted(){

  },
  computed: {
    getRootProjects(){
      return this.$store.getters["projectsStore/rootProjects"]
    },
    isLoading(){
      return this.$store.getters["projectsStore/isLoading"]
    },
    isErrored(){
      return this.$store.getters["projectsStore/isErrored"]
    }
  }
}
</script>

<style>

</style>