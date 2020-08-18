import React from 'react';
import {NavItem, NavLink} from "reactstrap";
import {NavLink as Link} from "react-router-dom";
import {USER_RECEIPT_URL, USER_STATISTIC_URL} from "../../config/url";

export const EntitiesMenu = () => {
    return (
        <>
            <NavItem>
                <NavLink tag={Link} to={USER_STATISTIC_URL} active>Статистика</NavLink>
            </NavItem>
            <NavItem>
                <NavLink tag={Link} to={USER_RECEIPT_URL} active>Подать показания</NavLink>
            </NavItem>
        </>
    );
}

export default EntitiesMenu;