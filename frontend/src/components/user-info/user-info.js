import React from 'react';
import {connect} from "react-redux";
import {fetchUser, getUser, isAuthenticated, logout} from "../../store/reducers/auth.reducer";
import {NavLink as Link, withRouter} from "react-router-dom";
import {Button, NavbarText, NavItem, NavLink} from "reactstrap";
import {LOGIN_URL, REGISTER_URL} from "../../config/url";

export const UserInfo = (props) => {
    const {user, isAuthenticated} = props;

    const logout = () => {
        props.logout();
    }

    const authMenuItems = (
        <>
            <NavItem>
                <NavLink tag={Link} to={LOGIN_URL} active>Войти</NavLink>
            </NavItem>
            <NavItem>
                <NavLink tag={Link} to={REGISTER_URL} active>Регистрация</NavLink>
            </NavItem>
        </>
    );

    const userMenuItems = (
        <>
            <NavItem>
                <NavbarText>Пользователь: {user.name}</NavbarText>
            </NavItem>
            <NavItem>
                <Button style={{marginLeft: '15px'}} color="secondary" size="sm"
                        onClick={() => logout()}> Выйти</Button>
            </NavItem>
        </>
    )


    if (!!isAuthenticated) {
        return (userMenuItems)
    } else {
        return (authMenuItems)
    }

}

const mapStateToProps = state => ({
    user: getUser(state),
    isAuthenticated: isAuthenticated(state)
})

const mapDispatchToProps = {fetchUser, logout};

export default withRouter(connect(
    mapStateToProps,
    mapDispatchToProps
)(UserInfo));