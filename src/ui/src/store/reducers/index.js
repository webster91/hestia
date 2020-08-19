import {combineReducers} from "redux";
import auth from "./auth.reducer";
import register from "./register.reducer";
import {loadingBarReducer as loadingBar} from 'react-redux-loading-bar';
import address from "./address.reducer";

const reducer = combineReducers({
    auth,
    address,
    register,
    loadingBar,
});

export default reducer;