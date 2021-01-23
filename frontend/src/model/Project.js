export class Project{
    constructor(data = null){
        this.id = null;
        this.name = null;
        this.description = null;
        this.created = null;
        this.progress = 0;
        this.parentId = null;
        this.subProjectsIds = []
        

        if(data){
            Object.assign(this, data);
        } 
    }

    deepCopy(){
        let copy = new Project();
        return Object.assign(copy, JSON.parse(JSON.stringify(this)));
    }
}