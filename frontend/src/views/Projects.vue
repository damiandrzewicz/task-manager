<template>
  <div>
    <!-- Header  -->
    <h1 class="mx-12">Projects</h1>

    <v-container fluid >

      <!-- List  -->
      <div>
        <div v-for="project in projects" :key="project.id">
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

import isNil from 'lodash/isNil';
import { loadingOn, loadingOff, alert } from "@/utils/globals"

export default {
  name: "Projects",
  components: {
    ProjectCard,
  },
  props: {
  },
  data: () => ({
    projects: null
  }),
  created(){
    this.$log.info("projects");
    this.loadPage();
  },
  mounted(){

  },
  computed: {

  },
  methods: {
    parentId(){
      return this.$route.params.parentId;
    },
    // API wrappers
    fetchRootProjects(){
      loadingOn()
      this.$store.dispatch("projectsStore/loadRootProjects")
        .then(() => {
          loadingOff()
          
          this.projects = this.$store.getters["projectsStore/rootProjects"];
          this.$log.debug(`loading roots: ${this.projects.length} for ${this.parentId()}`)
        })
        .catch(err => {
          this.$log.error(err)
          alert("error", "Cannot load projects")
        })
    },
    fetchSubProjects(){
      loadingOn()
      this.$store.dispatch("projectsStore/loadSubProjects", this.parentId())
        .then(() => {
          loadingOff()
          
          this.projects = this.$store.getters["projectsStore/childProjectsForParent"](  parseInt(this.parentId(), 10))
          this.$log.debug(`loading subs: ${this.projects.length} for ${this.parentId()}`)
        })
        .catch(err => {
          this.$log.error(err)
          alert("error", "Cannot load projects")
        })
    },
    loadPage(){
      this.$log.debug("exec")
      if(isNil(this.parentId())){
        this.fetchRootProjects()
      }else {
        this.fetchSubProjects()
      }
    }
  },
  watch:{
      "$route.params.parentId"() {
          this.$log.debug(`projectId changed`)
          this.loadPage();
      }
  }
}
</script>

<style>

</style>