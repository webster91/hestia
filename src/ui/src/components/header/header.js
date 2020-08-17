import React from 'react';
import NavBar from "../nav-bar/nav-bar";
import {LoadingBar} from "react-redux-loading-bar";
import './header.css';
import {fetchUser, getUser, isAuthenticated, logout} from "../../store/reducers/auth.reducer";
import {connect} from "react-redux";


const Header = (props) => {

    return (
        <div id="app-header">
            <LoadingBar className="loading-bar"/>
            <NavBar
                isAuthenticated={props.isAuthenticated}
                isAdmin={props.isAdmin}
            />
        </div>
    );
};


const mapStateToProps = state => ({
    user: getUser(state),
    isAuthenticated: isAuthenticated(state)
})

const mapDispatchToProps = {fetchUser, logout};

export default connect(mapStateToProps, null)(Header);
