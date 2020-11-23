import React, { useContext, useEffect, useState } from "react";
import { Context } from "../../context/user/context";
import FormCreateComposition from "../FormCreateComposition/index";
import ListComposition from "../ListComposition";
import UserInfo from "../UserInfo";
import "./style.scss";

export const ContextProfile = React.createContext();

export default function Main() {
   const { state } = useContext(Context);
   const [user, setUser] = useState(null);
   const [compositions, setCompositions] = useState([]);
   const [changerCompositions, setChangerCompositions] = useState(false);

   useEffect(() => {
      fetch(`/api/users/${state.user.id}`, { method: "GET" })
         .then(response => response.json())
         .then(json => setUser(json))
         .catch(error => console.log("ERR", error));
   }, []);

   useEffect(() => {
      fetch("/api/compositions/current-user", { method: "GET" })
         .then(response => response.json())
         .then(json => setCompositions(json))
         .catch(error => console.log("ERR", error));
   }, [changerCompositions]);

   return (
      <main className="main">
         <section className="section-one-page-profile">
            <ContextProfile.Provider value={
               {
                  changerCompositions: changerCompositions,
                  setChangerCompositions: setChangerCompositions
               }
            }>
               <h3>About user</h3>
               <UserInfo user={user} />
               <br />
               <h3>Compositions</h3>
               <ListComposition compositions={compositions} />

               <h3>Create composition</h3>
               <FormCreateComposition />
            </ContextProfile.Provider>
         </section>
      </main >
   );
}