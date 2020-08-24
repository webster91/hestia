import React from 'react';
import ErrorBoundary from "../error-boundry";
import {Route} from "react-router-dom";

export const ErrorBoundaryRoute = ({component: Component, ...rest}) => {
    const encloseInErrorBoundary = props => (
        <ErrorBoundary>
            <Component {...props} />
        </ErrorBoundary>
    );

    if (!Component) throw new Error(`Необходимо указать компонент для пути ${(rest).path}`);

    return <Route {...rest} render={encloseInErrorBoundary}/>;
};

export default ErrorBoundaryRoute;