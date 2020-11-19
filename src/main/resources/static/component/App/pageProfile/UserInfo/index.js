import React from "react";

export default function UserInfo(props) {
   const { user } = props;
   return (
      <React.Fragment>
         <div>Id: {user && user.id}</div>
         <div>Name: {user && user.name}</div>
         <div>Email: {user && user.email}</div>
      </React.Fragment>
   );
}