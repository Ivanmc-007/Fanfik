import React from "react";
import { Formik, Form, Field, ErrorMessage } from 'formik';
import * as Yup from 'yup';

export default function FormRegistration() {

   async function handlerSubmit(values, { setSubmitting }) {
      alert(JSON.stringify(values, null, 2));
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
      } onSubmit={(values, { setSubmitting }) => {
         fetch("/api/users", { method: "POST", headers: { "Content-Type": "application/json" }, body: JSON.stringify(values) })
            .then(response => {
               let status = response.status;
               console.log("LOG", status);
               if (status === 200) console.log("Registration is successful");
               if (status === 409) console.log("User with this name exists!");
               // TODO: if(status === 200) может очистить поля
               // and show a message: "Check your email" 
               // TODO: if(status === 409) 
               // show a message about ERROR: "Сhoose onother login and try again!"
            })
            .catch(error => console.log("ERR", error))
            .finally(() => setSubmitting(false));
      }}>
         <Form>
            <Field type="text" placeholder="Login" name="name" />
            <div style={{ color: 'red', display: "inline-block" }}><ErrorMessage name="name" style={{ color: 'blue' }} /></div>
            <Field type="email" placeholder="Email" name="email" />
            <div style={{ color: 'red', display: "inline-block" }}><ErrorMessage name="email" /></div>
            <Field type="password" placeholder="Password" name="password" />
            <div style={{ color: 'red', display: "inline-block" }}><ErrorMessage name="password" /></div>
            <button type="submit">Register now</button>
         </Form>
         {/* {formik => (
            <form onSubmit={formik.handleSubmit}>
               <input type="text" placeholder="Login" {...formik.getFieldProps("name")} />
               {formik.touched.name && formik.errors.name ? <div>{formik.errors.name}</div> : null}
               <input type="email" placeholder="Email" {...formik.getFieldProps("email")} />
               {formik.touched.email && formik.errors.email ? <div>{formik.errors.email}</div> : null}
               <input type="password" placeholder="Password" {...formik.getFieldProps("password")} />
               {formik.touched.password && formik.errors.password ? <div>{formik.errors.password}</div> : null}
               <button type="submit">Register now</button>
            </form>
         )} */}
      </Formik>


   );
}