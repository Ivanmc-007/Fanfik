import React, { useEffect, useState, useContext } from "react";
import { Formik, Form, Field, ErrorMessage } from "formik";
import * as Yup from "yup";
import { ContextProfile } from "../Main/index";
import "./style.scss";

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
         tags: ["", "", ""],
      }} validationSchema={
         Yup.object({
            name: Yup.string()
               .required("Required")
               .max(50, "Must be 50 characters or less"),
            shortDescription: Yup.string()
               .required("Required")
               .max(80, "Must be 50 characters or less"),
         })
      } onSubmit={onSubmitHandler}>

         <Form className="form-create-composition">

            <Field type="text" placeholder="Name" name="name" className="form-create-composition__field" />
            <div className="form-create-composition__error"><ErrorMessage name="name" /></div>

            <Field type="text" placeholder="Shot description" name="shortDescription" className="form-create-composition__field" />
            <div className="form-create-composition__error"><ErrorMessage name="shortDescription" /></div>
            <div className="form-create-composition__checkboxs">
               {
                  genres.map((genre, index) => {
                     return (
                        <label key={genre.id}>
                           <Field type="checkbox" checked={arrChecked[index]} name="genres" value={genre.id} className="form-create-composition__checkbox" />
                           {genre.name}
                        </label>
                     );
                  })
               }
            </div>

            <Field type="text" name={`tags[0]`} placeholder="Tag1" className="form-create-composition__field" />
            <Field type="text" name={`tags[1]`} placeholder="Tag2" className="form-create-composition__field" />
            <Field type="text" name={"tags[2]"} placeholder="Tag3" className="form-create-composition__field" />
            <div>Node: Add paragraphs with text you can on page of composition
               (It will be available after creation)</div>
            <button className="form-create-composition__button" type="submit">Create</button>
         </Form>
      </Formik>
   );

   function onSubmitHandler(values, { setSubmitting }) {
      let valuesBody = {};
      for (let key in values) {
         if (key === "tags") {
            valuesBody.tags = [];
            values.tags.forEach(tag => {
               if (tag.length > 0)
                  valuesBody.tags.push(tag);
            });
         } else {
            valuesBody[key] = values[key];
         }
      }

      fetch("/api/compositions", {
         method: "POST",
         headers: { "Content-Type": "application/json" },
         body: JSON.stringify(valuesBody)
      })
         .then(response => {
            if (response.status === 200)
               setChangerCompositions(!changerCompositions);
            else throw new Error(`Something is wrong! Status response: ${response.status}`);
         })
         .catch(error => console.log("ERR", error))
         .finally(setSubmitting(false));
   }
}