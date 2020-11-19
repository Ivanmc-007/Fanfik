import React from "react";
import { Link } from "react-router-dom";

export default function ListComposition(props) {
   const { compositions } = props;
   return (
      <ul>
         {compositions && compositions.map((composition) => {
            const link = `/compositions/${composition.id}`;
            return (
               <li key={composition.id}>
                  <Link to={link}>{composition.name}</Link>
               </li>
            );
         })}
      </ul>
   );
}