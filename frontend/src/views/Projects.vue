<template>
  <div>


    <!-- Header  -->
    <h1 class="mx-12">Projects</h1>

    <v-container fluid >

      <!-- Navigation -->
      <Breadcrumb :items="getBreadcrumbs" />

      <v-toolbar flat dense class="mb-4">
        <v-spacer></v-spacer>
        <v-btn @click="onOpenProjectDialogAddMode">Add Project</v-btn>
      </v-toolbar>

      <!-- List  -->
      <ProjectsList :projects="getSubProjects"/>
      
    </v-container>
  </div>
</template>

<script>
import Breadcrumb from "@/components/nav/Breadcrumb"
import ProjectsList from "@/components/project/ProjectsList"



import isNil from 'lodash/isNil';
import { loadingOn, loadingOff, alert } from "@/utils/globals"

export default {
  name: "Projects",
  components: {
    Breadcrumb,
    ProjectsList,
  },
  data: () => ({
    breadcrumbs: [
      { path: `/`, name: `test`}
    ],
  }),
  created(){
    this.$log.info("projects");
  },
  mounted(){
    this.$log.debug(`this.$route.name: ${this.$route.name}`)
    this.fetchProjects();
  },
  computed: {


    // Routes
    getRouteProjectId(){
      const id = this.$route.params.projectId;
      return !isNil(id) ? parseInt(id, 10) : null
    },

    getSubProjects(){
        return this.$store.getters["projectsStore/subProjects"](this.getRouteProjectId);
    },

    getBreadcrumbs(){
      let breadcrumbs = []
      let buildBreadcrumbObject = (project) => ({ name: project.name, path: this.getBreadcrumbPath(project.id) })
      const id = this.getRouteProjectId;
      let project = this.$store.getters["projectsStore/projectById"](id);
      while(!isNil(project)){
        breadcrumbs.unshift(buildBreadcrumbObject(project))
        project = this.$store.getters["projectsStore/projectById"](project.parentId);
      }
      breadcrumbs.unshift(buildBreadcrumbObject({ name: "Roots", id: null }))
      return breadcrumbs;
    }
  },
  methods: {
    // API
    fetchProjects(){
      loadingOn()
      this.$store.dispatch("projectsStore/loadAllProjects")
        .then(() => {
          loadingOff()
        })
        .catch(err => {
          this.$log.error(err)
          alert("error", "Cannot load projects")
        })
    },

    // Helpers
    getBreadcrumbPath(id){
      return isNil(id) ? `/projects/list` : `/projects/${id}/list`
    },

    onOpenProjectDialogAddMode(){
      this.$store.dispatch('appStore/setProjectDialogProperties', {
        show: true,
        data: {
          mode: "create"
        }
      })
    },
  },
}
</script>

<style>

</style>