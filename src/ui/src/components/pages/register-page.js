import {AvField, AvForm} from 'availity-reactstrap-validation';
import {Alert, Button, Col, Input, Row} from 'reactstrap';
import React, {useEffect} from "react";
import InputMask from 'react-input-mask';
import {connect} from "react-redux";
import {
    errorMessage,
    handleRegister,
    registrationFailure,
    registrationSuccess,
    resetForm
} from "../../store/reducers/register.reducer";
import {Redirect} from "react-router-dom";

const RegisterPage = (props) => {

    useEffect(
        () => () => {
            props.resetForm();
        },
        []
    );

    const {registrationSuccess, registrationFailure, location, errorMessage} = props;


    const handleValidSubmit = (event, values) => {
        props.handleRegister(values.username, values.email, values.telephone, values.firstPassword);
        event.preventDefault();
    };


    if (registrationSuccess) {
        const {from} = (location.state) || {from: {pathname: '/', search: location.search}};
        return <Redirect to={from}/>;
    }

    return (
        <div>
            <Row className="justify-content-center">
                <Col md="8">
                    <h1 id="register-title">Регистрация</h1>
                </Col>
            </Row>
            <Row className="justify-content-center">
                <Col md="8">
                    {registrationFailure ? (
                        <Alert color="danger">
                            <strong>{errorMessage}</strong>
                        </Alert>
                    ) : null}
                    <AvForm id="register-form" onValidSubmit={handleValidSubmit}>
                        <AvField
                            name="username"
                            label="Имя пользователя"
                            placeholder={''}
                            validate={{
                                required: {value: true, errorMessage: 'Введите Имя пользователя'},
                                minLength: {
                                    value: 1,
                                    errorMessage: 'Имя пользователя должно быть больше 1 символа.'
                                },
                                maxLength: {
                                    value: 50,
                                    errorMessage: 'Имя пользователя не должно быть больше 50 символов.'
                                },
                            }}
                        />
                        <AvField
                            name="email"
                            label="Email"
                            placeholder={''}
                            type="email"
                            errorMessage={'Неверный формат email'}
                            validate={{
                                required: {value: true, errorMessage: 'Введите Email.'},
                                minLength: {
                                    value: 5,
                                    errorMessage: 'Ваш адрес электронной почты должен содержать не менее 5 символов.'
                                },
                                maxLength: {
                                    value: 254,
                                    errorMessage: 'Ваш адрес электронной почты не должн быть больше 50 символов.'
                                },
                            }}
                        />
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
                            name="firstPassword"
                            label="Пароль"
                            placeholder={''}
                            type="password"
                            validate={{
                                required: {value: true, errorMessage: 'Введите пароль.'},
                                minLength: {
                                    value: 4,
                                    errorMessage: 'Ваш пароль должен состоять не менее чем из 4 символов.'
                                },
                                maxLength: {
                                    value: 50,
                                    errorMessage: 'Ваш пароль не может быть длиннее 50 символов.'
                                },
                            }}
                        />
                        <AvField
                            name="secondPassword"
                            label="Подтвердите пароль"
                            placeholder=""
                            type="password"
                            validate={{
                                required: {value: true, errorMessage: 'Подтвердите ваш пароль.'},
                                minLength: {
                                    value: 4,
                                    errorMessage: 'Ваш пароль должен состоять не менее чем из 4 символов.'
                                },
                                maxLength: {
                                    value: 50,
                                    errorMessage: 'Ваш пароль не может быть длиннее 50 символов.'
                                },
                                match: {
                                    value: 'firstPassword',
                                    errorMessage: 'Пароль и его подтверждение не совпадают!'
                                },
                            }}
                        />
                        <Button id="register-submit" color="primary" type="submit">
                            Регистрация
                        </Button>
                    </AvForm>
                </Col>
            </Row>
        </div>
    );
}

const mapStateToProps = (state) => ({
    registrationFailure: registrationFailure(state),
    registrationSuccess: registrationSuccess(state),
    errorMessage: errorMessage(state),
});

const mapDispatchToProps = {handleRegister, resetForm};

export default connect(mapStateToProps, mapDispatchToProps)(RegisterPage);