import isNull from 'lodash/isNull';
import projectsApi from "@/api/projectsApi"
import Vue from 'vue'
import { Project } from "@/model/Project"

//to handle state
const state = {
    projects: []
}

//to handle state
const getters = {
    projects: state => state.projects,

    projectsSize: state => state.projects.length,

    projectById: state => id => state.projects.find(p => p.id === id),

    rootProjects: state => {
        return state.projects.filter(p => isNull(p.parentId))
    },

    subProjects: state => id => {
        return state.projects.filter(p => p.parentId === id)
    },
}

//to handle actions
const actions = {
    loadAllProjects({ commit }){
        return projectsApi.getProjects()
            .then(projects => { 
                let mappedProjects = projects.map(p => new Project(p));
                Vue.$log.debug(`loaded ${mappedProjects.length} projects`);
                commit("SET_PROJECTS", mappedProjects);
            })
    },
    loadProject({commit}, id){
        return projectsApi.getProject(id)
            .then(p => {
                let mapped = new Project(p)
                Vue.$log.debug(`loaded project`);
                commit("INSERT_OR_REPLACE_PROJECT", mapped);
            })
    },

    // loadRootProjects({commit}){
    //     return projectsApi.getRootProjects()
    //         .then(projects => {
    //             var mappedProjects = projects.map(p => new Project(p));
    //             Vue.$log.debug(`loaded ${mappedProjects.length} root projects`);
    //             mappedProjects.forEach(p => commit("ADD_PROJECT", p))
    //         })
    // },

    // loadSubProjects({commit}, id){
    //     return projectsApi.getSubProjects(id)
    //         .then(projects => {
    //             var mappedProjects = projects.map(p => new Project(p));
    //             Vue.$log.debug(`loaded ${mappedProjects.length} subprojects for project id=${id}`);
    //             mappedProjects.forEach(p => commit("ADD_PROJECT", p))
                
    //         })
    // },

    addProject({commit}, payload){
        Vue.$log.debug(`add project: ${JSON.stringify(payload.project)}`)
        return projectsApi.addProject(payload.project)
            .then(p => {
                let project = new Project(p);
                Vue.$log.debug(`added project: ${JSON.stringify(project)}`);
                commit("ADD_PROJECT", project);
            })
    },

    deleteProject({commit}, id){
        Vue.$log.debug(`delete project: id=${id}`)
        return projectsApi.deleteProject(id)
            .then(() => {
                Vue.$log.debug(`deleted project: id=${id}`)
                commit("DELETE_PROJECT", id)
            })
    }

}

//to handle mutations
const mutations = {
    SET_PROJECTS(state, projects){
        state.projects = projects
    },
    ADD_PROJECT(state, project){
        //if(!state.projects.find(p => p.id === project.id)){
            state.projects.push(project);
        //}
    },
    INSERT_OR_REPLACE_PROJECT(state, project){
        const index = state.projects.findIndex(p => p.id === project.id)
        if(index !== -1){
            state.projects[index] = project
        } else {
            state.projects.push(project);
        }
    },
    DELETE_PROJECT(state, id){
        state.projects = state.projects.filter(p => p.id !== id)
    },
}

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
};