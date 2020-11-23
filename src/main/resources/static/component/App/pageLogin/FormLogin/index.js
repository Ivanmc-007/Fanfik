import React, { useContext } from "react";
import { Formik, Form, Field, ErrorMessage } from "formik";
import * as Yup from "yup";
import { Context } from "../../context/user/context";
import { useHistory } from "react-router-dom";
import "./style.scss";

export default function FormLogin() {
   const { setCurrentUser, dispatchUser } = useContext(Context);
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
   }

   return (
      <Formik initialValues={{
         username: "",
         password: ""
      }} validationSchema={
         Yup.object({
            username: Yup.string()
               .required("Required")
               .max(20, "Must be 20 characters or less"),
            password: Yup.string()
               .required("Required")
         })
      } onSubmit={handlerSubmit}>
         <Form className="form-login">
            <Field type="text" placeholder="Login" name="username" className="form-login__field" />
            <div className="form-login__error"><ErrorMessage name="username" style={{ color: 'blue' }} /></div>
            <Field type="password" placeholder="Password" name="password" className="form-login__field" />
            <div className="form-login__error"><ErrorMessage name="password" /></div>
            <button type="submit" className="form-login__button">Log In</button>
         </Form>
      </Formik>
   );
}
