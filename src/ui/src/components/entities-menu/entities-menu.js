import React from 'react';
import {NavItem, NavLink} from "reactstrap";
import {NavLink as Link} from "react-router-dom";

export const EntitiesMenu = () => {
    return (
        <>
            <NavItem>
                <NavLink tag={Link} to="/user/statistic" active>Статистика</NavLink>
            </NavItem>
            <NavItem>
                <NavLink tag={Link} to="/user/meter" active>Подать показания</NavLink>
            </NavItem>
        </>
    );
}

export default EntitiesMenu;