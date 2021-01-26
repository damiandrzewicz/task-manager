<template>
  <v-card flat class="my-3 px-3">
      <v-form ref="form">
        <v-row dense class="mx-1 pr-5 d-flex align-center">

            <!-- Name label  -->
            <v-col md="4" class="my-0 py-0">
                <v-text-field v-model.trim="project.name" required label="Enter Project Name"></v-text-field>
            </v-col>

            <!-- Description label -->
            <v-col md="6" class="my-0 py-0">
                    <v-text-field v-model="project.description" label="Enter Project Description"></v-text-field>
            </v-col>

            

            <!-- Confirm button  -->
            <v-col md="2">
                <v-btn :disabled="!project.name" @click="odAddProject">
                    Add Project
                    <v-icon class="mx-3">mdi-folder-plus</v-icon>
                </v-btn>
            </v-col>
        </v-row>

      </v-form>
  </v-card>
</template>

<script>
import { Project } from "@/model/Project"  


export default {
    name: "AddProjectCard",
    props: {
        parentId:{
            default: null,
            type: Number
        }   
    },
    data: () => ({
        project: new Project()
    }),
    created(){
        this.project.parentId = this.parentId;
    },
    methods: {  
        odAddProject(){
            this.errored = false,
            this.$refs.form.validate();
            this.$store.dispatch("projectsStore/addProject", {project: this.project.deepCopy()})
                .catch(err => {
                    this.$log.error(err.response.data);
                    this.$store.dispatch("appStore/showAlert", { type: "error", message: "Cannot add project!" })
                })
            
            this.$refs.form.reset();
        }
    },
    computed: {
        isErrored(){
            return this.errored
        }
    }
}
</script>

<style>

</style>