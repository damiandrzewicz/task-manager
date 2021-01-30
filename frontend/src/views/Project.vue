<template>
  <div v-if="project">
    <!-- Header  -->
    <div class=" mx-12 my-5 text-h4">Project details</div>

    <v-container >
        <v-card >
            <v-row class="mx-3">
                <v-col md="6">
                    <v-text-field dense readonly label="Name" :value="project.name">sdf</v-text-field>
                    <v-text-field dense readonly label="Creator" value="test owner">sdf</v-text-field>
                    <v-text-field dense readonly label="Created" :value="project.created" :suffix="getDaysUntilNow()">sdf</v-text-field>

                </v-col>
            </v-row>

            <v-row class="mx-3"> 
                <v-col>
                    <v-textarea dense readonly label="Description" :value="project.description"></v-textarea>
                </v-col>
            </v-row>
        </v-card>
    </v-container>
  </div>
</template>

<script>
import isNil from 'lodash/isNil';
import { loadingOn, loadingOff, alert } from "@/utils/globals"
import { getDateFromFormat } from "@/utils/date"

export default {
    name: "Project",
    components: {

    },
    data: () => ({
        project: null
    }),
    mounted(){
        this.fetchProject();
    },
    methods: {
        // Routes
        getRouteProjectId(){
            const id = this.$route.params.projectId;
            return !isNil(id) ? parseInt(id, 10) : null
        },

        // API
        fetchProject(){
            loadingOn()
            this.$store.dispatch("projectsStore/loadProject", this.getRouteProjectId())
                .then(() => {
                    loadingOff()
                    this.project = this.$store.getters['projectsStore/projectById'](this.getRouteProjectId())
                })
                .catch(err => {
                    this.$log.error(err)
                    alert("error", "Cannot load project")
                })
        },

        //Helpers
        getDaysUntilNow(){
            const now = new Date(Date.now())
            const created = new Date(getDateFromFormat(this.project.created, 'dd-MM-yyyy kk:mm:ss'))
            const diffInTime = now.getTime() - created.getTime()
            const diffInDays = diffInTime / (1000 * 3600 * 24); 
            const diffInDaysRounded = Math.floor(diffInDays);
            return `(${diffInDaysRounded} ${ diffInDaysRounded > 1 ? 'days' : 'day' } ago)`
        }
    },
    computed: {

    }
    
}
</script>

<style>

</style>