import {
  ReportApplicationType,
  ReportCountryType,
  ReportDocumentType,
  ReportNaturalPersonNamesType,
  ReportNaturalPersonType,
  ReportRepresentativeType,
  ReportStatusType,
  ReportSubmissionMethodType,
} from "@duosoftbg/nacid-backoffice-components";
import { BaseNomenclature, Country, ReferenceData } from "@duosoftbg/nacid-components";
import { LegalApplicant, ReportApplicationType as ReportAppType } from "./base/baseReportFiltersType";

export interface CommonReportSearchFiltersDetails {
  applicationType: ReportAppType;
  commission: {
    isCommissionReviewed: boolean;
    isNotCommissionReviewed: boolean;
    sessionDateFrom: string;
    sessionDateTo: string;
    sessionNumberFrom: string;
    sessionNumberTo: string;
    commissionStatuses: CommissionStatus[];
  };
  submissionMethod: ReportSubmissionMethodType;
  serviceType: {
    serviceType: BaseNomenclature;
  };
  diploma: {
    isStateApproved: boolean;
    diplomaYearFrom: string;
    diplomaYearTo: string;
  };
  status: {
    statuses: ReportStatusType[];
  };
  application: ReportApplicationType;
  documentType: ReportDocumentType;
  documentReceiveMethod: {
    documentReceiveMethods: BaseNomenclature[];
  };
  applicationUserCreated: {
    users: BaseNomenclature[];
  };
  applicationResponsibleUser: {
    onlyActiveResponsibleUsers: boolean;
    users: BaseNomenclature[];
  };
  university: {
    onlyJointDegree: boolean | "";
    onlyWithDiplomaRegisters: boolean;
    universities: University[];
  };
  legalApplicant: {
    legalNatureTypes: Array<string>;
    legalApplicants: LegalApplicant[];
    legalApplicantNames: Array<string>;
  };
  trainingInstitution: {
    trainingInstitutions: TrainingInstitution[];
  };
  diplomaSpeciality: {
    speciality: {
      specialities: BaseNomenclature[];
      specialityNames: Array<string>;
      originalSpecialities: BaseNomenclature[];
      originalSpecialityNames: Array<string>;
    };
    qualification: {
      qualifications: BaseNomenclature[];
      qualificationNames: Array<string>;
      originalQualifications: BaseNomenclature[];
      originalQualificationNames: Array<string>;
    };
    eduLevel: {
      originalEduLevels: BaseNomenclature[];
      originalEduLevelNames: Array<string>;
    };
  };
  recognizedDiplomaSpeciality: {
    specialities: BaseNomenclature[];
    specialityNames: Array<string>;
    qualifications: BaseNomenclature[];
    qualificationNames: Array<string>;
    eduLevels: ReferenceData[];
  };
  naturalPersonApplicant: ReportNaturalPersonType & {
    personalDocumentTypes: BaseNomenclature[];
  };
  diplomaOwner: ReportNaturalPersonType & ReportCountryType;
  diplomaName: ReportNaturalPersonNamesType;
  representative: ReportRepresentativeType;
  docflowNumber: {
    docflowNumber: string;
  };
  page: number;
  pageSize: number;
}

export interface CommissionStatus {
  commissionStatus: string;
  legalReasons: BaseNomenclature[];
}

export interface University {
  country: Country;
  universities: BaseNomenclature[];
  universityNames: Array<string>;
  orgUniversityNames: Array<string>;
}

export interface TrainingInstitution {
  country: Country;
  trainingInstitutions: BaseNomenclature[];
  trainingInstitutionNames: Array<string>;
}
