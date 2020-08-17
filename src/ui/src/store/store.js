import {applyMiddleware, compose, createStore} from 'redux';
import thunkMiddleware from 'redux-thunk';
import reducer from "./reducers";
import promiseMiddleware from 'redux-promise-middleware';
import {loadingBarMiddleware} from 'react-redux-loading-bar'

const composeEnhancers = window['__REDUX_DEVTOOLS_EXTENSION_COMPOSE__'] || compose;


const defaultMiddlewares = [
    thunkMiddleware,
    promiseMiddleware,
    loadingBarMiddleware(),
];

const store = createStore(reducer, composeEnhancers(applyMiddleware(...defaultMiddlewares)));

export default store;