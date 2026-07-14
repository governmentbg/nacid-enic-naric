import { ApplicantType, Company, ServicesNaturalPerson, University } from "./personTypes";
import { ContactAddress, NaturalPersonNames } from "@duosoftbg/nacid-components";
import { ApplicationDocumentReceiveMethod } from "./applicationTypes";

export interface CommonApplicantDetails {
  applicationId?: number;

  applicant: ServicesApplicant;
  applicantHasRepresentative: boolean;
  representative: ServicesNaturalPerson;
  representativeCapacity: string;
  contactAddress: ContactAddress;
  resultReceive: ApplicationDocumentReceiveMethod;
  resultReceiveElectronic: ApplicationDocumentReceiveMethod;
  resultReceivePaper: ApplicationDocumentReceiveMethod;
  agreeDataUsage: boolean;
  documentsDeclaration: boolean;
  applicantTitleBefore: string;
  applicantTitleAfter: string;
  representativeCompanyIdentifier: string;
  certificateReceiveForms: string[];
  hasContactAddress?: boolean;
}

export interface ServicesApplicant {
  applicantType: ApplicantType;
  naturalPerson: ServicesNaturalPerson;
  company: Company;
  university: University;
}

export interface RudiApplicantDetails extends CommonApplicantDetails {
  diplomaNamesDifferent: boolean;
  diplomaNames: NaturalPersonNames;
}
