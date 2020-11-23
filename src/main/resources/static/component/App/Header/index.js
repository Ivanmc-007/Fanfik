import React from "react";
import { Link } from "react-router-dom";
import { Context } from "../context/user/context";
import "./style.scss";

export default function Header() {
   const { state } = React.useContext(Context);

   return (
      <header className="header">
         <section className="section-header">
            <div className="section-header__logo">
               <Link to="/">Logotype</Link>
            </div>
            {!state.user &&
               <nav className="section-header__menu">
                  <ul className="section-header__list">
                     <li className="section-header__point">
                        <Link to="/login">login</Link>
                     </li>
                     <li className="section-header__point">
                        <Link to="/registration">registration</Link>
                     </li>
                  </ul>
               </nav> ||
               <nav className="section-header__menu">
                  <ul className="section-header__list">
                     <li className="section-header__point">
                        <Link to="/profile">profile</Link>
                     </li>
                     <li className="section-header__point">
                        <a href="/logout">logout</a>
                     </li>
                  </ul>
               </nav>
            }
         </section>
      </header>
   );
}