import { SecurityGuard } from "@duosoftbg/nacid-components";
import React from "react";
import MyApplicationsPage from "../views/pages/myApplications/MyApplicationsPage";
import ServiceConfigWrapperPage from "../views/pages/ServiceConfigWrapperPage";
import HERecognitionEditPage from "../views/pages/higherEducation/HERecognitionEditPage";
import HERecognitionViewPage from "../views/pages/higherEducation/HERecognitionViewPage";
import DocDegreesEditPage from "../views/pages/doctorateDegrees/DocDegreesEditPage";
import DocDegreesViewPage from "../views/pages/doctorateDegrees/DocDegreesViewPage";
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
import RegprofEditPage from "../views/pages/regprof/RegprofEditPage";
import RegprofViewPage from "../views/pages/regprof/RegprofViewPage";
import UniChecksEditPage from "../views/pages/uniChecks/UniChecksEditPage";
import UniChecksViewPage from "../views/pages/uniChecks/UniChecksViewPage";
import ApplicationSignPage from "../views/pages/appSign/ApplicationSignPage";
import OfficialNotesEditPage from "../views/pages/officialNotes/OfficialNotesEditPage";
import OfficialNotesViewPage from "../views/pages/officialNotes/OfficialNotesViewPage";
import InquiryEditPage from "../views/pages/inquiry/InquiryEditPage";
import InquiryViewPage from "../views/pages/inquiry/InquiryViewPage";
import BiblioReferenceEditPage from "../views/pages/bibliographicReference/BiblioReferenceEditPage";
import BiblioReferenceViewPage from "../views/pages/bibliographicReference/BiblioReferenceViewPage";
import DocDeliveryEditPage from "../views/pages/documentDelivery/DocDeliveryEditPage";
import DocDeliveryViewPage from "../views/pages/documentDelivery/DocDeliveryViewPage";
import SignalEditPage from "../views/pages/signal/SignalEditPage";
import SignalViewPage from "../views/pages/signal/SignalViewPage";
import SuggestionEditPage from "../views/pages/suggestion/SuggestionEditPage";
import SuggestionViewPage from "../views/pages/suggestion/SuggestionViewPage";
import PublicAccessEditPage from "../views/pages/publicAccess/PublicAccessEditPage";
import PublicAccessViewPage from "../views/pages/publicAccess/PublicAccessViewPage";

export const myApplicationsRoutes = [
  {
    path: "/my-applications",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <MyApplicationsPage />
      </SecurityGuard>
    ),
  },
  {
    path: "/my-applications/app-sign",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <ApplicationSignPage />
      </SecurityGuard>
    ),
  },
  {
    path: "/my-applications/he-recognition/edit",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <ServiceConfigWrapperPage
          serviceConfig={heRecognitionServiceMAConfig}
          wrappedPage={<HERecognitionEditPage />}
        />
      </SecurityGuard>
    ),
  },
  {
    path: "/my-applications/he-recognition/view",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <HERecognitionViewPage titleCode={heRecognitionServiceMAConfig.titleCode} />
      </SecurityGuard>
    ),
  },
  {
    path: "/my-applications/uni-checks/edit",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <ServiceConfigWrapperPage serviceConfig={uniChecksServiceMAConfig} wrappedPage={<UniChecksEditPage />} />
      </SecurityGuard>
    ),
  },
  {
    path: "/my-applications/uni-checks/view",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <UniChecksViewPage titleCode={uniChecksServiceMAConfig.titleCode} />
      </SecurityGuard>
    ),
  },
  {
    path: "/my-applications/doc-degrees/edit",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <ServiceConfigWrapperPage serviceConfig={docDegreesServiceMAConfig} wrappedPage={<DocDegreesEditPage />} />
      </SecurityGuard>
    ),
  },
  {
    path: "/my-applications/doc-degrees/view",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <DocDegreesViewPage titleCode={docDegreesServiceMAConfig.titleCode} />
      </SecurityGuard>
    ),
  },
  {
    path: "/my-applications/regprof/edit",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <ServiceConfigWrapperPage serviceConfig={regprofServiceMAConfig} wrappedPage={<RegprofEditPage />} />
      </SecurityGuard>
    ),
  },
  {
    path: "/my-applications/regprof/view",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <RegprofViewPage titleCode={regprofServiceMAConfig.titleCode} />
      </SecurityGuard>
    ),
  },
  {
    path: "/my-applications/official-notes/edit",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <ServiceConfigWrapperPage
          serviceConfig={officialNotesServiceMAConfig}
          wrappedPage={<OfficialNotesEditPage />}
        />
      </SecurityGuard>
    ),
  },
  {
    path: "/my-applications/official-notes/view",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <OfficialNotesViewPage titleCode={officialNotesServiceMAConfig.titleCode} />
      </SecurityGuard>
    ),
  },
  {
    path: "/my-applications/inquiry/edit",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <ServiceConfigWrapperPage serviceConfig={inquiryServiceMAConfig} wrappedPage={<InquiryEditPage />} />
      </SecurityGuard>
    ),
  },
  {
    path: "/my-applications/inquiry/view",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <InquiryViewPage titleCode={inquiryServiceMAConfig.titleCode} />
      </SecurityGuard>
    ),
  },
  {
    path: "/my-applications/bibliographic-reference/edit",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <ServiceConfigWrapperPage
          serviceConfig={biblioReferenceServiceMAConfig}
          wrappedPage={<BiblioReferenceEditPage />}
        />
      </SecurityGuard>
    ),
  },
  {
    path: "/my-applications/bibliographic-reference/view",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <BiblioReferenceViewPage titleCode={biblioReferenceServiceMAConfig.titleCode} />
      </SecurityGuard>
    ),
  },
  {
    path: "/my-applications/document-delivery/edit",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <ServiceConfigWrapperPage serviceConfig={docDeliveryServiceMAConfig} wrappedPage={<DocDeliveryEditPage />} />
      </SecurityGuard>
    ),
  },
  {
    path: "/my-applications/document-delivery/view",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <DocDeliveryViewPage titleCode={docDeliveryServiceMAConfig.titleCode} />
      </SecurityGuard>
    ),
  },
  {
    path: "/my-applications/signal/edit",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <ServiceConfigWrapperPage serviceConfig={signalServiceMAConfig} wrappedPage={<SignalEditPage />} />
      </SecurityGuard>
    ),
  },
  {
    path: "/my-applications/signal/view",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <SignalViewPage titleCode={signalServiceMAConfig.titleCode} />
      </SecurityGuard>
    ),
  },
  {
    path: "/my-applications/suggestion/edit",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <ServiceConfigWrapperPage serviceConfig={suggestionServiceMAConfig} wrappedPage={<SuggestionEditPage />} />
      </SecurityGuard>
    ),
  },
  {
    path: "/my-applications/suggestion/view",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <SuggestionViewPage titleCode={suggestionServiceMAConfig.titleCode} />
      </SecurityGuard>
    ),
  },
  {
    path: "/my-applications/public-access/edit",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <ServiceConfigWrapperPage serviceConfig={publicAccessServiceMAConfig} wrappedPage={<PublicAccessEditPage />} />
      </SecurityGuard>
    ),
  },
  {
    path: "/my-applications/public-access/view",
    element: (
      <SecurityGuard loginOnUnauthorized={true}>
        <PublicAccessViewPage titleCode={publicAccessServiceMAConfig.titleCode} />
      </SecurityGuard>
    ),
  },
];
