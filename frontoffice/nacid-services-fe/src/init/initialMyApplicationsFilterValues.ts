import { ApplicationListFilter } from "../types/myApplicationsTypes";

export const initialMyApplicationsFilterValues: ApplicationListFilter = {
  dateCreatedFrom: "",
  dateCreatedTo: "",
  applicationSubtype: null,
  lastStatusName: null,
  entryDateFrom: "",
  entryDateTo: "",
  tempNumber: "",
  entryNumber: "",
  statute: false,
  authenticity: false,
  recommendation: false,

  order: "desc",
  orderBy: "lastSubmissionDate",
  page: 0,
  pageSize: 10,
};
