import { SecurityGuard } from "@duosoftbg/nacid-components";
import MyCorrespondencePage from "../views/pages/myCorrespondence/MyCorrespondencePage";
import React from "react";

export const myCorrespondenceRoutes = [
  {
    path: "/my-correspondence",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <MyCorrespondencePage />
      </SecurityGuard>
    ),
  },
];
