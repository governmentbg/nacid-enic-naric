import { SecurityRole, SidebarData } from "@duosoftbg/nacid-components";
import { messagesBg, messagesEn } from "../../i18n";
import { ProcessEnvironments } from "@duosoftbg/nacid-backoffice-components";

export const sidebarData: SidebarData[] = [
  {
    id: "backoffice-sidebar",
    title: "",
    titleEn: "",
    pages: [
      {
        id: "applications",
        title: messagesBg.translation["l.applications"],
        titleEn: messagesEn.translation["l.applications"],
        accessRoles: [SecurityRole.RudiApplicationAccess],
        children: [
          {
            id: "udirec-applications",
            title: messagesBg.translation["t.diploma.recognitions.code"],
            titleEn: messagesEn.translation["t.diploma.recognitions.code"],
            accessRoles: [SecurityRole.RudiApplicationAccess],
            href: `${ProcessEnvironments.Module.BackOffice.Rudi}/udirec-applications`,
          },
          {
            id: "sar-applications",
            title: messagesBg.translation["t.sar.applications.code"],
            titleEn: messagesEn.translation["t.sar.applications.code"],
            accessRoles: [SecurityRole.RudiApplicationAccess],
            href: `${ProcessEnvironments.Module.BackOffice.Rudi}/sar-applications`,
          },
          {
            id: "docrec-applications",
            title: messagesBg.translation["t.doctoral.degrees.recognitions.code"],
            titleEn: messagesEn.translation["t.doctoral.degrees.recognitions.code"],
            accessRoles: [SecurityRole.RudiApplicationAccess],
            href: `${ProcessEnvironments.Module.BackOffice.Rudi}/docrec-applications`,
          },
        ],
      },
      {
        id: "e-apps",
        title: messagesBg.translation["t.eApplications.short"],
        titleEn: messagesEn.translation["t.eApplications.short"],
        accessRoles: [SecurityRole.EAppsAcceptance],
        roleOperator: "and",
        children: [
          {
            id: "udirec-e-apps",
            title: messagesBg.translation["t.diploma.recognitions.code"],
            titleEn: messagesEn.translation["t.diploma.recognitions.code"],
            href: `${ProcessEnvironments.Module.BackOffice.Rudi}/udirec-e-apps`,
          },
          {
            id: "sar-e-apps",
            title: messagesBg.translation["t.sar.applications.code"],
            titleEn: messagesEn.translation["t.sar.applications.code"],
            href: `${ProcessEnvironments.Module.BackOffice.Rudi}/sar-e-apps`,
          },
          {
            id: "docrec-e-apps",
            title: messagesBg.translation["t.doctoral.degrees.recognitions.code"],
            titleEn: messagesEn.translation["t.doctoral.degrees.recognitions.code"],
            href: `${ProcessEnvironments.Module.BackOffice.Rudi}/docrec-e-apps`,
          },
          {
            id: "additional-documents-e-apps",
            title: messagesBg.translation["t.eAdditionalDocuments"],
            titleEn: messagesEn.translation["t.eAdditionalDocuments"],
            href: `${ProcessEnvironments.Module.BackOffice.Rudi}/additional-documents-e-apps`,
          },
          {
            id: "duplicates-e-apps",
            title: messagesBg.translation["t.eDuplicate"],
            titleEn: messagesEn.translation["t.eDuplicate"],
            href: `${ProcessEnvironments.Module.BackOffice.Rudi}/duplicates-e-apps`,
          },
        ],
      },

      {
        id: "reports",
        title: messagesBg.translation["l.reports"],
        titleEn: messagesEn.translation["l.reports"],
        accessRoles: [SecurityRole.RudiReportAccess],
        children: [
          {
            id: "common-report",
            title: messagesBg.translation["t.common.report"],
            titleEn: messagesEn.translation["t.common.report"],
            accessRoles: [SecurityRole.RudiReportAccess],
            href: `${ProcessEnvironments.Module.BackOffice.Rudi}/common-report`,
          },
        ],
      },
      {
        id: "commission-calendars",
        title: messagesBg.translation["t.commission.calendar"],
        titleEn: messagesEn.translation["t.commission.calendar"],
        accessRoles: [SecurityRole.CommissionCalendarAccess],
        href: `${ProcessEnvironments.Module.BackOffice.Rudi}/commission-calendars`,
      },
      {
        id: "history",
        title: messagesBg.translation["t.page.history"],
        titleEn: messagesEn.translation["t.page.history"],
        accessRoles: [SecurityRole.HistoryAccess],
        href: `${ProcessEnvironments.Module.BackOffice.Rudi}/history`,
      },
    ],
  },
];
