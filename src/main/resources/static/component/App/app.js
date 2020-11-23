import React from "react";
import ContextUser from "./context/user/context";
import {
   BrowserRouter,
   Switch,
   Route
} from "react-router-dom";
import { createBrowserHistory } from 'history';
import MainIndex from "./pageIndex/Main/index";
import MainLogin from "./pageLogin/Main/index";
import MainRegistration from "./pageRegistration/Main/index";
import MainProfile from "./pageProfile/Main/index";
import MainComposition from "./pageComposition/Main/index";
import MainParagraph from "./pageParagraph/Main/index";
import Header from "./Header/index";
import PrivateRoute from "./PrivateRoute/index";
import "./style.scss";

const history = createBrowserHistory();

function fetchCurrentUser() {
   return fetch("/api/users/current-user", { method: "GET", headers: { 'Content-Type': 'application/json' } })
      .then(response => response.json())
      .then(json => json)
      .catch(error => { console.log("ERR", error); return null; });
}

export default function App() {
   return (
      <ContextUser>
         <BrowserRouter history={history}>
            <Header />
            <Switch>
               <Route exact path="/" component={MainIndex} />
               <Route path="/login" component={MainLogin} />
               <Route path="/registration" component={MainRegistration} />
               <Route exact path="/compositions/:id" component={MainComposition} />
               <Route path="/compositions/:id/paragraphs" component={MainParagraph} />
               <PrivateRoute path="/profile" component={MainProfile} pathRedirect="/login" />
               {/* <Route path="/profile" component={MainProfile} /> */}
            </Switch>
         </BrowserRouter>
      </ContextUser>
   );
}


