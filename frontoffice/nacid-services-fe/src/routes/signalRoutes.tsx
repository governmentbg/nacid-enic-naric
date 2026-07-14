import { SecurityGuard } from "@duosoftbg/nacid-components";
import ServiceConfigWrapperPage from "../views/pages/ServiceConfigWrapperPage";
import { signalConfig } from "../config/servicesConfig";
import React from "react";
import SignalNewPage from "../views/pages/signal/SignalNewPage";
import ServiceInfoPage from "../views/pages/ServiceInfoPage";

export const signalRoutes = [
  {
    path: "/signal",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <ServiceConfigWrapperPage serviceConfig={signalConfig} wrappedPage={<ServiceInfoPage />} />
      </SecurityGuard>
    ),
  },
  {
    path: "/signal/new",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <ServiceConfigWrapperPage serviceConfig={signalConfig} wrappedPage={<SignalNewPage />} />
      </SecurityGuard>
    ),
  },
];
