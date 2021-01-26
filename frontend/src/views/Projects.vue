<template>
  <div>
    <!-- Header  -->
    <h1 class="mx-12">Projects</h1>

    <v-container fluid>

      <!-- List  -->
      <div>
        <div v-for="project in getRootProjects" :key="project.id">
            <ProjectCard :project="project"/>
        </div>
        <!-- <AddProjectCard/> -->
      </div>
      
    </v-container>
  </div>
</template>

<script>
import ProjectCard from "@/components/project/ProjectCard.vue"
// import AddProjectCard from "@/components/project/AddProjectCard.vue"

export default {
  name: "Projects",
  components: {
    ProjectCard,
    // AddProjectCard
  },
  data: () => ({
    loading: false,
    errored: false
  }),
  created(){
    this.$log.info("projects");
    this.$store.dispatch("appStore/setLoading", true)
    this.$store.dispatch("projectsStore/loadRootProjects")
      .then(() => {
        this.$store.dispatch("appStore/setLoading", false)
      })
      .catch(err => {
        this.$log.error(err)
        this.$store.dispatch("appStore/showAlert", {type: "error", message: "Cannot load projects"})
      })
  },
  mounted(){

  },
  computed: {
    getRootProjects(){
      return this.$store.getters["projectsStore/rootProjects"]
    },
  }
}
</script>

<style>

</style>