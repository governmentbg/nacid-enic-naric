import React from "react";
import ApplicationCheckupPage from "../views/pages/appCheckup/ApplicationCheckupPage";
import { SecurityGuard } from "@duosoftbg/nacid-components";
import {
  biblioReferenceServiceMAConfig,
  docDegreesServiceMAConfig,
  docDeliveryServiceMAConfig,
  heRecognitionServiceMAConfig,
  inquiryServiceMAConfig,
  officialNotesServiceMAConfig,
  publicAccessServiceMAConfig,
  regprofServiceMAConfig,
  signalServiceMAConfig,
  suggestionServiceMAConfig,
  uniChecksServiceMAConfig,
} from "../config/servicesConfigMyApplications";
import HERecognitionCheckupPage from "../views/pages/higherEducation/HERecognitionCheckupPage";
import UniChecksCheckupPage from "../views/pages/uniChecks/UniChecksCheckupPage";
import DocDegreesCheckupPage from "../views/pages/doctorateDegrees/DocDegreesCheckupPage";
import RegprofCheckupPage from "../views/pages/regprof/RegprofCheckupPage";
import OfficialNotesCheckupPage from "../views/pages/officialNotes/OfficialNotesCheckupPage";
import InquiryCheckupPage from "../views/pages/inquiry/InquiryCheckupPage";
import BiblioReferenceCheckupPage from "../views/pages/bibliographicReference/BiblioReferenceCheckupPage";
import DocDeliveryCheckupPage from "../views/pages/documentDelivery/DocDeliveryCheckupPage";
import SignalCheckupPage from "../views/pages/signal/SignalCheckupPage";
import SuggestionCheckupPage from "../views/pages/suggestion/SuggestionCheckupPage";
import PublicAccessCheckupPage from "../views/pages/publicAccess/PublicAccessCheckupPage";

export const appCheckupRoutes = [
  {
    path: "/app-checkup",
    element: <ApplicationCheckupPage />,
  },
  {
    path: "/app-checkup/he-recognition",
    element: (
      <SecurityGuard doCheck={false}>
        <HERecognitionCheckupPage titleCode={heRecognitionServiceMAConfig.titleCode} />
      </SecurityGuard>
    ),
  },
  {
    path: "/app-checkup/uni-checks",
    element: (
      <SecurityGuard doCheck={false}>
        <UniChecksCheckupPage titleCode={uniChecksServiceMAConfig.titleCode} />
      </SecurityGuard>
    ),
  },
  {
    path: "/app-checkup/doc-degrees",
    element: (
      <SecurityGuard doCheck={false}>
        <DocDegreesCheckupPage titleCode={docDegreesServiceMAConfig.titleCode} />
      </SecurityGuard>
    ),
  },
  {
    path: "/app-checkup/regprof",
    element: (
      <SecurityGuard doCheck={false}>
        <RegprofCheckupPage titleCode={regprofServiceMAConfig.titleCode} />
      </SecurityGuard>
    ),
  },
  {
    path: "/app-checkup/official-notes",
    element: (
      <SecurityGuard doCheck={false}>
        <OfficialNotesCheckupPage titleCode={officialNotesServiceMAConfig.titleCode} />
      </SecurityGuard>
    ),
  },
  {
    path: "/app-checkup/inquiry",
    element: (
      <SecurityGuard doCheck={false}>
        <InquiryCheckupPage titleCode={inquiryServiceMAConfig.titleCode} />
      </SecurityGuard>
    ),
  },
  {
    path: "/app-checkup/bibliographic-reference",
    element: (
      <SecurityGuard doCheck={false}>
        <BiblioReferenceCheckupPage titleCode={biblioReferenceServiceMAConfig.titleCode} />
      </SecurityGuard>
    ),
  },
  {
    path: "/app-checkup/document-delivery",
    element: (
      <SecurityGuard doCheck={false}>
        <DocDeliveryCheckupPage titleCode={docDeliveryServiceMAConfig.titleCode} />
      </SecurityGuard>
    ),
  },
  {
    path: "/app-checkup/signal",
    element: (
      <SecurityGuard doCheck={false}>
        <SignalCheckupPage titleCode={signalServiceMAConfig.titleCode} />
      </SecurityGuard>
    ),
  },
  {
    path: "/app-checkup/suggestion",
    element: (
      <SecurityGuard doCheck={false}>
        <SuggestionCheckupPage titleCode={suggestionServiceMAConfig.titleCode} />
      </SecurityGuard>
    ),
  },
  {
    path: "/app-checkup/public-access",
    element: (
      <SecurityGuard doCheck={false}>
        <PublicAccessCheckupPage titleCode={publicAccessServiceMAConfig.titleCode} />
      </SecurityGuard>
    ),
  },
];
