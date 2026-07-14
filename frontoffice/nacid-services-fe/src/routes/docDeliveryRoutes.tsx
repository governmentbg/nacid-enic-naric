import { SecurityGuard } from "@duosoftbg/nacid-components";
import ServiceConfigWrapperPage from "../views/pages/ServiceConfigWrapperPage";
import { docDeliveryLibrariesConfig, docDeliveryNacidConfig } from "../config/servicesConfig";
import React from "react";
import DocDeliveryNewPage from "../views/pages/documentDelivery/DocDeliveryNewPage";
import ServiceInfoPage from "../views/pages/ServiceInfoPage";

export const docDeliveryRoutes = [
  {
    path: "/document-delivery-libraries",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <ServiceConfigWrapperPage serviceConfig={docDeliveryLibrariesConfig} wrappedPage={<ServiceInfoPage />} />
      </SecurityGuard>
    ),
  },
  {
    path: "/document-delivery-libraries/new",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <ServiceConfigWrapperPage serviceConfig={docDeliveryLibrariesConfig} wrappedPage={<DocDeliveryNewPage />} />
      </SecurityGuard>
    ),
  },
  {
    path: "/document-delivery-nacid",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <ServiceConfigWrapperPage serviceConfig={docDeliveryNacidConfig} wrappedPage={<ServiceInfoPage />} />
      </SecurityGuard>
    ),
  },
  {
    path: "/document-delivery-nacid/new",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <ServiceConfigWrapperPage serviceConfig={docDeliveryNacidConfig} wrappedPage={<DocDeliveryNewPage />} />
      </SecurityGuard>
    ),
  },
];
