import React, { useState } from "react";
import { Link } from "react-router-dom";
import FormSearch from "../FormSearch";

export default function SectionSearch(props) {
   const [compositions, setCompositions] = useState([]);
   const { className } = props || "";

   return (
      <section className={className}>
         <FormSearch setCompositions={setCompositions} />
         <ul>
            {
               compositions.map((composition) => {
                  const link = `compositions/${composition.id}`;
                  return (
                     <li key={composition.id}>
                        <Link to={link}>{composition.name}</Link>

                        <ListTag tags={composition.tags} />
                     </li>
                  );
               })
            }
         </ul>
      </section>
   );
}

// function FormSearch(props) {
//    const { setCompositions } = props;

//    return (
//       <Formik
//          initialValues={{
//             searchText: ""
//          }}
//          onSubmit={onSubmitHandler}
//       >
//          <Form>
//             <Field type="text" name="searchText" />
//             <button type="submit">Search</button>
//          </Form>
//       </Formik >
//    );

//    function onSubmitHandler(values, { setSubmitting }) {
//       console.log("*");
//       if (values.searchText.length === 0) {
//          setCompositions([]);
//          setSubmitting(false);
//          return;
//       }

//       fetch(`/api/compositions/search?text=${values.searchText}`, { method: "GET" })
//          .then(resp => resp.json())
//          .then(json => setCompositions(json))
//          .catch(err => console.log("ERR", err))
//          .finally(setSubmitting(false));
//    }
// }

function ListTag(props) {
   const { tags } = props || [];
   return (
      <ul>
         {
            tags.map(tag => (<li key={tag.id}>{tag.text}</li>))
         }
      </ul>
   );
}