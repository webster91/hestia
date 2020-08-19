import axios from "axios";
import {FAILURE, REQUEST, SUCCESS} from "../../utils/action-type.util";

export const ACTION_TYPES = {
    FETCH_USER: 'user/FETCH_USER',
    LINK_ADDRESSES: 'user/LINK_ADDRESSES',
    RESET_LINK_ADDRESSES: 'user/RESET_LINK_ADDRESSES',
    LOGIN: 'user/LOGIN',
    LOGOUT: 'user/LOGOUT',
}

const initialState = {
    loading: false,
    showModalLogin: true,
    loginError: false,
    errorMessage: null,
    user: {
        id: null,
        name: null,
        login: null,
        addressId: null,
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

export const linkAddressToUser = (addressId, telephone) => async (dispatch, getState) => {
    await dispatch({
        type: ACTION_TYPES.LINK_ADDRESSES,
        payload: axios.post('/api/user/address', {addressId, telephone})
    });
}

export const resetFormLinkAddresses = () => async (dispatch, getState) => {
    await dispatch({
        type: ACTION_TYPES.RESET_LINK_ADDRESSES,
    });
};

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
        case REQUEST(ACTION_TYPES.LINK_ADDRESSES):
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
                    addressId: null,
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
        case FAILURE(ACTION_TYPES.LINK_ADDRESSES):
            return {
                ...initialState,
                showModalLogin: true,
                errorMessage: action.payload?.response?.data?.message,
            };
        case FAILURE(ACTION_TYPES.FETCH_USER):
        case FAILURE(ACTION_TYPES.LOGOUT):
            return {
                ...state,
                isAuthenticated: false
            }
        case ACTION_TYPES.RESET_LINK_ADDRESSES:
            return {
                ...state,
                errorMessage: initialState.errorMessage
            };
        default:
            return state;
    }
}

export const getUser = state => state.auth.user;
export const isAuthenticated = state => state.auth.isAuthenticated;
export const showModalLogin = state => state.auth.showModalLogin;
export const loginError = state => state.auth.loginError;
export const errorMessage = state => state.auth.errorMessage;
export const roles = state => state.auth.user.roles;