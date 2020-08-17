import {FAILURE, REQUEST, SUCCESS} from "../../utils/action-type.util";
import axios from 'axios';
import {login} from "./auth.reducer";

export const ACTION_TYPES = {
    CREATE_ACCOUNT: 'register/CREATE_ACCOUNT',
    RESET: 'register/RESET',
};

const initialState = {
    loading: false,
    registrationSuccess: false,
    registrationFailure: false,
    errorMessage: null,
};

export default function register(state = initialState, action) {
    switch (action.type) {
        case REQUEST(ACTION_TYPES.CREATE_ACCOUNT):
            return {
                ...state,
                loading: true,
            };
        case FAILURE(ACTION_TYPES.CREATE_ACCOUNT):
            return {
                ...initialState,
                registrationFailure: true,
                errorMessage: action.payload?.response?.data?.message,
            };
        case SUCCESS(ACTION_TYPES.CREATE_ACCOUNT):
            return {
                ...initialState,
                registrationSuccess: true,
            };
        case ACTION_TYPES.RESET:
            return {
                ...initialState,
            };
        default:
            return state;
    }
}

export const handleRegister = (username, email, telephone, password) => async dispatch => {
    await dispatch({
        type: ACTION_TYPES.CREATE_ACCOUNT,
        payload: axios.post('api/user', {username, email, telephone, password})
    });
    dispatch(login(telephone, password))
};

export const resetForm = () => async (dispatch, getState) => {
    await dispatch({
        type: ACTION_TYPES.RESET,
    });
};

export const errorMessage = state => state.register.errorMessage;
export const registrationFailure = state => state.register.registrationFailure;
export const registrationSuccess = state => state.register.registrationSuccess;