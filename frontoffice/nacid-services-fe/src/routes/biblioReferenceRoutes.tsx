import { SecurityGuard } from "@duosoftbg/nacid-components";
import ServiceConfigWrapperPage from "../views/pages/ServiceConfigWrapperPage";
import { biblioReferenceForeignConfig, biblioReferenceNacidConfig } from "../config/servicesConfig";
import React from "react";
import BiblioReferenceNewPage from "../views/pages/bibliographicReference/BiblioReferenceNewPage";
import ServiceInfoPage from "../views/pages/ServiceInfoPage";

export const biblioReferenceRoutes = [
  {
    path: "/bibliographic-reference-foreign",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <ServiceConfigWrapperPage serviceConfig={biblioReferenceForeignConfig} wrappedPage={<ServiceInfoPage />} />
      </SecurityGuard>
    ),
  },
  {
    path: "/bibliographic-reference-foreign/new",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <ServiceConfigWrapperPage
          serviceConfig={biblioReferenceForeignConfig}
          wrappedPage={<BiblioReferenceNewPage />}
        />
      </SecurityGuard>
    ),
  },
  {
    path: "/bibliographic-reference-nacid",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <ServiceConfigWrapperPage serviceConfig={biblioReferenceNacidConfig} wrappedPage={<ServiceInfoPage />} />
      </SecurityGuard>
    ),
  },
  {
    path: "/bibliographic-reference-nacid/new",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <ServiceConfigWrapperPage serviceConfig={biblioReferenceNacidConfig} wrappedPage={<BiblioReferenceNewPage />} />
      </SecurityGuard>
    ),
  },
];
