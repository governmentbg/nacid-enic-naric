import React from "react";
import { Page403, Page404, SecurityGuard, SecurityRole } from "@duosoftbg/nacid-components";
import StandartLayout from "../views/layouts/StandartLayout";
import Home from "../views/pages/Home";
import SARApplicationsPage from "../views/pages/applications/sar/SARApplicationsPage";
import UdirecAppsPage from "../views/pages/applications/udirec/UdirecAppsPage";
import DocrecAppsPage from "../views/pages/applications/docrec/DocrecAppsPage";
import CommissionCalendarPage from "../views/pages/commissionCalendar/CommissionCalendarPage";
import CommissionCalendarEditPage from "../views/pages/commissionCalendar/CommissionCalendarEditPage";
import CommissionCalendarCreatePage from "../views/pages/commissionCalendar/CommissionCalendarCreatePage";
import ProcessingViewPage from "../views/components/main/commissionCalendar/details/view/ProcessingViewPage";
import CommissionCalendarViewPage from "../views/pages/commissionCalendar/CommissionCalendarViewPage";
import HistoryPage from "../views/pages/history/HistoryPage";
import CommonReportPage from "../views/pages/report/CommonReportPage";
import { AppType } from "@duosoftbg/nacid-backoffice-components";
import { RouteBuilder } from "./routeBuilder";
import AppViewByBackofficeNumberPage from "../views/pages/applications/common/AppViewByBackofficeNumberPage";
// TODO: NACIDSE-16
import GradingScales from "../views/pages/GradingScales";

const routes = [
  {
    path: "/",
    element: <StandartLayout />,
    children: [
      { index: true, element: <Home /> },
      {
        path: "sar-applications",
        element: (
          <SecurityGuard displayOnUnauthorized={<Page403 />} checkForRoles={[SecurityRole.RudiApplicationAccess]}>
            <SARApplicationsPage />
          </SecurityGuard>
        ),
      },

      {
        path: "udirec-applications",
        element: (
          <SecurityGuard displayOnUnauthorized={<Page403 />} checkForRoles={[SecurityRole.RudiApplicationAccess]}>
            <UdirecAppsPage />
          </SecurityGuard>
        ),
      },
      {
        path: "docrec-applications",
        element: (
          <SecurityGuard displayOnUnauthorized={<Page403 />} checkForRoles={[SecurityRole.RudiApplicationAccess]}>
            <DocrecAppsPage />
          </SecurityGuard>
        ),
      },
      {
        path: "common-report",
        element: (
          <SecurityGuard displayOnUnauthorized={<Page403 />} checkForRoles={[SecurityRole.RudiReportAccess]}>
            <CommonReportPage />
          </SecurityGuard>
        ),
      },
      {
        path: "applications/view/:entryNum/:entryDate",
        element: (
          <SecurityGuard displayOnUnauthorized={<Page403 />} checkForRoles={[SecurityRole.RudiApplicationAccess]}>
            <AppViewByBackofficeNumberPage />
          </SecurityGuard>
        ),
      },
      {
        path: "commission-calendars",
        element: (
          <SecurityGuard displayOnUnauthorized={<Page403 />} checkForRoles={[SecurityRole.CommissionCalendarAccess]}>
            <CommissionCalendarPage />
          </SecurityGuard>
        ),
      },
      {
        path: "commission-calendars/edit/:calendarId",
        element: (
          <SecurityGuard displayOnUnauthorized={<Page403 />} checkForRoles={[SecurityRole.CommissionCalendarEdit]}>
            <CommissionCalendarEditPage />
          </SecurityGuard>
        ),
      },
      {
        path: "commission-calendars/view/:calendarId",
        element: (
          <SecurityGuard displayOnUnauthorized={<Page403 />} checkForRoles={[SecurityRole.CommissionCalendarAccess]}>
            <CommissionCalendarViewPage />
          </SecurityGuard>
        ),
      },
      {
        path: "commission-calendars/edit/:calendarId/commission-calendar-process/view/:applicationId",
        element: (
          <SecurityGuard displayOnUnauthorized={<Page403 />} checkForRoles={[SecurityRole.CommissionCalendarAccess]}>
            <ProcessingViewPage />
          </SecurityGuard>
        ),
      },
      {
        path: "commission-calendars/view/:calendarId/commission-calendar-process/view/:applicationId",
        element: (
          <SecurityGuard displayOnUnauthorized={<Page403 />} checkForRoles={[SecurityRole.CommissionCalendarAccess]}>
            <ProcessingViewPage />
          </SecurityGuard>
        ),
      },
      {
        path: "commission-calendars/create",
        element: (
          <SecurityGuard displayOnUnauthorized={<Page403 />} checkForRoles={[SecurityRole.CommissionCalendarEdit]}>
            <CommissionCalendarCreatePage />
          </SecurityGuard>
        ),
      },
      {
        path: "history",
        element: (
          <SecurityGuard displayOnUnauthorized={<Page403 />} checkForRoles={[SecurityRole.HistoryAccess]}>
            <HistoryPage />
          </SecurityGuard>
        ),
      },
      ...RouteBuilder.routesByAppType([
        AppType.UDIREC_APPLICATION,
        AppType.DOCREC_APPLICATION,
        AppType.SAR_APPLICATION,
        AppType.ADDITIONAL_DOCUMENTS_RUDI,
        AppType.DUPLICATE_RUDI,
      ]),
    ],
  },
  {
    path: "*",
    element: <StandartLayout />,
    children: [
      {
        path: "*",
        element: <Page404 />,
      },
    ],
  },
  {
    path: "/grade-scale",
    element: <StandartLayout />,
    children: [{ index: true, element: <GradingScales /> }],
  },
];

export default routes;
