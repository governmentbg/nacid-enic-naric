import { SecurityGuard } from "@duosoftbg/nacid-components";
import ServiceConfigWrapperPage from "../views/pages/ServiceConfigWrapperPage";
import { heRecognitionServiceConfig } from "../config/servicesConfig";
import HERecognitionNewPage from "../views/pages/higherEducation/HERecognitionNewPage";
import React from "react";
import ServiceInfoPage from "../views/pages/ServiceInfoPage";

export const heRecognitionRoutes = [
  {
    path: "/he-recognition",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <ServiceConfigWrapperPage serviceConfig={heRecognitionServiceConfig} wrappedPage={<ServiceInfoPage />} />
      </SecurityGuard>
    ),
  },
  {
    path: "/he-recognition/new",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <ServiceConfigWrapperPage serviceConfig={heRecognitionServiceConfig} wrappedPage={<HERecognitionNewPage />} />
      </SecurityGuard>
    ),
  },
];
