import React, {Component} from 'react';
import {AvField, AvForm} from 'availity-reactstrap-validation';
import {Alert, Button, Col, Input, Modal, ModalBody, ModalFooter, ModalHeader, Row} from "reactstrap";
import {Link} from 'react-router-dom';
import InputMask from "react-input-mask";
import {REGISTER_URL} from "../../config/url";

class Login extends Component {

    handleSubmit = (event, errors, {telephone, password}) => {
        this.props.handleLogin(telephone, password);
    };

    render() {
        const {loginError, handleClose} = this.props;

        return (
            <Modal isOpen={this.props.showModal} toggle={handleClose} backdrop="static" id="login-page"
                   autoFocus={true}>
                <AvForm onSubmit={this.handleSubmit}>
                    <ModalHeader id="login-title" toggle={handleClose}>
                        Войти
                    </ModalHeader>
                    <ModalBody>
                        <Row>
                            <Col md="12">
                                {loginError ? (
                                    <Alert color="danger">
                                        <strong>Ошибка при входе!</strong> Пожалуйста, проверьте свои учетные данные
                                        и попробуйте еще раз.
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
                                        required: {value: true, errorMessage: 'Введите ваш номер телефона.'},
                                        tel: {pattern: '[\+]7[0-9]{10}', errorMessage: 'Не верный формат.'},
                                    }}
                                    tag={[Input, InputMask]}
                                    mask="+79999999999"
                                    maskChar=""
                                />
                                <AvField
                                    name="password"
                                    type="password"
                                    label="Пароль"
                                    required
                                    errorMessage="Пароль не может быть пустым!"
                                />
                            </Col>
                        </Row>
                        <Alert color="warning">
                            <span>Вы еще не зарегестрированы?</span> <Link to={REGISTER_URL}>Регистрация</Link>
                        </Alert>
                    </ModalBody>
                    <ModalFooter>
                        <Button color="secondary" onClick={handleClose} tabIndex="1">
                            Отмена
                        </Button>{' '}
                        <Button color="primary" type="submit">
                            Войти
                        </Button>
                    </ModalFooter>
                </AvForm>
            </Modal>
        );
    }
}

export default Login;
