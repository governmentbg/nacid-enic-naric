import { SecurityGuard } from "@duosoftbg/nacid-components";
import ServiceConfigWrapperPage from "../views/pages/ServiceConfigWrapperPage";
import {
  uniCheckAuthenticityConfig,
  uniCheckRecommendationConfig,
  uniCheckStatusConfig,
} from "../config/servicesConfig";
import React from "react";
import UniChecksNewPage from "../views/pages/uniChecks/UniChecksNewPage";
import ServiceInfoPage from "../views/pages/ServiceInfoPage";

export const uniChecksRoutes = [
  {
    path: "/uni-checks-status",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <ServiceConfigWrapperPage serviceConfig={uniCheckStatusConfig} wrappedPage={<ServiceInfoPage />} />
      </SecurityGuard>
    ),
  },
  {
    path: "/uni-checks-status/new",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <ServiceConfigWrapperPage serviceConfig={uniCheckStatusConfig} wrappedPage={<UniChecksNewPage />} />
      </SecurityGuard>
    ),
  },
  {
    path: "/uni-checks-authenticity",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <ServiceConfigWrapperPage serviceConfig={uniCheckAuthenticityConfig} wrappedPage={<ServiceInfoPage />} />
      </SecurityGuard>
    ),
  },
  {
    path: "/uni-checks-authenticity/new",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <ServiceConfigWrapperPage serviceConfig={uniCheckAuthenticityConfig} wrappedPage={<UniChecksNewPage />} />
      </SecurityGuard>
    ),
  },
  {
    path: "/uni-checks-recommendation",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <ServiceConfigWrapperPage serviceConfig={uniCheckRecommendationConfig} wrappedPage={<ServiceInfoPage />} />
      </SecurityGuard>
    ),
  },
  {
    path: "/uni-checks-recommendation/new",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <ServiceConfigWrapperPage serviceConfig={uniCheckRecommendationConfig} wrappedPage={<UniChecksNewPage />} />
      </SecurityGuard>
    ),
  },
];
