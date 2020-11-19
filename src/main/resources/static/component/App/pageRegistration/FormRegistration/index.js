import React, { useState } from "react";
import { Formik, Form, Field, ErrorMessage } from 'formik';
import * as Yup from 'yup';
import { useHistory } from "react-router-dom";

export default function FormRegistration() {

   const history = useHistory();
   const [errorMessage, setErrorMessage] = useState("");

   function onSubmitHandler(values, { setSubmitting }) {
      fetch("/api/users", { method: "POST", headers: { "Content-Type": "application/json" }, body: JSON.stringify(values) })
         .then(response => {
            const status = response.status;
            if (status === 200) history.push("/login");
            if (status === 409) setErrorMessage("User with this name exists!");
         })
         .catch(error => console.log("ERR", error))
         .finally(() => setSubmitting(false));
   }

   return (
      <Formik initialValues={{
         name: "",
         email: "",
         password: ""
      }} validationSchema={
         Yup.object({
            name: Yup.string()
               .required("Required")
               .max(20, "Must be 20 characters or less"),
            password: Yup.string()
               .required("Required"),
            email: Yup.string()
               .required("Required")
               .email("Invalid email address")
         })
      } onSubmit={onSubmitHandler}>
         <Form>
            <Field type="text" placeholder="Login" name="name" />
            <div style={{ color: 'red', display: "inline-block" }}><ErrorMessage name="name" style={{ color: 'blue' }} /></div>
            <div style={{ color: 'red', display: "inline-block" }}><MyErrorMessage value={errorMessage} /></div>
            <Field type="email" placeholder="Email" name="email" />
            <div style={{ color: 'red', display: "inline-block" }}><ErrorMessage name="email" /></div>
            <Field type="password" placeholder="Password" name="password" />
            <div style={{ color: 'red', display: "inline-block" }}><ErrorMessage name="password" /></div>
            <button type="submit">Register now</button>
         </Form>
      </Formik>
   );
}

function MyErrorMessage(props) {
   let { value } = props || "";
   return (
      <React.Fragment>
         {value}
      </React.Fragment>
   );
}