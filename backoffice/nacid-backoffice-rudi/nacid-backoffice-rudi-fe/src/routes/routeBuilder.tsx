import { AppType, RouteManager } from "@duosoftbg/nacid-backoffice-components";
import { Page403, SecurityGuard, SecurityRole } from "@duosoftbg/nacid-components";
import StatementsEditPage from "../views/pages/applications/common/statements/StatementsEditPage";
import StatementsAddPage from "../views/pages/applications/common/statements/StatementsAddPage";
import StatementViewPage from "../views/pages/applications/common/statements/StatementViewPage";
import React from "react";
import ExpertEditPage from "../views/pages/applications/common/expert/ExpertEditPage";
import ExpertViewPage from "../views/pages/applications/common/expert/ExpertViewPage";
import ExpertAddPage from "../views/pages/applications/common/expert/ExpertAddPage";
import FoAppAcceptPage from "../views/pages/applications/common/FoAppAcceptPage";
import FoDocrecApplicationsPage from "../views/pages/applications/docrec/FoDocrecApplicationsPage";
import FoSARApplicationsPage from "../views/pages/applications/sar/FoSARApplicationsPage";
import FoUdirecApplicationsPage from "../views/pages/applications/udirec/FoUdirecApplicationsPage";
import FoAppViewPage from "../views/pages/applications/common/FoAppViewPage";
import AppCreatePage from "../views/pages/applications/common/AppCreatePage";
import AppEditPage from "../views/pages/applications/common/AppEditPage";
import AppViewPage from "../views/pages/applications/common/AppViewPage";
import ProcessingEditPage from "../views/components/main/commissionCalendar/details/tabs/processing/ProcessingEditPage";
import FoAdditionalDocumentsAppsPage from "../views/pages/applications/additionalDocuments/FoAdditionalDocumentsAppsPage";
import FoAppAdditionalDocumentsAcceptPage from "../views/pages/applications/additionalDocuments/FoAppAdditionalDocumentsAcceptPage";
import FoAppDuplicateAcceptPage from "../views/pages/applications/duplicate/FoAppDuplicateAcceptPage";
import FoDuplicateAppsPage from "../views/pages/applications/duplicate/FoDuplicateAppsPage";

export const RouteBuilder = {
  routesByAppType: (appTypes: AppType[]) => {
    const routes = [];

    appTypes.forEach((appType) => {
      const prefix = RouteManager.getRoutePrefix(appType);

      routes.push({
        path: `${prefix}-applications/create`,
        element: (
          <SecurityGuard displayOnUnauthorized={<Page403 />} checkForRoles={[SecurityRole.RudiApplicationCreate]}>
            <AppCreatePage appType={appType} />
          </SecurityGuard>
        ),
      });

      routes.push({
        path: `${prefix}-applications/view/:id`,
        element: (
          <SecurityGuard displayOnUnauthorized={<Page403 />} checkForRoles={[SecurityRole.RudiApplicationAccess]}>
            <AppViewPage appType={appType} />
          </SecurityGuard>
        ),
      });

      routes.push({
        path: `${prefix}-applications/edit/:id`,
        element: (
          <SecurityGuard displayOnUnauthorized={<Page403 />} checkForRoles={[SecurityRole.RudiApplicationEdit]}>
            <AppEditPage appType={appType} />
          </SecurityGuard>
        ),
      });

      //Statements
      routes.push({
        path: `${prefix}-applications/edit/:id/commission-member-statements/view/:statementId`,
        element: (
          <SecurityGuard displayOnUnauthorized={<Page403 />} checkForRoles={[SecurityRole.RudiApplicationAccess]}>
            <StatementViewPage />
          </SecurityGuard>
        ),
      });
      routes.push({
        path: `${prefix}-applications/edit/:id/commission-member-statements/edit/:statementId`,
        element: (
          <SecurityGuard displayOnUnauthorized={<Page403 />} checkForRoles={[SecurityRole.RudiApplicationEdit]}>
            <StatementsEditPage appType={appType} />
          </SecurityGuard>
        ),
      });
      routes.push({
        path: `${prefix}-applications/edit/:id/commission-member-statements/add`,
        element: (
          <SecurityGuard displayOnUnauthorized={<Page403 />} checkForRoles={[SecurityRole.RudiApplicationEdit]}>
            <StatementsAddPage appType={appType} />
          </SecurityGuard>
        ),
      });

      routes.push({
        path: "commission-calendars/edit/:calendarId/commission-calendar-process/edit/:applicationId",
        element: (
          <SecurityGuard displayOnUnauthorized={<Page403 />} checkForRoles={[SecurityRole.CommissionCalendarEdit]}>
            <ProcessingEditPage appType={appType} />
          </SecurityGuard>
        ),
      });

      routes.push({
        path: `${prefix}-applications/edit/:id/commission-members/edit/:memberId`,
        element: (
          <SecurityGuard displayOnUnauthorized={<Page403 />} checkForRoles={[SecurityRole.RudiApplicationEdit]}>
            <ExpertEditPage appType={appType} />
          </SecurityGuard>
        ),
      });

      routes.push({
        path: `${prefix}-applications/edit/:id/commission-members/view/:memberId`,
        element: (
          <SecurityGuard displayOnUnauthorized={<Page403 />} checkForRoles={[SecurityRole.RudiApplicationAccess]}>
            <ExpertViewPage appType={appType} />
          </SecurityGuard>
        ),
      });

      routes.push({
        path: `${prefix}-applications/edit/:id/commission-members/add`,
        element: (
          <SecurityGuard displayOnUnauthorized={<Page403 />} checkForRoles={[SecurityRole.RudiApplicationEdit]}>
            <ExpertAddPage appType={appType} />
          </SecurityGuard>
        ),
      });

      routes.push({
        path: `${prefix}-e-apps`,
        element: (
          <SecurityGuard displayOnUnauthorized={<Page403 />} checkForRoles={[SecurityRole.EAppsAcceptance]}>
            {appType === AppType.SAR_APPLICATION && <FoSARApplicationsPage />}
            {appType === AppType.UDIREC_APPLICATION && <FoUdirecApplicationsPage />}
            {appType === AppType.DOCREC_APPLICATION && <FoDocrecApplicationsPage />}
            {appType === AppType.ADDITIONAL_DOCUMENTS_RUDI && <FoAdditionalDocumentsAppsPage />}
            {appType === AppType.DUPLICATE_RUDI && <FoDuplicateAppsPage />}
          </SecurityGuard>
        ),
      });
      routes.push({
        path: `${prefix}-e-apps/:id/view`,
        element: (
          <SecurityGuard displayOnUnauthorized={<Page403 />} checkForRoles={[SecurityRole.EAppsAcceptance]}>
            <FoAppViewPage appType={appType} />
          </SecurityGuard>
        ),
      });
      routes.push({
        path: `${prefix}-e-apps/:id/accept`,
        element: (
          <SecurityGuard displayOnUnauthorized={<Page403 />} checkForRoles={[SecurityRole.EAppsAcceptance]}>
            {appType === AppType.DUPLICATE_RUDI && <FoAppDuplicateAcceptPage />}
            {appType === AppType.ADDITIONAL_DOCUMENTS_RUDI && <FoAppAdditionalDocumentsAcceptPage />}
            {appType !== AppType.ADDITIONAL_DOCUMENTS_RUDI && appType !== AppType.DUPLICATE_RUDI && (
              <FoAppAcceptPage appType={appType} />
            )}
          </SecurityGuard>
        ),
      });
    });

    return routes;
  },
};
