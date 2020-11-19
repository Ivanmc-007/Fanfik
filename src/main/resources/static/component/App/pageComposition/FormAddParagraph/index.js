import React from "react";

import { Formik, Form, Field, ErrorMessage } from "formik";
import * as Yup from "yup";
import { Context } from "../../context/user/context";
import { useHistory } from "react-router-dom";

import { Image, Video, Transformation, CloudinaryContext } from 'cloudinary-react';

export default function FormAddParagraph() {
   /*const { setCurrentUser, dispatchUser } = useContext(Context);
   const history = useHistory();

   async function handlerSubmit(values, { setSubmitting }) {
      const uRLSearchParams = new URLSearchParams();
      for (let key in values) {
         uRLSearchParams.append(key, values[key]);
      }

      try {
         const response = await fetch("/login", {
            method: "POST",
            body: uRLSearchParams
         });
         if (response.status === 200 && response.url && response.url.indexOf("error") === -1) {
            const json = await fetchCurrentUser();
            setCurrentUser(json);
            dispatchUser({ type: "isAuthorized" });
            history.push("/");
         } else {
            window.location = response.url;
         }
      } catch (error) {
         console.log("ERR", error);
      } finally {
         setSubmitting(false);
      }

   }

   async function fetchCurrentUser() {
      try {
         const response = await fetch("/api/users/current-user", {
            method: "GET"
         });
         const json = await response.json();
         return json;
      } catch (error) {
         console.log("ERR", error); return null;
      }
   }*/

   return (
      <Formik initialValues={{
         name: "",
         text: "",
         linkToImage: ""
      }} validationSchema={
         Yup.object({
            name: Yup.string()
               .required("Required")
               .max(50, "Must be 50 characters or less"),
            text: Yup.string()
               .required("Required")
         })
      } onSubmit={handlerSubmit}>
         <Form>
            <Field type="text" placeholder="Title" name="name" />
            <div style={{ color: 'red', display: "inline-block" }}><ErrorMessage name="name" style={{ color: 'blue' }} /></div>
            <Field component="textarea" name="text" placeholder="Your text here" />
            <div style={{ color: 'red', display: "inline-block" }}><ErrorMessage name="text" /></div>
            <button type="submit">Add paragraph</button>
         </Form>
      </Formik >
   );

   async function handlerSubmit(values, { setSubmitting }) {
      alert(JSON.stringify(values));
   }
}
