<template>
  <div>
      <v-card flat class="my-4">
          <v-row class="mx-1">
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
  </div>
</template>

<script>
import { formatCreatedDate } from "@/utils/date"
import { getProgressColor } from "@/utils/progress"

import { Project } from "@/model/Project"

export default {
    name: "ProjectCard",
    props: {
        project: {
            type: Project
        }
    },
    data: () => ({
        showSubitems: false,
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
        }
    },
    methods: {
        onEditProject(){
            this.$log.debug("called")
        },

        onDeleteProject(){
            this.$log.debug("called")
        }
    }
}
</script>

<style>

</style>