import { SecurityGuard } from "@duosoftbg/nacid-components";
import ServiceConfigWrapperPage from "../views/pages/ServiceConfigWrapperPage";
import { inquiryCitingsConfig, inquiryFactorConfig } from "../config/servicesConfig";
import React from "react";
import InquiryNewPage from "../views/pages/inquiry/InquiryNewPage";
import ServiceInfoPage from "../views/pages/ServiceInfoPage";

export const inquiryRoutes = [
  {
    path: "/inquiry-citings",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <ServiceConfigWrapperPage serviceConfig={inquiryCitingsConfig} wrappedPage={<ServiceInfoPage />} />
      </SecurityGuard>
    ),
  },
  {
    path: "/inquiry-citings/new",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <ServiceConfigWrapperPage serviceConfig={inquiryCitingsConfig} wrappedPage={<InquiryNewPage />} />
      </SecurityGuard>
    ),
  },
  {
    path: "/inquiry-factor",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <ServiceConfigWrapperPage serviceConfig={inquiryFactorConfig} wrappedPage={<ServiceInfoPage />} />
      </SecurityGuard>
    ),
  },
  {
    path: "/inquiry-factor/new",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <ServiceConfigWrapperPage serviceConfig={inquiryFactorConfig} wrappedPage={<InquiryNewPage />} />
      </SecurityGuard>
    ),
  },
];
