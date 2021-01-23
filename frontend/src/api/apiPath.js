export class ApiPath {
    constructor(){
        this.uri = "http://localhost:3000/api";
    }

    buildApiPath(collection, version, uri = null){
        if(uri)
            this.uri = uri;
        return `${this.uri}/${version}/${collection}`;
    }
}