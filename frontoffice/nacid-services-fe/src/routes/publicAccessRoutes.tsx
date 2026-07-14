import { SecurityGuard } from "@duosoftbg/nacid-components";
import ServiceConfigWrapperPage from "../views/pages/ServiceConfigWrapperPage";
import { publicAccessConfig } from "../config/servicesConfig";
import ServiceInfoPage from "../views/pages/ServiceInfoPage";
import React from "react";
import PublicAccessNewPage from "../views/pages/publicAccess/PublicAccessNewPage";

export const publicAccessRoutes = [
  {
    path: "/public-access",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <ServiceConfigWrapperPage serviceConfig={publicAccessConfig} wrappedPage={<ServiceInfoPage />} />
      </SecurityGuard>
    ),
  },
  {
    path: "/public-access/new",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <ServiceConfigWrapperPage serviceConfig={publicAccessConfig} wrappedPage={<PublicAccessNewPage />} />
      </SecurityGuard>
    ),
  },
];
