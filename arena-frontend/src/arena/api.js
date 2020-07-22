import { api } from './enums';

export const findEntities = (controller, example) => {
    let url = new URL(`${api}/${controller}/search`);
    let params = {};
    Object.keys(example).forEach(
        key => {
            if (example[key] !== undefined)
                params[key] = example[key]
        });
    url.search = new URLSearchParams(params
    ).toString();
    return fetch(url, {
        method: 'GET', // or 'PUT'
        headers: {
            'Content-Type': 'application/json'
        }
    })
}
