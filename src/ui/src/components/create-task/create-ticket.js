import React from 'react';
import {Alert, Button, Col, Row} from "reactstrap";
import {AvField, AvForm} from 'availity-reactstrap-validation';


const CreateTicket = (props) => {

    const {onSubmit, createFailure, errorMessage} = props;

    const handleSubmit = (event, errors, {header, description}) => {
        onSubmit(header, description)
    };

    return (
        <Row className="justify-content-center">
            <Col md="8">
                <h2 id="settings-title">Формирование заявки</h2>
                {createFailure ? (
                    <Alert color="danger">
                        <strong>{errorMessage}</strong>
                    </Alert>
                ) : null}
                <AvForm id="settings-form" onSubmit={handleSubmit}>
                    <AvField
                        className="form-control"
                        name="header"
                        id="header"
                        label="Загаловок"
                        placeholder="Название загаловка"
                        validate={{
                            required: {value: true, errorMessage: 'Введите заголовок.'},
                            minLength: {
                                value: 1,
                                errorMessage: 'Ваш заголовок должн состоять не менее чем из 1 символов'
                            },
                            maxLength: {value: 70, errorMessage: 'Ваш заголовок не может быть длиннее 70 символов.'},
                        }}
                    />
                    <AvField
                        className="form-control"
                        name="description"
                        id="description"
                        label="Описание"
                        placeholder="Описание заявки"
                        type="textarea"
                        validate={{
                            required: {value: true, errorMessage: 'Введите описание заявки.'},
                            minLength: {
                                value: 5,
                                errorMessage: 'Ваше описание заявки должно состоять не менее чем из 5 символов.'
                            },
                            maxLength: {value: 512, errorMessage: 'Ваш заголовок не может быть длиннее 512 символов.'},
                        }}
                    />
                    <Button color="primary" type="submit">
                        Создать
                    </Button>
                </AvForm>
            </Col>
        </Row>
    );
};

export default CreateTicket;