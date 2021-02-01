export class Project{
    id = null;
    name = null;
    description = null;
    created = null;
    progress = 0;
    parentId = null;
    subProjectsIds = [];
    abbreveation = null

    constructor(data = null){
        if(data){
            Object.assign(this, data);
        } 
    }

    deepCopy(){
        let copy = new Project();
        return Object.assign(copy, JSON.parse(JSON.stringify(this)));
    }
}