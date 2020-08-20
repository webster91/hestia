import {FAILURE, REQUEST, SUCCESS} from "../../utils/action-type.util";
import axios from "axios";

export const ACTION_TYPES = {
    GET_TICKETS: 'ticket/GET_TICKETS',
    SAVE_TICKET: 'ticket/SAVE_TICKET',
    RESET: 'ticket/RESET',
};

const initialState = {
    ticket: [],
    errorMessage: null,
    createSuccess: false,
    createFailure: false,
};

export const fetchUserTickets = (addressId) => async (dispatch, getState) => {
    await dispatch({
        type: ACTION_TYPES.GET_TICKETS,
        payload: axios.get(`/api/address/${addressId}/receipt`)
    });
}

export const saveUserTicket = (userId, header, description) => async (dispatch, getState) => {
    await dispatch({
        type: ACTION_TYPES.SAVE_TICKET,
        payload: axios.post(`/api/ticket`, {userId, header, description})
    });
}

export const reset = () => ({
    type: ACTION_TYPES.RESET,
});


export default function ticket(state = initialState, action) {
    switch (action.type) {
        case REQUEST(ACTION_TYPES.GET_TICKETS):
        case REQUEST(ACTION_TYPES.SAVE_TICKET):
            return {
                ...state,
                loading: true,
            };
        case SUCCESS(ACTION_TYPES.SAVE_TICKET):
            return {
                ...state,
                createSuccess: true,
            };
        case SUCCESS(ACTION_TYPES.GET_TICKETS):
            return {
                ...state,
                ticket: action.payload,
            };
        case FAILURE(ACTION_TYPES.GET_TICKETS):
            return {
                ...initialState,
                errorMessage: action.payload?.response?.data?.message,
            };
        case FAILURE(ACTION_TYPES.SAVE_TICKET):
            return {
                ...initialState,
                errorMessage: action.payload?.response?.data?.message,
                createFailure: true,
            };
        case ACTION_TYPES.RESET:
            return {
                ...initialState,
            };
        default:
            return state;
    }
}

export const errorMessage = state => state.ticket.errorMessage;
export const getTickets = state => state.ticket.ticket;
export const createFailure = state => state.ticket.createFailure;
export const createSuccess = state => state.ticket.createSuccess;