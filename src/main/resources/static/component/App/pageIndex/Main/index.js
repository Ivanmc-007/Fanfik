import React from "react";
import { Context } from "../../context/user/context";
import SectionSearch from "../../SectionSearch/index";
import SectionNewCompositions from "../SectionNewCompositions";
import SectionCloudTags from "../SectionCloudTags";
import "./style.scss";

export default function Main() {
   const { state } = React.useContext(Context);

   return (
      <main style={{ fontSize: "18px" }}>
         <SectionSearch className="section-search__page-index" />
         <SectionNewCompositions />
         <SectionCloudTags />
      </main>
   );
}