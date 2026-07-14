import { SecurityGuard } from "@duosoftbg/nacid-components";
import ServiceConfigWrapperPage from "../views/pages/ServiceConfigWrapperPage";
import { docDegreesDoctorOfScienceServiceConfig, docDegreesDoctorServiceConfig } from "../config/servicesConfig";
import DocDegreesNewPage from "../views/pages/doctorateDegrees/DocDegreesNewPage";
import React from "react";
import ServiceInfoPage from "../views/pages/ServiceInfoPage";

export const docDegreesRoutes = [
  {
    path: "/doc-degrees-doctor",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <ServiceConfigWrapperPage serviceConfig={docDegreesDoctorServiceConfig} wrappedPage={<ServiceInfoPage />} />
      </SecurityGuard>
    ),
  },
  {
    path: "/doc-degrees-doctor/new",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <ServiceConfigWrapperPage serviceConfig={docDegreesDoctorServiceConfig} wrappedPage={<DocDegreesNewPage />} />
      </SecurityGuard>
    ),
  },
  {
    path: "/doc-degrees-doctor-science",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <ServiceConfigWrapperPage
          serviceConfig={docDegreesDoctorOfScienceServiceConfig}
          wrappedPage={<ServiceInfoPage />}
        />
      </SecurityGuard>
    ),
  },
  {
    path: "/doc-degrees-doctor-science/new",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <ServiceConfigWrapperPage
          serviceConfig={docDegreesDoctorOfScienceServiceConfig}
          wrappedPage={<DocDegreesNewPage />}
        />
      </SecurityGuard>
    ),
  },
];
