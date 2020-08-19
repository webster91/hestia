import {FAILURE, REQUEST, SUCCESS} from "../../utils/action-type.util";
import axios from "axios";

export const ACTION_TYPES = {
    GET_ADDRESSES: 'address/GET_ADDRESSES',
    GET_LINK_ADDRESSES: 'address/GET_LINK_ADDRESSES',
};

const initialState = {
    address: [],
    loading: false,
    errorMessage: null,
};

export const fetchAddresses = () => async (dispatch, getState) => {
    await dispatch({
        type: ACTION_TYPES.GET_ADDRESSES,
        payload: axios.get('/api/address')
    });
}

export default function address(state = initialState, action) {
    switch (action.type) {
        case REQUEST(ACTION_TYPES.GET_ADDRESSES):
            return {
                ...state,
                loading: true,
            };
        case SUCCESS(ACTION_TYPES.GET_ADDRESSES):
            return {
                ...initialState,
                address: action.payload.data,
            };
        case FAILURE(ACTION_TYPES.GET_ADDRESSES):
            return {
                ...initialState,
                errorMessage: action.payload?.response?.data?.message,
            };
        default:
            return state;
    }
}

export const getAddress = state => state.address.address;
export const errorMessage = state => state.address.errorMessage;


