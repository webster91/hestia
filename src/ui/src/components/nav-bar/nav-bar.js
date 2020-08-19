import React, {useState} from 'react';
import {Collapse, Nav, Navbar, NavbarBrand, NavbarToggler} from 'reactstrap';
import UserInfo from "../user-info/user-info";
import {NavLink as Link} from 'react-router-dom';
import EntitiesMenu from "../entities-menu/entities-menu";
import AdminMenu from "../admin-menu";
import {MAIN_URL} from "../../config/url";

const NavBar = (props) => {

    const [isOpen, setIsOpen] = useState(false);

    const toggle = () => setIsOpen(!isOpen);

    return (
        <div>
            <Navbar expand="lg" color="dark" dark>
                <NavbarBrand tag={Link} to={MAIN_URL}>
                    <span>Hestia</span>
                </NavbarBrand>
                <NavbarToggler onClick={toggle} aria-controls="responsive-navbar-nav"/>
                <Collapse isOpen={isOpen} navbar>
                    <Nav className="mr-auto" navbar>
                        {props.isAuthenticated && <EntitiesMenu/>}
                        {<AdminMenu/>}
                    </Nav>
                    <Nav>
                        <UserInfo/>
                    </Nav>
                </Collapse>
            </Navbar>
        </div>
    );
}

export default NavBar;