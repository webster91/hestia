import axios from "axios";
import {FAILURE, REQUEST, SUCCESS} from "../../utils/action-type.util";

export const ACTION_TYPES = {
    FETCH_USER: 'FETCH_USER',
    LOGIN: 'LOGIN',
    LOGOUT: 'LOGOUT',
}

const initialState = {
    loading: false,
    showModalLogin: true,
    loginError: false,
    user: {
        id: null,
        name: null,
        login: null,
        roles: [],
    },
    isAuthenticated: false
}

export const fetchUser = () => async (dispatch, getState) => {
    await dispatch({
        type: ACTION_TYPES.FETCH_USER,
        payload: axios.get('api/user')
    });
}

export const login = (telephone, password) => async dispatch => {
    const data = `telephone=${encodeURIComponent(telephone)}&password=${encodeURIComponent(password)}`;
    await dispatch({
        type: ACTION_TYPES.LOGIN,
        payload: axios.post('api/perform_login', data)
    });
    dispatch(fetchUser());
}

export const logout = () => async dispatch => {
    await dispatch({
        type: ACTION_TYPES.LOGOUT,
        payload: axios.post('api/logOut')
    });
    window.location.href = '/';
}

export default function user(state = initialState, action) {
    switch (action.type) {
        case REQUEST(ACTION_TYPES.FETCH_USER):
        case REQUEST(ACTION_TYPES.LOGIN):
        case REQUEST(ACTION_TYPES.LOGOUT):
            return {
                ...state,
                loading: true,
                error: null
            };
        case SUCCESS(ACTION_TYPES.FETCH_USER):
            return {
                ...state,
                user: action.payload.data,
                isAuthenticated: true,
                loading: true
            };
        case SUCCESS(ACTION_TYPES.LOGOUT):
            return {
                ...state,
                user: {
                    id: null,
                    name: null,
                    login: null,
                    roles: [],
                },
                isAuthenticated: false,
                loading: false
            };
        case SUCCESS(ACTION_TYPES.LOGIN):
            return {
                ...state,
                loading: false,
                loginError: false,
                showModalLogin: false,
                loginSuccess: true,
            };
        case FAILURE(ACTION_TYPES.LOGIN):
            return {
                ...initialState,
                errorMessage: action.payload,
                showModalLogin: true,
                loginError: true,
            };
        case FAILURE(ACTION_TYPES.FETCH_USER):
        case FAILURE(ACTION_TYPES.LOGOUT):
            return {
                ...state,
                isAuthenticated: false
            }
        default:
            return state;
    }
}

export const getUser = state => state.auth.user;
export const isAuthenticated = state => state.auth.isAuthenticated;
export const showModalLogin = state => state.auth.showModalLogin;
export const loginError = state => state.auth.loginError;