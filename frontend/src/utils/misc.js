export function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

export function trim(data){
    return data.trim();
}

export function capitalize(string = '') {
    return [...string].map((char, index) => index ? char : char.toUpperCase()).join('');
}