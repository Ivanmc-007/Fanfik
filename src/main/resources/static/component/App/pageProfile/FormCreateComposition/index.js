import React, { useEffect, useState, useContext } from "react";
import { Formik, Form, Field, ErrorMessage } from "formik";
import * as Yup from "yup";
import { ContextProfile } from "../Main/index";

export default function FormCreateComposition(props) {
   const { changerCompositions, setChangerCompositions } = useContext(ContextProfile);
   const [genres, setGenres] = useState([]);
   const [arrChecked] = useState([]);
   useEffect(() => {
      fetch(`/api/genres`, { method: "GET" })
         .then(response => response.json())
         .then(json => setGenres(json))
         .catch(error => console.log("ERR", error));
   }, []);

   return (
      <Formik initialValues={{
         name: "",
         shortDescription: "",
         genres: [],
         tags: [],
      }} validationSchema={
         Yup.object({
            name: Yup.string()
               .required("Required")
               .max(50, "Must be 50 characters or less"),
            shortDescription: Yup.string()
               .required("Required")
               .max(80, "Must be 50 characters or less"),
         })
      } onSubmit={handlerSubmit}>

         <Form>
            <div>
               <Field type="text" placeholder="Name" name="name" />
               <ErrorMessage name="name" />
            </div>
            <div>
               <Field type="text" placeholder="Shot description" name="shortDescription" />
               <ErrorMessage name="shortDescription" />
            </div>
            {
               genres.map((genre, index) => {
                  return (
                     <label key={genre.id}>
                        <Field type="checkbox" checked={arrChecked[index]} name="genres" value={genre.id} />
                        {genre.name}
                     </label>
                  );
               })
            }

            <div><Field type="text" name={`tags[0]`} placeholder="Tag1" /></div>
            <div><Field type="text" name={`tags[1]`} placeholder="Tag2" /></div>
            <div><Field type="text" name={"tags[2]"} placeholder="Tag3" /></div>
            <div>Node: Add paragraphs with text you can on page of composition
               (It will be available after creation)</div>
            <div><button type="submit">Create</button></div>
         </Form>
      </Formik>
   );

   async function handlerSubmit(values, { setSubmitting }) {
      try {
         const response = await fetch("/api/compositions", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(values)
         });
         console.log("status: ", response.status);
         if (response.status === 200) {
            setChangerCompositions(!changerCompositions);
         }
      } catch (err) {
         console.log("ERR", err);
      } finally {
         setSubmitting(false);
      }

   }
}