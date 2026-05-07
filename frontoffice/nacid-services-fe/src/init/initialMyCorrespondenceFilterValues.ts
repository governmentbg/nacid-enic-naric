import { CorrespondenceListFilter } from "../types/myCorrespondenceTypes";

export const initialMyCorrespondenceFilterValues: CorrespondenceListFilter = {
  dateCreatedFrom: "",
  dateCreatedTo: "",
  dateReadFrom: "",
  dateReadTo: "",
  registrationDateFrom: "",
  registrationDateTo: "",
  tempNumber: "",
  registrationNumber: "",
  read: null,

  order: "desc",
  orderBy: "dateSent",
  page: 0,
  pageSize: 10,
};
