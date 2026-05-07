import { DEFAULT_PAGE, DEFAULT_PAGE_SIZE, DESC_ORDER } from "@duosoftbg/nacid-components";
import { ApplicationFilterDetails } from "../../types/applicationTypes";
import { RudiApplication } from "../../utils/constants";
import { TextSearchType } from "@duosoftbg/nacid-backoffice-components";

export const sarFilterInitialValues: ApplicationFilterDetails = {
  entryNum: "",
  entryNumExactMatch: false,
  ateCode: RudiApplication.rudiApplicationType,
  aseCode: RudiApplication.rudiSARApplicationSybType,
  applicantNameSearchType: TextSearchType.CONTAINS_WORDS,
  apnStatusCode: "",
  docflowStatusCode: "",
  applicantName: "",
  diplomaOwnerName: "",
  dateFrom: "",
  dateTo: "",
  backofficeDateFrom: "",
  backofficeDateTo: "",
  excludedApplications: [],
  universityId: "",
  universityName: "",
  responsibleUser: "",
  page: DEFAULT_PAGE,
  pageSize: DEFAULT_PAGE_SIZE,
  order: DESC_ORDER,
  orderBy: "id",
};
