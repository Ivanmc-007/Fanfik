import React from "react";
import ReactDom from "react-dom";
//import Main from "../../component/Main/index";

const title = React.createElement("h1", {}, "Hello From React!");

ReactDom.render(title, document.getElementById("root"));