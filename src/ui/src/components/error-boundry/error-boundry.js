import React, {Component, useState} from 'react';
import ErrorIndicator from '../error-indicator';

export default class ErrorBoundary extends Component {

    constructor() {
        super();
        this.state = {hasError: false};
    }

    //Хз как реализовать в функциональном стиле
    componentDidCatch() {
        this.setState({hasError: true});
    }

    render() {
        if (this.state.hasError) {
            return <ErrorIndicator/>;
        }

        return this.props.children;
    }
}
