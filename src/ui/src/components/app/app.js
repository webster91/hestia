import React from "react";
import {BrowserRouter as Router, Switch,} from "react-router-dom";
import ErrorBoundaryRoute from "../error-boundary-route";
import Header from "../header";
import {ErrorPage, LoginPage, MainPage, ReceiptPage, RegisterPage, StatisticPage} from "../pages";
import {
    ERROR_URL,
    LOGIN_URL,
    MAIN_URL,
    OTHER_URL,
    REGISTER_URL,
    USER_RECEIPT_URL,
    USER_STATISTIC_URL
} from "../../config/url";

const App = () => {
    return (
        <Router>
            <Header/>
            <Switch>
                <ErrorBoundaryRoute exact path={MAIN_URL} component={MainPage}/>
                <ErrorBoundaryRoute exact path={LOGIN_URL} component={LoginPage}/>
                <ErrorBoundaryRoute exact path={REGISTER_URL} component={RegisterPage}/>
                <ErrorBoundaryRoute exact path={USER_STATISTIC_URL} component={StatisticPage}/>
                <ErrorBoundaryRoute exact path={USER_RECEIPT_URL} component={ReceiptPage}/>
                <ErrorBoundaryRoute path={[ERROR_URL, OTHER_URL]} component={ErrorPage}/>
            </Switch>
        </Router>
    );
}

export default App