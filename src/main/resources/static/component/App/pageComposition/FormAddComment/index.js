import React, { useContext, useState } from "react";
import { Context } from "../../context/user/context";
import { CompositionContext } from "../Main/index";
import { Formik, Form, Field, ErrorMessage } from "formik";
import * as Yup from "yup";
import SockJsClient from "react-stomp";

export default function FormAddComment() {
   const { state } = useContext(Context);
   const { compositionId, dispatchComments } = useContext(CompositionContext);
   const userId = state.user.id;
   const [stompClient, setStompClient] = useState(null);

   return (

      <Formik
         initialValues={{
            text: ""
         }}
         validationSchema={
            Yup.object({
               text: Yup.string()
                  .max(100, "Must be 100 characters or less")
                  .required("Required")
            })
         }
         onSubmit={onSubmitHandler}
      >
         <Form>
            <Field type="text" placeholder="Comment..." name="text" />
            <ErrorMessage name="text" />
            <button type="submit">Add comment</button>
            <SockJsClient
               url="/websocket-comments"
               topics={[`/topic/comments/${compositionId}`]}
               onConnect={() => {
                  console.log("connected");
               }}
               onDisconnect={() => {
                  console.log("Disconnected");
               }}
               onMessage={getMessage}
               ref={(client) => {
                  setStompClient(client);
               }} />
         </Form>
      </Formik>

   );

   // отправляем
   function onSubmitHandler(values, { setSubmitting }) {
      let valuesAnswer = {};
      for (let key in values) {
         valuesAnswer[key] = values[key];
      }
      valuesAnswer.userId = userId;
      valuesAnswer.compositionId = compositionId;
      stompClient.sendMessage(`/app-socket/comment-add/${compositionId}`, JSON.stringify(valuesAnswer));
      setSubmitting(false);
   }

   // получаем ответ
   function getMessage(json) {
      dispatchComments({ type: "add", comment: json });
   }

}