import React from "react";
import ServicesPage from "../views/pages/ServicesPage";
import ServicesLayout from "../views/layouts/ServicesLayout";
import { Page404 } from "@duosoftbg/nacid-components";
import { heRecognitionRoutes } from "./heRecognitionRoutes";
import { docDegreesRoutes } from "./docDegreesRoutes";
import { regprofRoutes } from "./regprofRoutes";
import { uniChecksRoutes } from "./uniChecksRoutes";
import { docDeliveryRoutes } from "./docDeliveryRoutes";
import { biblioReferenceRoutes } from "./biblioReferenceRoutes";
import { officialNotesRoutes } from "./officialNotesRoutes";
import { inquiryRoutes } from "./inquiryRoutes";
import { signalRoutes } from "./signalRoutes";
import { suggestionRoutes } from "./suggestionRoutes";
import { myApplicationsRoutes } from "./myApplicationsRoutes";
import { appCheckupRoutes } from "./appCheckupRoutes";
import { publicAccessRoutes } from "./publicAccessRoutes";
import { myCorrespondenceRoutes } from "./myCorrespondenceRoutes";

const routes = [
  {
    path: "/",
    element: <ServicesLayout />,
    children: [
      { index: true, element: <ServicesPage /> },
      ...officialNotesRoutes,
      ...inquiryRoutes,
      ...biblioReferenceRoutes,
      ...docDeliveryRoutes,
      ...heRecognitionRoutes,
      ...docDegreesRoutes,
      ...regprofRoutes,
      ...uniChecksRoutes,
      ...suggestionRoutes,
      ...signalRoutes,
      ...publicAccessRoutes,
      ...myApplicationsRoutes,
      ...appCheckupRoutes,
      ...myCorrespondenceRoutes,
    ],
  },
  {
    path: "*",
    element: <ServicesLayout />,
    children: [
      {
        path: "*",
        element: <Page404 />,
      },
    ],
  },
];

export default routes;
