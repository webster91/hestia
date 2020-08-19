import React, {useEffect, useState} from 'react';
import {Button, Table} from "reactstrap";
import {connect} from "react-redux";
import {fetchAddresses, getAddress} from "../../store/reducers/address.reducer";
import LinkUserModal from "../link-user-modal";
import {errorMessage, linkAddressToUser, resetFormLinkAddresses} from "../../store/reducers/auth.reducer";

const AdminLinkingPage = (props) => {

    const [addressId, setAddressId] = useState();
    const [showModal, setShowModal] = useState(false);

    useEffect(() => {
        props.fetchAddresses();
    }, []);


    useEffect(() => () => {
        props.resetFormLinkAddresses();
    }, [showModal]);

    const handleClose = () => {
        setShowModal(false);
    };

    const handleLink = (addressId, telephone) => {
        props.linkAddressToUser(addressId, telephone);
        !!props.errorMessage && setShowModal(false);
    };

    const doLinkUser = (id) => () => {
        setShowModal(true)
        setAddressId(id)
    }


    const renderLinkUser = () => {
        return showModal && <LinkUserModal addressId={addressId}
                                           showModal={showModal}
                                           linkError={props.errorMessage}
                                           handleClose={handleClose}
                                           handleLink={handleLink}/>
    }

    return (
        <div className="table-responsive">
            <Table className="table-striped">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Город</th>
                    <th>Улица</th>
                    <th>Дом</th>
                    <th>Квартира</th>
                    <th/>
                </tr>
                </thead>

                <tbody>
                {props.address.map((s, index) => (
                    <tr key={index}>
                        <td>{s.id}</td>
                        <td>{s.city}</td>
                        <td>{s.street}</td>
                        <td>{s.house}</td>
                        <td>{s.flat}</td>
                        <td>
                            <Button color="primary" onClick={doLinkUser(s.id)}>
                                Привязать
                            </Button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </Table>
            {renderLinkUser()}
        </div>
    )
}

const mapStateToProps = (state) => ({
    address: getAddress(state),
    errorMessage: errorMessage(state),
});

const mapDispatchToProps = {fetchAddresses, linkAddressToUser, resetFormLinkAddresses};

export default connect(mapStateToProps, mapDispatchToProps)(AdminLinkingPage);
