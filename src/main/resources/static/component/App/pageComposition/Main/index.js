import React, { useState, useEffect, useContext } from "react";
import { useParams } from 'react-router-dom';
import { Context } from "../../context/user/context";
import FormAddParagraph from "../FormAddParagraph/index";

export default function Main() {
   const { state } = useContext(Context);
   // retrieve params into a variable
   const params = useParams();
   const [composition, setComposition] = useState(null);
   useEffect(() => {
      fetch(`/api/compositions/${params.id}`, { method: "GET" })
         .then(response => response.json())
         .then(json => setComposition(json))
         .catch(error => console.log("ERR", error));
   }, []);
   return (
      <main>
         <section>

            <h1>{composition && composition.name}</h1>
            <h3>{composition && composition.shortDescription}</h3>

            <ul>
               Genres:
               {composition && composition.genres && composition.genres.map((genre) => {
               return (
                  <li key={genre.id}>{genre.name}</li>
               );
            })}
            </ul>
            <ul>
               Tags:
               {composition && composition.tags && composition.tags.map((tag) => {
               return (
                  <li key={tag.id}>{tag.text}</li>
               );
            })}
            </ul>
            {((state && state.user && state.user.id) ===
               (composition && composition.user && composition.user.id)) &&
               <FormAddParagraph />
            }
         </section>
      </main>
   );
}