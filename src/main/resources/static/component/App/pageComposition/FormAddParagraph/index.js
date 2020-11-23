import React, { useContext } from "react";

import { Formik, Form, Field, ErrorMessage } from "formik";
import * as Yup from "yup";

import { CompositionContext } from "../Main/index";
import "./style.scss";

export default function FormAddParagraph() {
   const { compositionId, changerParagraphs, setChangerParagraphs } = useContext(CompositionContext);
   const nameCloudinary = "ivanmc-007cloudinary";
   let uploadUrl = `https://api.cloudinary.com/v1_1/${nameCloudinary}/image/upload`;

   return (
      <Formik
         initialValues={{
            name: "",
            text: "",
            image: null
         }}
         validationSchema={
            Yup.object({
               name: Yup.string()
                  .required("Required")
                  .max(50, "Must be 50 characters or less"),
               text: Yup.string()
                  .max(4000, "Must be 4000 characters or less")
                  .required("Required"),
               image: Yup.mixed().required("Requered"),
            })
         }
         onSubmit={onSubmitHandler}>
         {formik => (
            <Form className="form-add-paragraph">
               <Field type="text" placeholder="Title" name="name" className="form-add-paragraph__field" />
               <div className="form-add-paragraph__error"><ErrorMessage name="name" style={{ color: 'blue' }} /></div>
               <Field component="textarea" name="text" placeholder="Your text here" className="form-add-paragraph__textarea" />
               <div className="form-add-paragraph__error"><ErrorMessage name="text" /></div>

               <div className="form-add-paragraph__file">
                  <input id="file" name="image" type="file" onChange={(event) => {
                     formik.setFieldValue("image", event.currentTarget.files[0]);
                  }} />
               </div>

               <div className="form-add-paragraph__error"><ErrorMessage name="image" /></div>
               <button className="form-add-paragraph__button" type="submit">Add paragraph</button>
            </Form>
         )}
      </Formik>
   );

   async function onSubmitHandler(values, { setSubmitting }) {
      const linkToImage = await getLinkOnFileInCloudinary(values.image);
      let valuesBody = {};
      valuesBody.linkToImage = linkToImage;

      for (let key in values) {
         if (key !== "image")
            valuesBody[key] = values[key];
      }

      fetch(`/api/paragraphs/compositions/${compositionId}`, {
         method: "POST",
         headers: { 'Content-Type': 'application/json' },
         body: JSON.stringify(valuesBody)
      })
         .then(response => {
            if (response.status === 200)
               setChangerParagraphs(!changerParagraphs);
            // TODO: обработать ERROR.status 
         })
         .catch(error => console.log("ERR", error))
         .finally(setSubmitting(false));

   }

   async function getLinkOnFileInCloudinary(file) {
      let formData = new FormData();
      formData.append('file', file);
      formData.append('upload_preset', 'upload_fanfik');

      const response = await fetch(uploadUrl, {
         method: "POST",
         body: formData
      });
      const json = await response.json();
      return json.secure_url;
   }

}
