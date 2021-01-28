<template>
  <div>
      <v-card flat :class="['mb-4', {'card-opened secondary': showSubitems}]" @click="onHandleClick">
          <v-row dense class="mx-1">
            <!-- Name  -->
            <v-col>
                <div :class="['caption', showSubitems ? 'white--text' : 'grey--text' ]" >Name</div>
                <div>{{ project.name }}</div>
            </v-col>

            <!-- Description -->
            <v-col>
                <div :class="['caption', showSubitems ? 'white--text' : 'grey--text' ]">Description</div>
                <div>{{ project.description }}</div>
            </v-col>

            <!-- Subprojects count -->
            <v-col>
                <div :class="['caption', showSubitems ? 'white--text' : 'grey--text' ]">Subprojects</div>
                <div>{{ project.subProjectsIds.length }}</div>
            </v-col>

            <!-- Created -->
            <v-col>
                <div :class="['caption', showSubitems ? 'white--text' : 'grey--text' ]">Created</div>
                <div>{{ project.created }}</div>
            </v-col>

            <!-- Buttons -->
            <v-col md="2" class="d-flex align-center">
                <v-tooltip top>
                    <template v-slot:activator="{ on, attrs }">
                        <v-btn 
                            icon 
                            small 
                            v-bind="attrs" 
                            v-on="on" 
                            @click.stop="onEditProject" 
                            @mousedown.stop
                            :color="showSubitems ? 'white' : ''">
                            <v-icon>mdi-pencil</v-icon>
                        </v-btn>
                    </template>
                    <span>Edit project</span>
                </v-tooltip>

                <v-tooltip top>
                    <template v-slot:activator="{ on, attrs }">
                        <v-btn 
                            icon 
                            small 
                            v-bind="attrs" 
                            v-on="on" 
                            @click.stop="onDeleteProject" 
                            @mousedown.stop
                            :color="showSubitems ? 'white' : ''">
                            <v-icon>mdi-delete</v-icon>
                        </v-btn>
                    </template>
                    <span>Delete project</span>
                </v-tooltip>

                <v-tooltip top>
                    <template v-slot:activator="{ on, attrs }">
                        <v-btn 
                            icon 
                            small 
                            v-bind="attrs" 
                            v-on="on" 
                            @click.stop="onDeleteProject" 
                            @mousedown.stop
                            :color="showSubitems ? 'white' : ''">
                            <v-icon>mdi-plus-box</v-icon>
                        </v-btn>
                    </template>
                    <span>Add subproject</span>
                </v-tooltip>
            </v-col>
          </v-row>

        <!-- Progress bar -->
        <v-row class="mx-1 d-flex align-center">
            <v-col>
                <v-progress-linear :value="project.progress" striped height="6" :color="computeProgressColor"></v-progress-linear>
            </v-col>

            <v-col md="1" class="py-0">
                {{ project.progress }}%
            </v-col>
        </v-row>
      </v-card>

      <!-- Child projects -->
      <div class="pl-5 mt-5" v-show="showSubitems">
        <div v-for="project in getChildProjects" :key="project.id">
            <ProjectCard :project="project"/>
        </div>
        <!-- <AddProjectCard/> -->
      </div>
  </div>
</template>

<script>
import { formatCreatedDate } from "@/utils/date"
import { getProgressColor } from "@/utils/progress"

import { Project } from "@/model/Project"

// import AddProjectCard from "@/components/project/AddProjectCard.vue"


export default {
    name: "ProjectCard",
    components: {
        // AddProjectCard
    },
    props: {
        project: {
            type: Project
        }
    },
    data: () => ({
        showSubitems: false,
        clickData: {
            count: 0,
            timer: null,
            delay: 250
        }
        
    }),
    created(){
        this.$log.debug(`creating card for project: ${JSON.stringify(this.project)}`)
    },

    computed: {
        computeCreated(){
            return formatCreatedDate(this.project.created);
        },
        computeProgressColor(){
            return getProgressColor(this.project.progress)
        },
        getChildProjects(){
            return this.$store.getters["projectsStore/childProjectsForParent"](this.project.id)
        }
    },
    methods: {
        onEditProject(){
            this.$log.debug("called")
        },

        onDeleteProject(){
            this.$log.debug("called")
            this.$store.dispatch("appStore/setLoading", true)
            this.$store.dispatch("projectsStore/deleteProject", this.project.id)
                .then(() => {
                    this.$store.dispatch("appStore/setLoading", false)
                })
                .catch(err => {
                    this.$log.error(err.response.data);
                    this.$store.dispatch("errorStore/showError", { type: "error", message: "Cannot add project, check console!" })
                })
        },

        onOpenProject(){
            this.$log.debug("called")
            this.$router.push(`/projects/${this.project.id}/subprojects`);
        },

        onToggleSubitems(){
            this.$log.debug("called")

            if(!this.project.subProjectsIds.length){
                this.$log.debug(`no subprojects for project id=${this.project.id}`)
                return;
            }
            
            // Load only when opening
            if(!this.showSubitems){
                this.$store.dispatch("appStore/setLoading", true)
                this.$store.dispatch("projectsStore/loadSubProjects", this.project.id)
                    .then(() => {
                        this.$store.dispatch("appStore/setLoading", false)
                    })
                    .catch(err => {
                        this.$log.error(err)
                        this.$store.dispatch("appStore/showAlert", {type: "error", message: "Cannot load subprojects"})
                    }) 
            }

            this.showSubitems = !this.showSubitems;
        },

        onHandleClick(){
            this.$log.debug("called")

            this.clickData.count++
            if(this.clickData.count === 1){
                this.clickData.timer = setTimeout(() => {
                    this.clickData.count = 0;
                    this.onToggleSubitems()
                }, this.clickData.delay);
            } else if(this.clickData.count === 2){
                clearTimeout(this.clickData.timer)
                this.clickData.count = 0;
                this.onOpenProject()
            }
        }
    },

}
</script>

<style>
.v-card--link:before, .v-icon:after {
    background-color: white !important;
}
.card-opened{
    color: white !important;
}
</style>