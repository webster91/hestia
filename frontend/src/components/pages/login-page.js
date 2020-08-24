import React, {useEffect, useState} from 'react';
import Login from "../login/login";
import {connect} from "react-redux";
import {isAuthenticated, login, loginError, showModalLogin} from "../../store/reducers/auth.reducer";
import {Redirect} from "react-router-dom";
import {MAIN_URL} from "../../config/url";

const LoginPage = (props) => {
    const [showModal, setShowModal] = useState(props.showModal);

    useEffect(() => {
        setShowModal(true);
    }, []);

    const handleLogin = (telephone, password) => {
        props.login(telephone, password);
    }

    const handleClose = () => {
        setShowModal(false);
        props.history.push('/');
    };

    const {location} = props;
    const {from} = (location.state) || {from: {pathname: MAIN_URL, search: location.search}};
    if (props.isAuthenticated) {
        return <Redirect to={from}/>;
    }
    return (<Login showModal={showModal} handleLogin={handleLogin} handleClose={handleClose}
                   loginError={props.loginError}/>)
}


const mapStateToProps = (state) => ({
    isAuthenticated: isAuthenticated(state),
    showModal: showModalLogin(state),
    loginError: loginError(state),
});

const mapDispatchToProps = {login};

export default connect(mapStateToProps, mapDispatchToProps)(LoginPage);

