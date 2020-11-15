import React from "react";
import { Link } from "react-router-dom";
import { Context } from "../context/user/context";

export default function Header() {
   const { state } = React.useContext(Context);

   return (
      <header>
         <section>
            <div><Link to="/">Logotype</Link></div>
            {!state.user &&
               <nav>
                  <ul>
                     <li>
                        <Link to="/login">login</Link>
                     </li>
                     <li>
                        <Link to="/registration">registration</Link>
                     </li>
                  </ul>
               </nav> ||
               <nav>
                  <ul>
                     <li>
                        <Link to="/profile">profile</Link>
                     </li>
                     <li>
                        <a href="/logout">logout</a>
                     </li>
                  </ul>
               </nav>
            }
         </section>
      </header>
   );
}