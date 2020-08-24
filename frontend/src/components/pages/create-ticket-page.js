import React from 'react';
import CreateTicket from "../create-task/create-ticket";
import {connect} from "react-redux";
import {createFailure, createSuccess, errorMessage, reset, saveUserTicket} from "../../store/reducers/ticket.reducer";
import {getUser} from "../../store/reducers/auth.reducer";
import {Redirect} from "react-router-dom";
import {MAIN_URL} from "../../config/url";

const CreateTicketPage = (props) => {

    const {saveUserTicket, user = {}, reset, createFailure, createSuccess, errorMessage} = props;

    const handleSubmit = (header, description) => {
        saveUserTicket(user.id, header, description)
    };

    const {location} = props;
    const {from} = (location.state) || {from: {pathname: MAIN_URL, search: location.search}};

    if (createSuccess) {
        reset();
        return <Redirect to={from}/>;
    }

    return (
        <>
            <CreateTicket onSubmit={handleSubmit} errorMessage={errorMessage} createFailure={createFailure}/>
        </>
    )
};

const mapStateToProps = (state) => ({
    errorMessage: errorMessage(state),
    user: getUser(state),
    createFailure: createFailure(state),
    createSuccess: createSuccess(state),
});

const mapDispatchToProps = {saveUserTicket, reset};

export default connect(mapStateToProps, mapDispatchToProps)(CreateTicketPage);