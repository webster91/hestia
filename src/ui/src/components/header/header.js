import React from 'react';
import NavBar from "../nav-bar/nav-bar";
import {LoadingBar} from "react-redux-loading-bar";
import './header.css';
import {fetchUser, getUser, isAuthenticated, logout, roles} from "../../store/reducers/auth.reducer";
import {connect} from "react-redux";
import {hasAnyAuthority} from "../../utils/auth.util";
import {ROLES} from "../../config/constants";


const Header = (props) => {

    return (
        <div id="app-header">
            <LoadingBar/>
            <NavBar
                isAuthenticated={props.isAuthenticated}
                isAdmin={props.isAdmin}
            />
        </div>
    );
};


const mapStateToProps = state => ({
    user: getUser(state),
    isAuthenticated: isAuthenticated(state),
    isAdmin: hasAnyAuthority(roles(state), [ROLES.ADMIN]),
})

const mapDispatchToProps = {fetchUser, logout};

export default connect(mapStateToProps, null)(Header);
