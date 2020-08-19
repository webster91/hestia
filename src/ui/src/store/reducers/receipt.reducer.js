import {FAILURE, REQUEST, SUCCESS} from "../../utils/action-type.util";
import axios from "axios";

export const ACTION_TYPES = {
    GET_RECEIPTS: 'receipt/GET_RECEIPT',
};

const initialState = {
    receipts: [],
    loading: false,
    errorMessage: null,
};

export const fetchReceipts = () => async (dispatch, getState) => {
    await dispatch({
        type: ACTION_TYPES.GET_RECEIPTS,
        payload: axios.get('api/receipt')
    });
}

export const fetchReceiptByAddress = (addressId) => async (dispatch, getState) => {
    await dispatch({
        type: ACTION_TYPES.GET_RECEIPTS,
        payload: axios.get(`/api/address/${addressId}/receipt`)
    });
}

export default function receipt(state = initialState, action) {
    switch (action.type) {
        case REQUEST(ACTION_TYPES.GET_RECEIPTS):
            return {
                ...state,
                loading: true,
            };
        case SUCCESS(ACTION_TYPES.GET_RECEIPTS):
            return {
                ...initialState,
                receipts: action.payload,
            };
        case FAILURE(ACTION_TYPES.GET_RECEIPTS):
            return {
                ...initialState,
                errorMessage: action.payload?.response?.data?.message,
            };
        default:
            return state;
    }
}

export const errorMessage = state => state.receipt.errorMessage;
export const receipts = state => state.receipt.receipts;