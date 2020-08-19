import React from 'react';
import './admin-menu.css'
import {DropdownItem, DropdownMenu, DropdownToggle, NavLink, UncontrolledDropdown} from "reactstrap";
import {NavLink as Link} from "react-router-dom";
import {ADMIN_MONITORING_URL, ADMIN_USER_LINKING_URL} from "../../config/url";

const AdminMenu = () => {
    return (
        <UncontrolledDropdown nav inNavbar id="basic-nav-dropdown">
            <DropdownToggle nav caret>
                Меню администратора
            </DropdownToggle>
            <DropdownMenu>
                <DropdownItem>
                    <NavLink tag={Link} to={ADMIN_USER_LINKING_URL} active style={{color: 'black'}}>Привязка
                        пользователей</NavLink>
                </DropdownItem>
                <DropdownItem>
                    <NavLink tag={Link} to={ADMIN_MONITORING_URL} active style={{color: 'black'}}>Мониторинг
                        заявок</NavLink>
                </DropdownItem>
            </DropdownMenu>
        </UncontrolledDropdown>
    );
};

export default AdminMenu;