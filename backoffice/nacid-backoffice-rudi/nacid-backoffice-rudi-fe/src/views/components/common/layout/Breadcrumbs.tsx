import { BreadcrumbData, AppBreadcrumbs, InitialBreadcrumb } from "@duosoftbg/nacid-components";
import { ProcessEnvironments } from "@duosoftbg/nacid-backoffice-components";

export const breadcrumbData: BreadcrumbData = [
  { path: "/", name: "t.page.rudi", isHomePage: true },
  { path: "/sar-applications", name: "t.sar.applications" },
  { path: "/sar-applications/edit/:id", name: "l.page.edit" },
  { path: "/sar-applications/view/:id", name: "l.page.view" },
  { path: "/sar-applications/create", name: "t.newApplication" },
  { path: "/sar-e-apps", name: "t.sar.eApplications" },
  { path: "/sar-e-apps/:id/accept", name: "t.accept" },
  { path: "/sar-e-apps/:id/view", name: "l.page.view" },
  { path: "/udirec-applications", name: "t.diploma.recognitions" },
  { path: "/udirec-applications/edit/:id", name: "l.page.edit" },
  { path: "/udirec-applications/view/:id", name: "l.page.view" },
  { path: "/applications/view/:entryNum/:entryDate", name: "l.page.view" },
  { path: "/udirec-applications/create", name: "t.newApplication" },
  { path: "/udirec-e-apps", name: "t.udirec.eApplications" },
  { path: "/udirec-e-apps/:id/accept", name: "t.accept" },
  { path: "/udirec-e-apps/:id/view", name: "l.page.view" },
  { path: "/docrec-applications", name: "t.doctoral.degrees.recognitions" },
  { path: "/docrec-applications/edit/:id", name: "l.page.edit" },
  { path: "/docrec-applications/view/:id", name: "l.page.view" },
  { path: "/docrec-applications/create", name: "t.newApplication" },
  { path: "/docrec-e-apps", name: "t.docrec.eApplications" },
  { path: "/docrec-e-apps/:id/accept", name: "t.accept" },
  { path: "/docrec-e-apps/:id/view", name: "l.page.view" },
  { path: "/additional-documents-e-apps", name: "t.eAdditionalDocuments" },
  { path: "/additional-documents-e-apps/:id/accept", name: "t.accept" },
  { path: "/additional-documents-e-apps/:id/view", name: "l.page.view" },
  { path: "/duplicates-e-apps", name: "t.eDuplicate" },
  { path: "/duplicates-e-apps/:id/accept", name: "t.accept" },
  { path: "/duplicates-e-apps/:id/view", name: "l.page.view" },
  { path: "/commission-calendars", name: "t.commission.calendar" },
  { path: "/commission-calendars/edit/:calendarId", name: "t.commission.calendar.edit" },
  { path: "/commission-calendars/create", name: "t.commission.calendar.create" },
  { path: "/commission-calendars/view/:calendarId", name: "t.commission.calendar.view" },
  {
    path: "/commission-calendars/edit/:calendarId/commission-calendar-process/edit/:applicationId",
    name: "t.commission.calendar.processing.edit",
  },
  {
    path: "/sar-applications/edit/:id/commission-members/edit/:memberId",
    name: "t.application.experts.edit",
  },
  {
    path: "/udirec-applications/edit/:id/commission-members/edit/:memberId",
    name: "t.application.experts.edit",
  },
  {
    path: "/docrec-applications/edit/:id/commission-members/edit/:memberId",
    name: "t.application.experts.edit",
  },
  {
    path: "/udirec-applications/edit/:id/commission-members/add",
    name: "t.application.experts.add",
  },
  {
    path: "/docrec-applications/edit/:id/commission-members/add",
    name: "t.application.experts.add",
  },
  {
    path: "/udirec-applications/edit/:id/commission-members/view/:memberId",
    name: "t.application.experts.view",
  },
  {
    path: "/docrec-applications/edit/:id/commission-members/view/:memberId",
    name: "t.application.experts.view",
  },
  {
    path: "/sar-applications/edit/:id/commission-member-statements/edit/:statementId",
    name: "t.application.expert.statement.edit",
  },
  {
    path: "/udirec-applications/edit/:id/commission-member-statements/edit/:statementId",
    name: "t.application.expert.statement.edit",
  },
  {
    path: "/docrec-applications/edit/:id/commission-member-statements/edit/:statementId",
    name: "t.application.expert.statement.edit",
  },
  {
    path: "/sar-applications/edit/:id/commission-member-statements/add",
    name: "t.application.expert.statement.add",
  },
  {
    path: "/udirec-applications/edit/:id/commission-member-statements/add",
    name: "t.application.expert.statement.add",
  },
  {
    path: "/docrec-applications/edit/:id/commission-member-statements/add",
    name: "t.application.expert.statement.add",
  },
  {
    path: "/sar-applications/edit/:id/attachments/edit/:attachmentId/direction/:direction",
    name: "t.application.attachment.edit",
  },
  {
    path: "/udirec-applications/edit/:id/attachments/edit/:attachmentId/direction/:direction",
    name: "t.application.attachment.edit",
  },
  {
    path: "/docrec-applications/edit/:id/attachments/edit/:attachmentId/direction/:direction",
    name: "t.application.attachment.edit",
  },
  {
    path: "/sar-applications/edit/:id/attachments/add/direction/:direction",
    name: "t.application.attachment.create",
  },
  {
    path: "/udirec-applications/edit/:id/attachments/add/direction/:direction",
    name: "t.application.attachment.create",
  },
  {
    path: "/docrec-applications/edit/:id/attachments/add/direction/:direction",
    name: "t.application.attachment.create",
  },
  {
    path: "/sar-applications/edit/:id/commission-member-statements/view/:statementId",
    name: "t.application.expert.statement.view",
  },
  {
    path: "/udirec-applications/edit/:id/commission-member-statements/view/:statementId",
    name: "t.application.expert.statement.view",
  },
  {
    path: "/docrec-applications/edit/:id/commission-member-statements/view/:statementId",
    name: "t.application.expert.statement.view",
  },
  {
    path: "/sar-applications/edit/:id/commission-members/view/:memberId",
    name: "t.application.experts.view",
  },
  {
    path: "/sar-applications/edit/:id/commission-members/add",
    name: "t.application.experts.add",
  },
  {
    path: "/commission-calendars/edit/:calendarId/commission-calendar-process/view/:applicationId",
    name: "t.commission.calendar.processing.view",
  },
  {
    path: "/commission-calendars/view/:calendarId/commission-calendar-process/view/:applicationId",
    name: "t.commission.calendar.processing.view",
  },
  { path: "/common-report", name: "t.common.report" },
  { path: "/history", name: "t.page.history" },
];

export const initialBreadcrumb: InitialBreadcrumb = [
  { path: `${ProcessEnvironments.Module.BackOffice.Core}`, name: "t.backOfficeSystem", external: true },
];

const Breadcrumbs = () => {
  return <AppBreadcrumbs data={breadcrumbData} initial={initialBreadcrumb} />;
};

export default Breadcrumbs;
