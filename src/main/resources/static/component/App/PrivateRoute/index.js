import React from "react";
import {
   Route,
   Redirect
} from "react-router-dom";
import { Context } from "../context/user/context";

export default function PrivateRoute(props) {
   const { state } = React.useContext(Context);
   const { path } = props;
   const { component } = props;
   const { pathRedirect } = props;

   return state.user ?
      <Route path={path} component={component} /> :
      <Redirect from={path} to={pathRedirect} />;

}
