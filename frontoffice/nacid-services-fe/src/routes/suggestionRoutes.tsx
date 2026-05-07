import { SecurityGuard } from "@duosoftbg/nacid-components";
import ServiceConfigWrapperPage from "../views/pages/ServiceConfigWrapperPage";
import { suggestionConfig } from "../config/servicesConfig";
import React from "react";
import SuggestionNewPage from "../views/pages/suggestion/SuggestionNewPage";
import ServiceInfoPage from "../views/pages/ServiceInfoPage";

export const suggestionRoutes = [
  {
    path: "/suggestion",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <ServiceConfigWrapperPage serviceConfig={suggestionConfig} wrappedPage={<ServiceInfoPage />} />
      </SecurityGuard>
    ),
  },
  {
    path: "/suggestion/new",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <ServiceConfigWrapperPage serviceConfig={suggestionConfig} wrappedPage={<SuggestionNewPage />} />
      </SecurityGuard>
    ),
  },
];
