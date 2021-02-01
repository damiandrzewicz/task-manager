<template>
  <div v-if="project">

    <!-- Header  -->
    <div class=" mx-12 my-5 text-h4">Project</div>

    <v-toolbar flat dense class="mb-4">
        <v-spacer></v-spacer>
        <v-btn @click="onOpenProjectDialogEditMode">Edit Project</v-btn>
    </v-toolbar>

    <v-container >
        <ProjectCard :project="project" :mode="'view'"/>
    </v-container>

    <v-container>
        <v-card>
            <v-card-title>
                Statistics
            </v-card-title>

            <v-row class="mx-2">
                <v-col md="8">
                    <div class="outlined pa-2">
                        <div>Progress</div>
                        <div class="d-flex align-center justify-space-around">
                            <v-progress-linear class="mr-2" :value="project.progress" striped height="6" :color="computeProgressColor"></v-progress-linear>
                            <span>{{ project.progress }}%</span> 
                        </div>

                    </div>
                </v-col>

                <v-col md="4">
                    <div class="outlined pa-2">
                        <div>Issues</div>
                        <pie-chart :styles="{ position: 'relative', height: '150px' }" :data="chartData" :options="options"></pie-chart>
                    </div>
                </v-col>
                
            </v-row>

            

            <!-- Progress bar -->
            <!-- <v-row >
                <v-col>

                    
                    <div :style="{border: '1px solid rgba(0, 0, 0, 0.38)', borderRadius: '4px'}">
                        <v-col md="5">
                            <v-progress-linear :value="project.progress" striped height="6" :color="computeProgressColor"></v-progress-linear>
                        </v-col>
                        <v-col md="1" class="py-0">
                            {{ project.progress }}%
                        </v-col>
                    </div>

                </v-col>


                <v-col md="4">
                    <span :style="{ backgroundColor: 'white' }">asd</span> 
                    <v-row :style="{border: '1px solid rgba(0, 0, 0, 0.38)', borderRadius: '4px'}">
                        
                    </v-row> 
                    <v-row :style="{border: '1px solid rgba(0, 0, 0, 0.38)', borderRadius: '4px'}">
                        <v-col>
                            <pie-chart :styles="{ position: 'relative', height: '150px' }" :data="chartData" :options="options"></pie-chart>
                        </v-col>
                        
                    </v-row>
                </v-col>
            </v-row> -->
        </v-card>
    </v-container>
  </div>
</template>

<script>
import isNil from 'lodash/isNil';
import { loadingOn, loadingOff, alert } from "@/utils/globals"
import {getDaysUntilNow} from "@/utils/date"

import { getProgressColor } from "@/utils/progress"
import PieChart from "@/components/ui/PieChart.vue"
import ProjectCard from "@/components/project/ProjectCard"


export default {
    name: "Project",
    components: {
        PieChart,
        ProjectCard
    },  
    data: () => ({
        chartData: {
            labels: [
                'Opened',
                'Closed',
                'On hold'
            ],
            datasets: [{
                label: 'My First Dataset',
                data: [300, 50, 100],
                backgroundColor: [
                    'rgb(255, 99, 132)',
                    'rgb(54, 162, 235)',
                    'rgb(255, 205, 86)'
                ],
                hoverOffset: 4
            }]
        },
        options: {
            // responsive: true,
            maintainAspectRatio:false
        }
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
                })
                .catch(err => {
                    this.$log.error(err)
                    alert("error", "Cannot load project")
                })
        },

        // Handlers
        onOpenProjectDialogEditMode(){
            this.$log.debug("called")
            this.$store.dispatch('appStore/setProjectDialogProperties', {
                show: true,
                data: {
                    mode: "edit",
                    projectId: this.project.id
                }
            })
        },

        //Helpers
        getDaysUntilNow(){
            const diffInDaysRounded = getDaysUntilNow(this.project.created)
            let temp = ""
            if(diffInDaysRounded === 0){
                temp = "(today)"
            } else if(diffInDaysRounded === 1){
                temp = "(yesterday)"
            } else {
                temp = `(${diffInDaysRounded} ${ diffInDaysRounded > 1 ? 'days' : 'day' } ago)`
            }
            return temp
        }
    },
    computed: {
        computeProgressColor(){
            return getProgressColor(this.project.progress)
        },

        project(){
            return this.$store.getters['projectsStore/projectById'](this.getRouteProjectId())
        }
    }
    
}
</script>

<style>
.outlined{
    border: 1px solid rgba(0, 0, 0, 0.38);
    border-radius: 4px
}
</style>