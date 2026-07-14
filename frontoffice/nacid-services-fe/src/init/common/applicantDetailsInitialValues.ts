import {
  CommonApplicantDetails,
  RudiApplicantDetails,
  ServicesApplicant,
} from "../../types/common/applicantDetailsTypes";
import { initialCompany, initialServicesNaturalPerson, initialUniversity } from "./personInitialValues";
import { ApplicantType } from "../../types/common/personTypes";
import { initialContactAddress, initialReceiverAddress } from "./addressInitialValues";
import { DocumentReceiveMethod } from "@duosoftbg/nacid-components";

export const initialResultReceive: DocumentReceiveMethod = {
  id: "",
  name: "",
  documentRecipient: false,
  certificateReceiveFormCode: "",
};

export const initialServicesApplicant: ServicesApplicant = {
  applicantType: ApplicantType.NATURAL_PERSON,
  naturalPerson: initialServicesNaturalPerson,
  company: initialCompany,
  university: initialUniversity,
};

export const initialCommonApplicantDetails: CommonApplicantDetails = {
  applicant: initialServicesApplicant,
  applicantHasRepresentative: false,
  representative: initialServicesNaturalPerson,
  representativeCapacity: "",
  contactAddress: initialContactAddress,
  resultReceive: null,
  resultReceiveElectronic: null,
  resultReceivePaper: null,
  agreeDataUsage: false,
  documentsDeclaration: false,
  applicantTitleAfter: "",
  applicantTitleBefore: "",
  representativeCompanyIdentifier: "",
  certificateReceiveForms: null,
};

export const initialLibApplicantDetails: CommonApplicantDetails = {
  ...initialCommonApplicantDetails,
  agreeDataUsage: null,
  documentsDeclaration: null,
  hasContactAddress: false,
  contactAddress: null,
  resultReceive: { resultReceive: initialResultReceive, receiverAddress: initialReceiverAddress },
  resultReceiveElectronic: null,
  resultReceivePaper: null,
};

export const initialUniChecksApplicantDetails: CommonApplicantDetails = {
  ...initialCommonApplicantDetails,
  resultReceive: { resultReceive: initialResultReceive, receiverAddress: initialReceiverAddress },
  resultReceiveElectronic: null,
  resultReceivePaper: null,
};

export const initialRudiApplicantDetails: RudiApplicantDetails = {
  ...initialCommonApplicantDetails,
  diplomaNamesDifferent: false,
  diplomaNames: {
    firstName: "",
    middleName: "",
    lastName: "",
  },
  resultReceive: null,
  resultReceiveElectronic: { resultReceive: initialResultReceive, receiverAddress: initialReceiverAddress },
  resultReceivePaper: { resultReceive: initialResultReceive, receiverAddress: initialReceiverAddress },
  certificateReceiveForms: [],
};
