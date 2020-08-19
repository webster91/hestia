import {combineReducers} from "redux";
import auth from "./auth.reducer";
import register from "./register.reducer";
import {loadingBarReducer as loadingBar} from 'react-redux-loading-bar';
import address from "./address.reducer";
import receipt from "./receipt.reducer";

const reducer = combineReducers({
    auth,
    address,
    register,
    receipt,
    loadingBar,
});

export default reducer;