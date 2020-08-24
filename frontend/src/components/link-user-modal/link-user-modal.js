import React from 'react';
import {AvField, AvForm} from "availity-reactstrap-validation";
import {Alert, Button, Col, Input, Modal, ModalBody, ModalFooter, ModalHeader, Row} from "reactstrap";
import InputMask from "react-input-mask";

const LinkUserModal = (props) => {

    const {addressId, linkError, handleClose, showModal, handleLink} = props;

    const handleSubmit = (event, errors, {telephone}) => {
        handleLink(addressId, telephone)
    };

    return (
        <Modal isOpen={showModal} toggle={handleClose} backdrop="static" id="link-user-modal"
               autoFocus={true}>
            <AvForm onSubmit={handleSubmit}>
                <ModalHeader id="login-title" toggle={handleClose}>
                    Привязать адрес к пользователю
                </ModalHeader>
                <ModalBody>
                    <Row>
                        <Col md="12">
                            {linkError ? (
                                <Alert color="danger">
                                    <strong>Ошибка при привязке пользователя!</strong> {linkError}
                                </Alert>
                            ) : null}
                        </Col>
                        <Col md="12">
                            <AvField
                                name="telephone"
                                label="Телефон"
                                placeholder={''}
                                type="tel"
                                validate={{
                                    required: {value: true, errorMessage: 'Введите номер телефона.'},
                                    tel: {pattern: '[\+]7[0-9]{10}', errorMessage: 'Не верный формат.'},
                                }}
                                tag={[Input, InputMask]}
                                mask="+79999999999"
                                maskChar=""
                            />
                        </Col>
                    </Row>
                </ModalBody>
                <ModalFooter>
                    <Button color="secondary" onClick={handleClose} tabIndex="1">
                        Отмена
                    </Button>{' '}
                    <Button color="primary" type="submit">
                        Привязать
                    </Button>
                </ModalFooter>
            </AvForm>
        </Modal>
    );

};

export default LinkUserModal;