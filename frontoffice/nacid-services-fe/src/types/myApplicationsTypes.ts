export interface ApplicationListFilter {
  dateCreatedFrom: string;
  dateCreatedTo: string;
  lastStatusName: string;
  applicationSubtype: string;
  tempNumber: string;
  entryNumber: string;
  entryDateFrom: string;
  entryDateTo: string;
  statute: boolean;
  authenticity: boolean;
  recommendation: boolean;

  page: number;
  pageSize: number;
  orderBy: string;
  order: "asc" | "desc";
}
