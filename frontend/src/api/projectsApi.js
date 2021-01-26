import axios from 'axios';
import {ApiPath} from "@/api/apiPath"

class ProjectsApi{
    constructor(){
        this.host = new ApiPath().buildApiPath("projects", "v1");
    }

    async getProjects(){
        return axios.get(`${this.host}`).then(res => res.data);
    }

    async getRootProjects(){
        return axios.get(`${this.host}/root`).then(res => res.data);
    }

    async getSubProjects(id){
        return axios.get(`${this.host}/${id}/subprojects`).then(res => res.data);
    }

    async getProject(id){
        return axios.get(`${this.host}/${id}`, id).then(res => res.data);
    }

    async addProject(project){
        return axios.post(`${this.host}`, project).then(res => res.data);
    }

    async updateProject(project){
        return axios.put(`${this.host}`, project).then(res => res.data);
    }

    async deleteProject(id){
        return axios.delete(`${this.host}/${id}`).then(res => res.data);
    }
}

export default new ProjectsApi();