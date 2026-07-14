import * as yup from "yup";

import {
  createNaturalPersonNamesValidationSchema,
  createNaturalPersonIdentifierValidationSchema,
} from "../common/person/personValidationSchemas";
import { createFullApplicantDetailsValidationSchema } from "../common/applicant/applicantDetailsValidationSchema";
import { initializeYup } from "@duosoftbg/nacid-components";
import { createReceiverAddressValidationSchema } from "../common/address/addressValidationSchemas";

export const createRegprofApplicantDetailsValidationSchema = () => {
  initializeYup(yup);

  const applicantValidationSchema = yup
    .object({
      qualificationNames: yup.mixed().when("qualificationNamesDifferent", {
        is: true,
        then: createNaturalPersonNamesValidationSchema().concat(createNaturalPersonIdentifierValidationSchema()),
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
