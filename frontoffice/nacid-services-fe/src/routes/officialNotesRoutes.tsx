import { SecurityGuard } from "@duosoftbg/nacid-components";
import ServiceConfigWrapperPage from "../views/pages/ServiceConfigWrapperPage";
import { officialNotesDissertationConfig, officialNotesPositionConfig } from "../config/servicesConfig";
import React from "react";
import OfficialNotesNewPage from "../views/pages/officialNotes/OfficialNotesNewPage";
import ServiceInfoPage from "../views/pages/ServiceInfoPage";

export const officialNotesRoutes = [
  {
    path: "/official-notes-dissertation",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <ServiceConfigWrapperPage serviceConfig={officialNotesDissertationConfig} wrappedPage={<ServiceInfoPage />} />
      </SecurityGuard>
    ),
  },
  {
    path: "/official-notes-dissertation/new",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <ServiceConfigWrapperPage
          serviceConfig={officialNotesDissertationConfig}
          wrappedPage={<OfficialNotesNewPage />}
        />
      </SecurityGuard>
    ),
  },
  {
    path: "/official-notes-position",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <ServiceConfigWrapperPage serviceConfig={officialNotesPositionConfig} wrappedPage={<ServiceInfoPage />} />
      </SecurityGuard>
    ),
  },
  {
    path: "/official-notes-position/new",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <ServiceConfigWrapperPage serviceConfig={officialNotesPositionConfig} wrappedPage={<OfficialNotesNewPage />} />
      </SecurityGuard>
    ),
  },
];
