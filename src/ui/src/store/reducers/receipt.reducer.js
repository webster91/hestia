import {FAILURE, REQUEST, SUCCESS} from "../../utils/action-type.util";
import axios from "axios";

export const ACTION_TYPES = {
    GET_RECEIPT: 'receipt/GET_RECEIPT',
};

const initialState = {
    receipts: [],
    loading: false,
    errorMessage: null,
};

export const fetchReceipt = () => async (dispatch, getState) => {
    await dispatch({
        type: ACTION_TYPES.GET_RECEIPT,
        payload: axios.get('api/user')
    });
}


export default function receipt(state = initialState, action) {
    switch (action.type) {
        case REQUEST(ACTION_TYPES.GET_RECEIPT):
            return {
                ...state,
                loading: true,
            };
        case SUCCESS(ACTION_TYPES.GET_RECEIPT):
            return {
                ...initialState,
                receipts: action.payload,
            };
        case FAILURE(ACTION_TYPES.GET_RECEIPT):
            return {
                ...initialState,
                errorMessage: action.payload?.response?.data?.message,
            };
        default:
            return state;
    }
}

export const errorMessage = state => state.register.errorMessage;
export const receipts = state => state.register.receipts;