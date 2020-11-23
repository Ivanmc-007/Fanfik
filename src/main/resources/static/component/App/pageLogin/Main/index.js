import React from "react";
import FormLogin from "../FormLogin/index";
import "./style.scss";

export default function Main() {
   return (
      <main>
         <section className="section-one">
            <FormLogin />
         </section>
      </main>
   );
}