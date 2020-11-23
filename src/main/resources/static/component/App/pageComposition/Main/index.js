import React, { useState, useEffect, useContext } from "react";
import { useParams, Link } from 'react-router-dom';
import { Context } from "../../context/user/context";
import FormAddParagraph from "../FormAddParagraph";
import FormAddComment from "../FormAddComment";
import "./style.scss";

export const CompositionContext = React.createContext();

function reducerComments(state, action) {
   switch (action.type) {
      case "set": {
         console.log("COMMENTS", action.comments);
         return { comments: action.comments };
      }
      case "add": {
         console.log("NEW COMMENT", action.comment);
         return { comments: [...state.comments, action.comment] };
      }
      default: return new Error("Unknow state!");
   }
}

export default function Main() {
   const { state } = useContext(Context);
   // retrieve params into a variable
   const params = useParams();
   const [composition, setComposition] = useState(null);
   const [paragraphs, setParagraphs] = useState([]);
   const [changerParagraphs, setChangerParagraphs] = useState(false);
   const [showForm, setShowForm] = useState(false);
   const [showFormAddComment, setShowFormAddComment] = useState(false);

   const [stateComments, dispatchComments] = React.useReducer(reducerComments, { comments: [] });

   useEffect(() => {
      fetch(`/api/compositions/${params.id}`, { method: "GET" })
         .then(response => response.json())
         .then(json => {
            setComposition(json);
            // dispatchComments({ type: "set", comments: arr });
         })
         .catch(error => console.log("ERR", error));
   }, []);

   useEffect(() => {
      fetch(`/api/comments/compositions/${params.id}`, { method: "GET" })
         .then(resp => resp.json())
         .then(json => dispatchComments({ type: "set", comments: json }))
         .catch(error => console.log(error));
   }, []);

   useEffect(() => {
      fetch(`/api/paragraphs/compositions/${params.id}`, { method: "GET" })
         .then(response => response.json())
         .then(json => setParagraphs(json))
         .catch(error => console.log("ERR", error));
   }, [changerParagraphs]);

   useEffect(() => {
      const id1 = state && state.user && state.user.id;
      const id2 = composition && composition.user && composition.user.id;
      if (id1 !== null && id1 === id2)
         setShowForm(true);
   }, [composition]);

   useEffect(() => {
      const id = state && state.user && state.user.id;
      if (id !== null && typeof id === "number")
         setShowFormAddComment(true);
   }, []);

   return (
      <CompositionContext.Provider value={{
         compositionId: params.id,
         changerParagraphs: changerParagraphs,
         setChangerParagraphs: setChangerParagraphs,
         dispatchComments: dispatchComments
      }}>
         <main className="main">
            <section>

               <h1>{composition && composition.name}</h1>
               <div className="short-description">{composition && composition.shortDescription}</div>
               <h3>Genres:</h3>
               <ul className="list-genres">
                  {composition && composition.genres && composition.genres.map((genre) => {
                     return (<li className="list-genres__point" key={genre.id}>{genre.name}</li>);
                  })}
               </ul>
               <h3>Tags:</h3>
               <ul className="list-tags">
                  {composition && composition.tags && composition.tags.map((tag) => {
                     return (<li className="list-tags__point" key={tag.id}>{tag.text}</li>);
                  })}
               </ul>
               <h3>Paragraphs:</h3>
               <ul className="list-paragraphs">
                  {paragraphs && paragraphs.map((paragraph, index) => {
                     let link = `/compositions/${params.id}/paragraphs`;
                     return (
                        <li className="list-paragraphs__point" key={index}>
                           <Link to={{
                              pathname: link,
                              state: { index: index }
                           }}>Paragraph {index + 1}. {paragraph.name}</Link>
                        </li>);
                  })}
               </ul>

               {showForm &&
                  <FormAddParagraph />
               }

               <h3>Comments:</h3>
               <ul className="list-comments">
                  {
                     stateComments.comments.map((comment, index) => {
                        return (
                           <li className="list-comments__point" key={index}>
                              <div className="list-comments__point-important">{comment.user.name}</div>: {comment.text}
                           </li>);
                     })
                  }
               </ul>

               {showFormAddComment &&
                  <FormAddComment />
               }
            </section>
         </main>
      </CompositionContext.Provider>
   );
}