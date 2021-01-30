<template>
    <div>
        <div v-for="project in projects" :key="project.id">
            <ProjectCard :project="project"/>
        </div>
    </div>
</template>

<script>
import ProjectCard from "@/components/project/ProjectCard.vue"

export default {
    name: "ProjectsList",
    props: {
        projects: {
            type: Array,
            default: () => ({})
        }
    },
    components:{
        ProjectCard
    }
}
</script>

<style>

</style>