<template>
    <v-form ref="form">
        <v-card >
            <v-card-title>
                <slot name="title"></slot>
            </v-card-title>

            <v-card-text>
                <v-row class="mx-3">
                    <v-col md="6">
                        <v-text-field 
                            dense 
                            :readonly="isViewMode()" 
                            outlined 
                            prepend-icon="mdi-folder" 
                            :rules="validation.rules.name"
                            label="Name" 
                            v-model="tempProject.name"
                        >
                        </v-text-field>

                        <v-text-field 
                            dense 
                            readonly 
                            outlined 
                            prepend-icon="mdi-account" 
                            v-if="isViewMode()"
                            label="Creator" 
                            value="test owner"
                        >
                        </v-text-field>

                        <v-text-field 
                            dense 
                            readonly 
                            outlined 
                            prepend-icon="mdi-calendar" 
                            v-if="isViewMode()"
                            label="Created" 
                            :value="computeCreated" 
                            :suffix="computeDaysUntilNow"
                        >
                        </v-text-field>

                    </v-col>

                    <v-col md="6">
                        <v-text-field 
                            dense 
                            readonly 
                            outlined 
                            prepend-icon="mdi-folder-multiple" 
                            v-if="isViewMode()"
                            label="Subprojects number" 
                            :value="tempProject.subProjectsIds.length"
                        >
                        </v-text-field>

                        <v-text-field 
                            dense 
                            :readonly="isViewMode()"
                            outlined 
                            prepend-icon="mdi-label" 
                            label="Abbreveation" 
                            v-model="tempProject.abbreveation"
                            :rules="validation.rules.abbreveation"
                        >
                        </v-text-field>

                    </v-col>
                </v-row>

                <v-row class="mx-3"> 
                    <v-col>
                        <v-textarea 
                            dense 
                            :readonly="isViewMode()" 
                            outlined 
                            label="Description" 
                            v-model="tempProject.description"
                            
                        >
                        </v-textarea>
                    </v-col>
                </v-row>
            </v-card-text>

            <v-card-actions v-if="!isViewMode()">
                <v-spacer></v-spacer>
                <v-btn @click="onOk">Ok</v-btn>
                <v-btn @click="onCancel">Cancel</v-btn>
            </v-card-actions>
        </v-card>
    </v-form>
</template>

<script>
import { Project } from "@/model/Project"
import {formatDate, getDaysUntilNow} from "@/utils/date"


export default {
    name: "ProjectCard",
    props: {
        project: {
            type: Project,
            default: null
        },
        mode: {
            type: String,
            default: 'view'
        }
    },
    created(){
        this.copyProjectTempObject()
    },
    data: function(){
        return {
            valid: true,
            validation: {
                rules: {
                    name: [
                        v => !!v || 'Name is required',
                    ],
                    abbreveation: [
                        v =>  !this.abbreveations().includes(v) || 'Abbreveation already exists!',
                    ]
                }
            },
            tempProject: null
        }
    },
    methods: {
        // Helpers
        isViewMode(){
            return this.mode === 'view'
        },
        isEditMode(){
            return this.mode === 'edit'
        },
        isCreateMode(){
            return this.mode === 'create'
        },
        copyProjectTempObject(){
            this.tempProject = this.$props.project.deepCopy()
        },

        // Handlers
        onOk(){
            if(!this.validate()){
                return
            }
            this.$emit('eventOk', this.tempProject)
        },
        onCancel(){
            this.$emit('eventCancel')
        },

        // Form validations
        validate () {
            return this.$refs.form.validate()
        },
        reset () {
            this.$refs.form.reset()
        },
        resetValidation () {
            this.$refs.form.resetValidation()
        },

        abbreveations(){
            let v = this.$store.getters['projectsStore/abbreveations']
            this.$log.debug(`abbr: ${JSON.stringify(v)}`)
            return v
        },

        test(){
            return true
        }
    },
    computed: {
        computeCreated(){
            return formatDate(new Date(this.tempProject.created), 'dd-MM-yyyy kk:mm:ss');
        },
        computeDaysUntilNow(){
            const diffInDaysRounded = getDaysUntilNow(this.tempProject.created)
            let temp = ""
            if(diffInDaysRounded === 0){
                temp = "(today)"
            } else if(diffInDaysRounded === 1){
                temp = "(yesterday)"
            } else {
                temp = `(${diffInDaysRounded} ${ diffInDaysRounded > 1 ? 'days' : 'day' } ago)`
            }
            return temp
        },

    },
    watch: {
        project(){
            this.copyProjectTempObject()
        }
    }
}
</script>

<style>

</style>