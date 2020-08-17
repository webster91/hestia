import {combineReducers} from "redux";
import auth from "./auth.reducer";
import register from "./register.reducer";
import {loadingBarReducer as loadingBar} from 'react-redux-loading-bar';

const reducer = combineReducers({
    auth,
    register,
    loadingBar,
});

export default reducer;