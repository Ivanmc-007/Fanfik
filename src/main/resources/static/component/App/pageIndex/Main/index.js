import React from "react";
import { Context } from "../../context/user/context";

export default function Main() {
   const { state } = React.useContext(Context);

   return (
      <main>
         <section>
            <h1>MAIN PAGE</h1>
            Current user: {state.user && state.user.name}
         </section>
      </main>
   );
}