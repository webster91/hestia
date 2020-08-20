import React from 'react';
import {AvFeedback, AvField, AvForm} from "availity-reactstrap-validation";
import {Alert, Button, Col, Modal, ModalBody, ModalFooter, ModalHeader, Row} from "reactstrap";

const ChangeStatusModal = (props) => {

    const {ticketId, showModal, errorMessage, handleClose, handleChangeStatusTicket} = props;

    const handleSubmit = (event, values) => {
        handleChangeStatusTicket(ticketId, values.status)
    };

    return (
        <Modal isOpen={showModal} toggle={handleClose} backdrop="static" id="link-user-modal"
               autoFocus={true}>
            <AvForm onValidSubmit={handleSubmit}>
                <ModalHeader id="login-title" toggle={handleClose}>
                    Изменение статуса заявки
                </ModalHeader>
                <ModalBody>
                    <Row>
                        <Col md="12">
                            {errorMessage ? (
                                <Alert color="danger">
                                    <strong>Ошибка при привязке пользователя!</strong> {errorMessage}
                                </Alert>
                            ) : null}
                        </Col>
                        <Col md="12">
                            <AvField
                                id={'status'}
                                name="status"
                                label="Статус"
                                placeholder={''}
                                type="select"
                                validate={{
                                    required: {
                                        value: true,
                                        errorMessage: 'Выберите статус.',
                                    }
                                }}
                            >
                                <AvFeedback>Заполните поле!</AvFeedback>
                                <option> </option>
                                <option>Выполнена</option>
                                <option>В работе</option>
                                <option>Создана</option>
                                <option>Ошибка</option>
                            </AvField>
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
    )
};

export default ChangeStatusModal;