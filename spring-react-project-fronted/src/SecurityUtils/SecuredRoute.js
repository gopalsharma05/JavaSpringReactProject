import { Component } from "react";
import { Route, Redirect } from "react-router-dom";
import { useSelector } from "react-redux";

const SecuredRoute = ({ component: Component, security, ...otherProps }) => {
  const isValid = useSelector((state) => state.security.validToken);
  return (
    <Route
      {...otherProps}
      render={(props) =>
        isValid === true ? <Component {...props} /> : <Redirect to="/login" />
      }
    />
  );
};

export default SecuredRoute;
