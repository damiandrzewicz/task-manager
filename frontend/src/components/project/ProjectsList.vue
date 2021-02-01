<template>
    <v-card>
        <v-data-table
            :headers="headers"
            :items="projects"
            :item-class="rowClasses"
            :single-expand="false"
            :expanded.sync="expanded"
            item-key="id"
            show-expand
            :hide-default-footer="hideFooter"
        >
            <!-- Format date created  -->
            <template v-slot:item.created="{ item }">
                {{ formatCreated(item.created) }}
            </template>

            <!-- Format current progress -->
            <template v-slot:item.progress="{ item }">
                <v-progress-circular
                    :rotate="-90"
                    :size="40"
                    :value="item.progress"
                    :color="getProgressColor(item.progress)"
                >
                    {{item.progress}}%
                </v-progress-circular>
            </template>

            <!-- Item actions -->
            <template v-slot:item.actions="{ item }">
                <v-icon
                    small
                    class="mr-2"
                    @click="onEditProject(item)"
                >
                    mdi-pencil
                </v-icon>
                <v-icon
                    small
                    class="mr-2"
                    @click="onOpenProject(item)"
                >
                    mdi-card-search
                </v-icon>
                <v-icon
                    small
                    @click="onDeleteProject(item)"
                >
                    mdi-delete
                </v-icon>
            </template>

            <template v-slot:item.data-table-expand="{ item, isExpanded, expand }">
                <v-btn icon @click="expand(true)" v-if="item.subProjectsIds.length && !isExpanded"><v-icon>mdi-chevron-down</v-icon></v-btn>
                <v-btn icon @click="expand(false)" v-if="item.subProjectsIds.length && isExpanded"><v-icon>mdi-chevron-up</v-icon></v-btn>
            </template>

            <!-- Expanded item -->
            <template v-slot:expanded-item="{ headers, item }">
                <td :colspan="headers.length">
                    <ProjectsList :projects="getSubProjects(item.id)" :hideFooter="true"/>
                </td>
            </template>

        </v-data-table>
    </v-card>
</template>

<script>
import { formatDate } from "@/utils/date"
import { getProgressColor } from "@/utils/progress"

import ProjectsList from "@/components/project/ProjectsList"

export default {
    name: "ProjectsList",
    components:{
        ProjectsList
    },
    props: {
        projects: {
            type: Array,
            default: () => ({})
        },
        hideFooter: {
            type: Boolean,
            fedault: false
        }
    },
    data: () => ({
        expanded: [],
        headers: [
            {text: 'Project', align: 'start', value: 'name'},
            {text: 'Abbr', value: 'abbreveation'},
            {text: 'Created', value: 'created'},
            {text: 'Subprojects count', value: 'subProjectsIds.length'},
            {text: 'Progress', value: 'progress'},
            {text: 'Actions', value: 'actions', sortable: false},
            {text: '', value: 'data-table-expand'},
        ]
    }),

    computed: {
    },
    methods: {
        // Utils
        formatCreated(created){
            return formatDate(new Date(created), 'dd-MM-yyyy kk:mm:ss');
        },
        getProgressColor(value){
            return getProgressColor(value)
        },
        isRowExpanded(id){
            return this.expanded.find(i => i.id === id)
        },
        rowClasses(item){
            if(this.isRowExpanded(item.id)){
                return "secondary white--text"
            }
        },

        // API
        getSubProjects(parentId){
            return this.$store.getters["projectsStore/subProjects"](parentId);
        },

        // Handlers
        onEditProject(item){
            this.$log.debug("called")
            this.$store.dispatch('appStore/setProjectDialogProperties', {
                show: true,
                data: {
                    mode: "edit",
                    projectId: item.id
                }
            })
        },
        onOpenProject(item){
            this.$log.debug("called")
            this.$router.push(`/projects/${item.id}`);
        },
        onDeleteProject(item){
            this.$log.debug("called")
            this.$store.dispatch("appStore/setLoading", true)
            this.$store.dispatch("projectsStore/deleteProject", item.id)
                .then(() => {
                    this.$store.dispatch("appStore/setLoading", false)
                })
                .catch(err => {
                    this.$log.error(err.response.data);
                    this.$store.dispatch("errorStore/showError", { type: "error", message: "Cannot add project, check console!" })
                })
        }
    }
}
</script>

<style>

</style>