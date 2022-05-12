import axios from "axios";

const request = axios.create({
    baseURL: 'http://localhost:8081',
    // baseURL: 'http://42.192.235.175:8081',
    timeout: 5000
})

export default request