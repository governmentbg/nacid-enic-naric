export interface ApplicationFilterDetails {
  entryNum: string;
  entryNumExactMatch: boolean;
  ateCode: string;
  aseCode: string;
  applicantNameSearchType: string;
  apnStatusCode: string;
  docflowStatusCode: string;
  applicantName: string;
  diplomaOwnerName: string;
  dateFrom: string;
  dateTo: string;
  backofficeDateFrom: string;
  backofficeDateTo: string;
  excludedApplications: number[];
  universityName: string;
  universityId: string;
  responsibleUser: string;
  page: number;
  pageSize: number;
  order: string;
  orderBy: string;
}
