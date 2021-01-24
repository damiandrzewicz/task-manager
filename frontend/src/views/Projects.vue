<template>
  <div>
    <!-- Header  -->
    <h1 class="mx-12">Projects</h1>

    <v-container>
      
      <!-- Loading  -->
      <div v-if="isLoading" class="d-flex align-center justify-center flex-column">
        <v-progress-circular
          :size="50"
          color="primary"
          indeterminate
        ></v-progress-circular>
      </div>

      <!-- Error  -->
      <div v-else-if="isErrored">
        errored ...
      </div>

      <!-- List  -->
      <div v-else>
        <div v-for="project in getRootProjects" :key="project.id">
          <ProjectCard :project="project"/>
        </div>
        <AddProjectCard/>
      </div>
      
    </v-container>


  </div>
</template>

<script>
import ProjectCard from "@/components/project/ProjectCard.vue"
import AddProjectCard from "@/components/project/AddProjectCard.vue"

export default {
  name: "Projects",
  components: {
    ProjectCard,
    AddProjectCard
  },
  data: () => ({
    loading: false,
    errored: false
  }),
  created(){
    this.$log.info("Projects");
    this.$store.dispatch("projectsStore/loadProjects");
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