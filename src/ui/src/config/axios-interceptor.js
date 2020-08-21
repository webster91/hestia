import axios from 'axios';
import {SERVER_API_URL, TOKEN_PREFIX} from "./constants";
import {AUTH_TOKEN_KEY} from "../store/reducers/auth.reducer";

const TIMEOUT = 1 * 60 * 1000;
axios.defaults.timeout = TIMEOUT;
axios.defaults.baseURL = SERVER_API_URL;

const setupAxiosInterceptors = () => {
    const onRequestSuccess = config => {
        const token = localStorage.getItem(AUTH_TOKEN_KEY);
        console.log(token)
        if (token) {
            config.headers.Authorization = `${TOKEN_PREFIX} ${token}`;
        }
        return config;
    };
    const onResponseSuccess = response => response;
    const onResponseError = err => {
        if (err.status === 404) {
            window.history.push('');
        }

        return Promise.reject(err);
    };
    axios.interceptors.request.use(onRequestSuccess);
    axios.interceptors.response.use(onResponseSuccess, onResponseError);
};

export default setupAxiosInterceptors;
