import React, { useState, useEffect } from "react";
import { useLocation, useParams } from "react-router-dom";
import Pagination from "react-js-pagination";
import "bootstrap/dist/css/bootstrap.min.css";
import "./style.scss";

export default function Main() {
   const location = useLocation();
   const params = useParams();
   const { index } = location.state || 0;
   const [activePage, setActivePage] = useState(index);
   const [paragraphs, setParagraphs] = useState([]);
   const [paragraph, setParagraph] = useState(null);

   useEffect(() => {
      fetch(`/api/paragraphs/compositions/${params.id}`, {
         method: "GET"
      })
         .then(resp => resp.json())
         .then(json => {
            setParagraphs(json);
            setParagraph(json[index]);
            setActivePage(index + 1);
         })
         .catch(err => console.log(err));
   }, []);

   return (
      <main className="main">
         <section>
            <div className="paragraph">
               <h2 className="paragraph__title">{paragraph && paragraph.name}</h2>
               <div className="paragraph__text">
                  <img src={paragraph && paragraph.linkToImage || ""} className="paragraph__image" />
                  {paragraph && paragraph.text}
               </div>
            </div>

            <div>
               <Pagination
                  itemClass="page-item"
                  linkClass="page-link"
                  activePage={activePage}
                  itemsCountPerPage={1}
                  totalItemsCount={paragraphs.length}
                  pageRangeDisplayed={1}
                  onChange={handlePageChange}
               />
            </div>

         </section>
      </main>
   );

   function handlePageChange(pageNumber) {
      setActivePage(pageNumber);
      setParagraph(paragraphs[pageNumber - 1]);
   }
}