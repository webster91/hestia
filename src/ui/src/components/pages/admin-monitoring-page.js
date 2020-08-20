import React, {useEffect, useState} from 'react';
import {connect} from "react-redux";
import {
    changeStatusTicket,
    createSuccess,
    errorMessage,
    fetchAllTickets,
    getTickets,
    reset
} from "../../store/reducers/ticket.reducer";
import {Button, Table} from "reactstrap";
import ChangeStatusModal from "../changeStatusModal";

const AdminMonitoringPage = (props) => {

    const {tickets, fetchAllTickets, changeStatusTicket, errorMessage, reset, createSuccess} = props

    const [ticketId, setTicketId] = useState();
    const [showModal, setShowModal] = useState(false);

    useEffect(() => {
        fetchAllTickets();
    }, createSuccess);

    const openModal = (id) => () => {
        setShowModal(true)
        setTicketId(id)
    }

    const handleClose = () => {
        setShowModal(false);
    };

    const handleChangeStatusTicket = async (ticketId, status) => {
        await changeStatusTicket(ticketId, status)
        !errorMessage && setShowModal(false);
        reset()
        fetchAllTickets()
    }

    const renderChangeStatusTicketModal = () => {
        return showModal && <ChangeStatusModal ticketId={ticketId}
                                               showModal={showModal}
                                               errorMessage={errorMessage}
                                               handleClose={handleClose}
                                               handleChangeStatusTicket={handleChangeStatusTicket}/>
    }

    return (
        <div className="table-responsive">
            <Table className="table-striped">
                <thead>
                <tr>
                    <th>ID заявки</th>
                    <th>ID пользователя</th>
                    <th>Заголовок</th>
                    <th>Описание</th>
                    <th>Статус</th>
                    <th/>
                </tr>
                </thead>

                <tbody>
                {tickets.map((s, index) => (
                    <tr key={index}>
                        <td>{s.id}</td>
                        <td>{s.userId}</td>
                        <td>{s.header}</td>
                        <td>{s.description}</td>
                        <td>{s.status}</td>
                        <td>
                            <Button color="primary" onClick={openModal(s.id)}>
                                Сменить статус
                            </Button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </Table>
            {renderChangeStatusTicketModal()}
        </div>
    )
}


const mapStateToProps = (state) => ({
    tickets: getTickets(state),
    errorMessage: errorMessage(state),
    createSuccess: createSuccess(state),
});

const mapDispatchToProps = {fetchAllTickets, changeStatusTicket, reset};

export default connect(mapStateToProps, mapDispatchToProps)(AdminMonitoringPage);
