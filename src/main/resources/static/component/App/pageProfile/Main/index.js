import React, { useContext, useEffect, useState } from "react";
import { Context } from "../../context/user/context";

export default function Main() {
   const { state } = useContext(Context);
   let [user, setUser] = useState(null);
   useEffect(() => {
      fetch(`/api/users/${state.user.id}`, { method: "GET" })
         .then(response => response.json())
         .then(json => {
            setUser(json);
            console.log(json);
         })
         .catch(error => console.log("ERR", error));
   }, []);

   // TODO: remove if needn't
   async function fetchUser() {
      try {
         const response = await fetch(`/api/users/${state.user.id}`, { method: "GET" });
         const json = await response.json();
         console.log("JSON: ", json);
         return json;
      } catch (error) {
         console.log("ERR", error);
      }
   }

   return (
      <main>
         <section>
            <h3>About user</h3>
            <div>Name: {user && user.name}</div>
         </section>
      </main>
   );
}