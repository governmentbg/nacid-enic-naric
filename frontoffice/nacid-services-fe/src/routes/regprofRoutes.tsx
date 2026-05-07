import { SecurityGuard } from "@duosoftbg/nacid-components";
import ServiceConfigWrapperPage from "../views/pages/ServiceConfigWrapperPage";
import { regprofServiceConfig } from "../config/servicesConfig";
import RegprofNewPage from "../views/pages/regprof/RegprofNewPage";
import React from "react";
import ServiceInfoPage from "../views/pages/ServiceInfoPage";

export const regprofRoutes = [
  {
    path: "/regprof",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <ServiceConfigWrapperPage serviceConfig={regprofServiceConfig} wrappedPage={<ServiceInfoPage />} />
      </SecurityGuard>
    ),
  },
  {
    path: "/regprof/new",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <ServiceConfigWrapperPage serviceConfig={regprofServiceConfig} wrappedPage={<RegprofNewPage />} />
      </SecurityGuard>
    ),
  },
];
