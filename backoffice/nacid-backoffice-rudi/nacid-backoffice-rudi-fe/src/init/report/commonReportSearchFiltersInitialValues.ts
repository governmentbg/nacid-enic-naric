import {
  CommissionStatus,
  CommonReportSearchFiltersDetails,
  TrainingInstitution,
  University,
} from "../../types/report/commonReportFiltersType";
import { DEFAULT_PAGE, DEFAULT_PAGE_SIZE, ReferenceDataDomain } from "@duosoftbg/nacid-components";
import { JoinType } from "../../utils/constants";
import { InitialValues } from "@duosoftbg/nacid-backoffice-components";

export const commonReportSearchFiltersInitialValues: CommonReportSearchFiltersDetails = {
  applicationType: {
    applicationTypes: [],
    sarServices: [
      { id: "A", name: "", domain: ReferenceDataDomain.SAR_APPLICATION_TYPE },
      { id: "R", name: "", domain: ReferenceDataDomain.SAR_APPLICATION_TYPE },
      { id: "S", name: "", domain: ReferenceDataDomain.SAR_APPLICATION_TYPE },
    ],
    sarServicesJoin: JoinType.joinAny,
  },
  commission: {
    isCommissionReviewed: false,
    isNotCommissionReviewed: false,
    sessionDateFrom: "",
    sessionDateTo: "",
    sessionNumberFrom: "",
    sessionNumberTo: "",
    commissionStatuses: [],
  },
  submissionMethod: InitialValues.report.submissionMethod,
  serviceType: {
    serviceType: InitialValues.forms.common.baseNomenclatureInitialValues,
  },
  diploma: {
    isStateApproved: false,
    diplomaYearFrom: "",
    diplomaYearTo: "",
  },
  status: {
    statuses: [],
  },
  application: InitialValues.report.application,
  documentType: InitialValues.report.documentType,
  documentReceiveMethod: {
    documentReceiveMethods: [],
  },
  applicationUserCreated: {
    users: [],
  },
  applicationResponsibleUser: {
    onlyActiveResponsibleUsers: false,
    users: [],
  },
  university: {
    onlyJointDegree: "",
    onlyWithDiplomaRegisters: false,
    universities: [],
  },
  legalApplicant: {
    legalNatureTypes: ["U"],
    legalApplicants: [],
    legalApplicantNames: [],
  },
  trainingInstitution: {
    trainingInstitutions: [],
  },
  diplomaSpeciality: {
    speciality: {
      specialities: [],
      specialityNames: [],
      originalSpecialities: [],
      originalSpecialityNames: [],
    },
    qualification: {
      qualifications: [],
      qualificationNames: [],
      originalQualifications: [],
      originalQualificationNames: [],
    },
    eduLevel: {
      originalEduLevels: [],
      originalEduLevelNames: [],
    },
  },
  recognizedDiplomaSpeciality: {
    specialities: [],
    specialityNames: [],
    qualifications: [],
    qualificationNames: [],
    eduLevels: [],
  },
  naturalPersonApplicant: {
    ...InitialValues.report.naturalPerson,
    personalDocumentTypes: [],
  },
  diplomaOwner: { ...InitialValues.report.naturalPerson, ...InitialValues.report.country },
  diplomaName: InitialValues.report.naturalPersonNames,
  representative: InitialValues.report.representative,
  docflowNumber: {
    docflowNumber: "",
  },
  page: DEFAULT_PAGE,
  pageSize: DEFAULT_PAGE_SIZE,
};

export const commissionStatusInitialValues: CommissionStatus = {
  commissionStatus: "",
  legalReasons: [],
};

export const universityInitialValues: University = {
  country: { id: "", name: "" },
  universities: [],
  universityNames: [],
  orgUniversityNames: [],
};

export const trainingInstitutionInitialValues: TrainingInstitution = {
  country: { id: "", name: "" },
  trainingInstitutions: [],
  trainingInstitutionNames: [],
};
