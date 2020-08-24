import React from 'react';
import {Bar, BarChart, CartesianGrid, Tooltip, XAxis, YAxis} from "recharts";
import moment from "moment";
import {Col, Row} from "reactstrap";

const ReceiptChart = (props) => {

    const {receipts} = props;

    console.log(moment.locale())

    const data = (receipts?.data || []).map((value) => (
        {
            month: moment(value.dateInterest).format('ll'),
            cost: value.sum
        }
    ));

    if (!data || data.length < 1) {
        return (
            <>
                <Row className="justify-content-center">
                    Нет данных платежей
                </Row>
            </>);
    }

    return (
        <Row className="justify-content-center">
            <Col md="8">
                <BarChart width={800} height={800} data={data} maxBarSize={10} barSize={10}>
                    <CartesianGrid strokeDasharray="3 3"/>
                    <XAxis dataKey="month" name="Дата"/>
                    <YAxis/>
                    <Tooltip/>
                    <Bar dataKey="cost" fill="#8884d8" name="Стоимость"/>
                </BarChart>
            </Col>
        </Row>
    )
};

export default ReceiptChart;