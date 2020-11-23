import React, { useState, useEffect } from "react";
import "./style.scss";

export default function SectionCloudTags() {

   const [tags, setTags] = useState([]);

   useEffect(() => {
      fetch("/api/tags", { method: "GET" })
         .then(resp => resp.json())
         .then(json => setTags(json))
         .catch(err => console.log(err));
   }, []);

   return (
      <section>
         <h3>Tags</h3>
         <ul className="list-cloud-tags">
            {
               tags.map((tag) => {
                  return <li className="list-cloud-tags__point" key={tag.id}>{tag.text}</li>;
               })
            }
         </ul>
      </section>
   );
}