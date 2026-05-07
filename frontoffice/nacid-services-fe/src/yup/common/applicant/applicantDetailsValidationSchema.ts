import * as yup from "yup";

import { initializeYup } from "../../utils/yupUtils";
import {
  createCompanyValidationSchema,
  createNaturalPersonFullValidationSchema,
  createNaturalPersonLibValidationSchema,
  createNaturalPersonNamesValidationSchema,
  createUniversityValidationSchema,
} from "../../common/person/personValidationSchemas";
import {
  createContactAddressValidationSchema,
  createReceiverAddressValidationSchema,
} from "../../common/address/addressValidationSchemas";
import { ApplicantType } from "../../../types/common/personTypes";

export const createLibApplicantDetailsValidationSchema = () => {
  initializeYup(yup);

  const commonApplicantValidationSchema = yup.object({
    applicant: yup.object({
      applicantType: yup.mixed().oneOf(Object.values(ApplicantType)),
      naturalPerson: yup.mixed().when("applicantType", {
        is: (applType) => applType && applType === ApplicantType.NATURAL_PERSON,
        then: createNaturalPersonLibValidationSchema(),
      }),
      company: yup.mixed().when("applicantType", {
        is: (applType) => applType && applType === ApplicantType.COMPANY,
        then: createCompanyValidationSchema(),
      }),
      university: yup.mixed().when("applicantType", {
        is: (applType) => applType && applType === ApplicantType.UNIVERSITY,
        then: createUniversityValidationSchema(),
      }),
    }),
    representative: yup.mixed().when("applicantHasRepresentative", {
      is: true,
      then: createNaturalPersonLibValidationSchema(),
    }),
    contactAddress: yup.mixed().when("hasContactAddress", {
      is: true,
      then: createContactAddressValidationSchema(),
    }),
    resultReceive: yup.object({
      receiverAddress: yup.mixed().when("resultReceive", {
        is: (resultReceive) => resultReceive && resultReceive.documentRecipient,
        then: createReceiverAddressValidationSchema(),
      }),
    }),
  });

  return commonApplicantValidationSchema;
};

export const createFullApplicantDetailsValidationSchema = () => {
  initializeYup(yup);

  const commonApplicantValidationSchema = yup.object({
    applicant: yup.object({
      applicantType: yup.mixed().oneOf(Object.values(ApplicantType)),
      naturalPerson: yup.mixed().when("applicantType", {
        is: (applType) => applType && applType === ApplicantType.NATURAL_PERSON,
        then: createNaturalPersonFullValidationSchema(),
      }),
      company: yup.mixed().when("applicantType", {
        is: (applType) => applType && applType === ApplicantType.COMPANY,
        then: createCompanyValidationSchema(),
      }),
      university: yup.mixed().when("applicantType", {
        is: (applType) => applType && applType === ApplicantType.UNIVERSITY,
        then: createUniversityValidationSchema(),
      }),
    }),
    representative: yup.mixed().when("applicantHasRepresentative", {
      is: true,
      then: createNaturalPersonFullValidationSchema(),
    }),
    contactAddress: createContactAddressValidationSchema(),
  });

  return commonApplicantValidationSchema;
};

export const createRudiApplicantDetailsValidationSchema = () => {
  initializeYup(yup);

  const applicantValidationSchema = yup
    .object({
      diplomaNames: yup.mixed().when("diplomaNamesDifferent", {
        is: true,
        then: createNaturalPersonNamesValidationSchema(),
      }),
      certificateReceiveForms: yup.array().min(1),
      resultReceiveElectronic: yup.object({
        receiverAddress: yup.mixed().when("resultReceive", {
          is: (resultReceive) => resultReceive && resultReceive.documentRecipient,
          then: createReceiverAddressValidationSchema(),
        }),
      }),
      resultReceivePaper: yup.object({
        receiverAddress: yup.mixed().when("resultReceive", {
          is: (resultReceive) => resultReceive && resultReceive.documentRecipient,
          then: createReceiverAddressValidationSchema(),
        }),
      }),
    })
    .concat(createFullApplicantDetailsValidationSchema());

  return applicantValidationSchema;
};
