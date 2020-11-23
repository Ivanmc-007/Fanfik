import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import "./style.scss";

export default function SectionNewCompositions() {

   const [compositions, setCompositions] = useState([]);

   useEffect(() => {
      fetch("/api/compositions/newest", { method: "GET" })
         .then(resp => resp.json())
         .then(json => setCompositions(json))
         .catch(err => consile.log("ERR", err));
   }, []);

   return (
      <section>
         <h3>Last compositions</h3>
         <ListComposition compositions={compositions} />
      </section>
   );
}

function ListComposition(props) {
   const { compositions } = props || [];
   return (
      <ul className="list-composition-newest">
         {
            compositions.map((composition) => {
               const link = `/compositions/${composition.id}`;
               return (
                  <li className="list-composition-newest__point" key={composition.id}>
                     <Link to={link}>{composition.name}</Link>
                  </li>
               );
            })
         }
      </ul>
   );
}