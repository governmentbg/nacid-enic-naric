export interface CorrespondenceListFilter {
  dateCreatedFrom: string;
  dateCreatedTo: string;
  dateReadFrom: string;
  dateReadTo: string;
  tempNumber: string;
  registrationNumber: string;
  registrationDateFrom: string;
  registrationDateTo: string;
  read: boolean;

  page: number;
  pageSize: number;
  orderBy: string;
  order: "asc" | "desc";
}
