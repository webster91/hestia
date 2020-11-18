import React, {useEffect} from "react";
import {BrowserRouter as Router, Switch,} from "react-router-dom";
import ErrorBoundaryRoute from "../error-boundary-route";
import Header from "../header";
import {ErrorPage, LoginPage, MainPage, ReceiptPage, RegisterPage, StatisticPage} from "../pages";
import {
    ADMIN_MONITORING_URL,
    ADMIN_USER_LINKING_URL,
    ERROR_URL,
    LOGIN_URL,
    MAIN_URL,
    OTHER_URL,
    REGISTER_URL,
    USER_RECEIPT_URL,
    USER_STATISTIC_URL,
    USER_TICKET_URL
} from "../../config/url";
import AdminMonitoringPage from "../pages/admin-monitoring-page";
import AdminLinkingPage from "../pages/admin-linking-page";
import CreateTicketPage from "../pages/create-ticket-page";
import {connect} from "react-redux";
import {fetchUser, isAuthenticated, roles} from "../../store/reducers/auth.reducer";
import {hasAnyAuthority} from "../../utils/auth.util";
import {ROLES} from "../../config/constants";


const App = (props) => {

    useEffect(() => {
        props.fetchUser();
    }, []);

    return (
        <Router>
            <Header/>
            <Switch>
                <ErrorBoundaryRoute exact path={MAIN_URL} component={MainPage}/>
                <ErrorBoundaryRoute exact path={LOGIN_URL} component={LoginPage}/>
                <ErrorBoundaryRoute exact path={REGISTER_URL} component={RegisterPage}/>
                <ErrorBoundaryRoute exact path={USER_STATISTIC_URL} component={StatisticPage}/>
                <ErrorBoundaryRoute exact path={USER_RECEIPT_URL} component={ReceiptPage}/>
                <ErrorBoundaryRoute exact path={ADMIN_USER_LINKING_URL} component={AdminLinkingPage}/>
                <ErrorBoundaryRoute exact path={ADMIN_MONITORING_URL} component={AdminMonitoringPage}/>
                <ErrorBoundaryRoute exact path={USER_TICKET_URL} component={CreateTicketPage}/>
                <ErrorBoundaryRoute path={[ERROR_URL, OTHER_URL]} component={ErrorPage}/>
            </Switch>
        </Router>
    );
}

const mapStateToProps = state => ({
    isAuthenticated: isAuthenticated(state),
    isAdmin: hasAnyAuthority(roles(state), [ROLES.ADMIN]),
});

const mapDispatchToProps = {fetchUser};

export default connect(mapStateToProps, mapDispatchToProps)(App);