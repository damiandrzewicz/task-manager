export function getProgressColor(value){
    const between = function(x, min, max){return x >= min && x <= max;};
    const v = value;
    if(between(v, 0, 20)){return "red";}
    else if(between(v, 21, 40)){return "yellow";}
    else if(between(v, 41, 60)){return "orange";}
    else if(between(v, 61, 80)){return "grey";}
    else if(between(v, 81, 99)){return "blue";}
    else{return "green";}
}