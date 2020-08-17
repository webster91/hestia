import React from "react";
import {BrowserRouter as Router, Switch,} from "react-router-dom";
import ErrorBoundaryRoute from "../error-boundary-route";
import Header from "../header";
import {ErrorPage, LoginPage, MainPage, ReceiptPage, RegisterPage, StatisticPage} from "../pages";

const App = () => {
    return (
        <Router>
            <Header/>
            <Switch>
                <ErrorBoundaryRoute exact path={"/"} component={MainPage}/>
                <ErrorBoundaryRoute exact path={"/login"} component={LoginPage}/>
                <ErrorBoundaryRoute exact path={"/register"} component={RegisterPage}/>
                <ErrorBoundaryRoute exact path={"/user/statistic"} component={StatisticPage}/>
                <ErrorBoundaryRoute exact path={"/user/receipt"} component={ReceiptPage}/>
                <ErrorBoundaryRoute path={["error", "/**"]} component={ErrorPage}/>
            </Switch>
        </Router>
    );
}

export default App