import React from "react";

export const Context = React.createContext();

function reducer(state, action) {
   switch (action.type) {
      case "notAuthorized": return { user: null };
      case "isAuthorized": return { user: getCurrentUser() };
      default: return new Error("Unknow state!");
   }
}

let currUs = null;

function setCurrentUser(currentUser) {
   currUs = currentUser;
}

function getCurrentUser() {
   return currUs;
}

export default function ContextUser({ children }) {

   const [state, dispatchUser] = React.useReducer(reducer, { user: null });

   return (
      <Context.Provider value={
         {
            setCurrentUser: setCurrentUser,
            dispatchUser: dispatchUser,
            state: state
         }
      }>
         {children}
      </Context.Provider>
   );
}