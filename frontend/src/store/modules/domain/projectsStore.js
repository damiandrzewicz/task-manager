import isNull from 'lodash/isNull';
import projectsApi from "@/api/projectsApi"
import Vue from 'vue'
import { Project } from "@/model/Project"

//to handle state
const state = {
    projects: [],
    loading: false,
    errored: false
}

//to handle state
const getters = {
    projects: state => state.projects,

    rootProjects: state => {
        return state.projects.filter(p => isNull(p.parentId))
    },

    // States 
    isLoading: state => state.loading,
    isErrored: state => state.errored,
}

//to handle actions
const actions = {
    initBeforeLoad({commit}){
        commit("SET_LOADING", true);
        commit("SET_ERRORED", false);
    },

    loadProjects({ commit, dispatch }){
        dispatch("initBeforeLoad");
        projectsApi.getProjects()
            .then(projects => { 
                var mappedProjects = projects.map(p => new Project(p));
                Vue.$log.debug(`loaded ${mappedProjects.length} projects`);
                commit("SET_PROJECTS", mappedProjects);
            })
            .catch(error => {
                Vue.$log.error("err: " + error);
                commit("SET_ERRORED", true)
            })
            .finally( () => {
                commit("SET_LOADING", false)
            })
    },

    addProject({commit}, payload){
        Vue.$log.debug(`add project: ${JSON.stringify(payload.project)}`)
        return projectsApi.addProject(payload.project)
            .then(p => {
                let project = new Project(p);
                Vue.$log.debug(`added project: ${JSON.stringify(project)}`);
                commit("ADD_PROJECT", project);
            })
    }

}

//to handle mutations
const mutations = {
    SET_PROJECTS(state, projects){
        state.projects = projects
    },
    ADD_PROJECT(state, project){
        state.projects.push(project);
    },
    REMOVE_PROJECT(state, project){
        const index = state.projects.indexOf(project);
        if(index >= -1){
            state.projects
        }
    },
    SET_LOADING(state, value){
        state.loading = value;
    },
    SET_ERRORED(state, value){
        state.errored = value;
    }
}

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
};