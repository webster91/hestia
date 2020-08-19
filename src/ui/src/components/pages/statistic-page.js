import React, {useEffect} from 'react';
import {connect} from "react-redux";
import ReceiptChart from "../receipt-chart";
import {fetchReceiptByAddress, receipts} from "../../store/reducers/receipt.reducer";
import {getUser} from "../../store/reducers/auth.reducer";

const StatisticPage = (props) => {

    useEffect(() => {
        props.fetchReceiptByAddress(props.getUser?.addressId);
    }, [receipts]);

    return (
        <>
            <h2>Статистика начисления платежей</h2>
            <ReceiptChart receipts={props.receipts}/>
        </>
    );
};

const mapStateToProps = (state) => ({
    getUser: getUser(state),
    receipts: receipts(state),
});

const mapDispatchToProps = {fetchReceiptByAddress};

export default connect(mapStateToProps, mapDispatchToProps)(StatisticPage);