import {combineReducers} from "redux";
import auth from "./auth.reducer";
import register from "./register.reducer";
import {loadingBarReducer as loadingBar} from 'react-redux-loading-bar';
import address from "./address.reducer";
import receipt from "./receipt.reducer";
import ticket from "./ticket.reducer";

const reducer = combineReducers({
    auth,
    address,
    register,
    receipt,
    ticket,
    loadingBar,
});

export default reducer;