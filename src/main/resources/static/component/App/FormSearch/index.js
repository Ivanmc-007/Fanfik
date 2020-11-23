import React from "react";
import { Formik, Form, Field } from "formik";
import "./style.scss";

export default function FormSearch(props) {
   const { setCompositions } = props;

   return (
      <Formik
         initialValues={{
            searchText: ""
         }}
         onSubmit={onSubmitHandler}
      >
         <Form className="from-search">
            <Field type="text" name="searchText" className="from-search__field" />
            <button type="submit" className="from-search__button">Search</button>
         </Form>
      </Formik >
   );

   function onSubmitHandler(values, { setSubmitting }) {
      console.log("*");
      if (values.searchText.length === 0) {
         setCompositions([]);
         setSubmitting(false);
         return;
      }

      fetch(`/api/compositions/search?text=${values.searchText}`, { method: "GET" })
         .then(resp => resp.json())
         .then(json => setCompositions(json))
         .catch(err => console.log("ERR", err))
         .finally(setSubmitting(false));
   }
}