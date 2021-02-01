<template>
    <div class="text-center">
        <v-dialog v-model="show" max-width="700"> 
            <ProjectCard :project="project" :mode="mode" @eventOk="onOk" @eventCancel="onCancel">
                <template v-slot:title>
                    {{getCardTitle()}}
                </template>

                <!-- <template v-slot:actions>
                    <v-btn @click="onOk">Ok</v-btn>
                    <v-btn @click="onCancel">Cancel</v-btn>
                </template> -->
            </ProjectCard>
        </v-dialog>
    </div>
</template>

<script>
import ProjectCard from "@/components/project/ProjectCard"
import { Project } from "@/model/Project"
import { capitalize } from "@/utils/misc"

export default {
    name: "ProjectDialog",
    props: {
        show: {
            type: Boolean,
            default: true
        },
        mode: {
            type: String,
            default: null
        },
        projectId: {
            type: Number,
            default: null
        },
    },
    components: {
        ProjectCard
    },
    data: () => ({
        project: new Project(),
        cardTitle: null
    }),
    created(){
        this.$log.debug('exec')
        this.$log.debug(`this.projectId: ${this.projectId}`)
        if(this.mode === 'view' || this.mode === 'edit'){
            this.cardTitle = "Project view"
            this.project = this.$store.getters['projectsStore/projectById'](this.projectId)
        }
    },
    computed: {

    },
    methods:{
        getCardTitle(){
            return `Project ${capitalize(this.mode)}`
        },

        // Handlers
        onOk(project){
            this.$log.debug('exec')
            this.$store.dispatch('projectsStore/saveProject', {project: project})
                .then(() => {
                    this.$store.dispatch("appStore/setLoading", false)
                })
                .catch(err => {
                    this.$log.error(err.response.data);
                    this.$store.dispatch("errorStore/showError", { type: "error", message: "Cannot add project, check console!" })
                })
            this.onCancel();
        },
        onCancel(){
            this.$log.debug('exec')
            this.$store.dispatch('appStore/setProjectDialogProperties', {
                show: false,
            })
        },

        addProject(){

        }
    }
}
</script>

<style>

</style>