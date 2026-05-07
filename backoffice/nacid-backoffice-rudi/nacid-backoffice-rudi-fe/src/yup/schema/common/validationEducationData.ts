import * as yup from "yup";
import { initializeYup } from "@duosoftbg/nacid-backoffice-components";
import i18n from "i18next";
import { validationDiplomaTypeDetailsSchema } from "./validationDiplomaTypeDetails";
import { validationForeignUniversityDetailsSchema } from "./validationForeignUniversityDetails";
import { validationTrainingLocationsDetailsSchema } from "./validationTrainingLocationsDetails";
import { validationDiplomaForPreviousHigherEducationSchema } from "./validationDiplomaForPreviousHigherEducation";
import { validationDissertationDetails } from "./validationDissertationDetails";
import { validationSecondaryEducationDiplomaDetailsSchema } from "./validationSecondaryEducationDiplomaDetails";

export const editEducationDataValidationSchema = () => {
  initializeYup(yup);

  const regexIntMsg = i18n.t("m.validation.regex.integer");
  const regexDecMsg = i18n.t("m.validation.regex.credits");

  const schema = yup.object({
    creditHours: yup
      .string()
      .trim()
      .optional()
      .nullable()
      .matches(/^(\d{1,9})$/, { message: regexIntMsg, excludeEmptyString: true }),
    ectsCredits: yup
      .string()
      .trim()
      .optional()
      .nullable()
      .matches(/^(\d{1,9})$/, { message: regexIntMsg, excludeEmptyString: true }),
    trainingDuration: yup
      .string()
      .trim()
      .optional()
      .nullable()
      .matches(/^(\d{1,6}|\d{0,6}\.\d{1,2})$/, { message: regexDecMsg, excludeEmptyString: true }),
    credits: yup
      .string()
      .trim()
      .optional()
      .nullable()
      .matches(/^(\d{1,6}|\d{0,6}\.\d{1,2})$/, { message: regexDecMsg, excludeEmptyString: true }),
    thesisBibliography: yup
      .string()
      .trim()
      .optional()
      .nullable()
      .matches(/^(\d{1,9})$/, { message: regexIntMsg, excludeEmptyString: true }),
    thesisVolume: yup
      .string()
      .trim()
      .optional()
      .nullable()
      .matches(/^(\d{1,9})$/, { message: regexIntMsg, excludeEmptyString: true }),
    graduationWayNotes: yup.mixed().when("graduationWayOther", {
      is: true,
      then: yup.string().optional().nullable().max(2000, i18n.t("validation.charCount.invalid.2000")),
      otherwise: yup.string().optional().nullable(),
    }),
    recognitionPurposeNotes: yup.mixed().when("recognitionPurposeOther", {
      is: true,
      then: yup.string().optional().nullable().max(255, i18n.t("validation.charCount.invalid.255")),
      otherwise: yup.string().optional().nullable(),
    }),
  });

  return schema
    .concat(validationDiplomaTypeDetailsSchema())
    .concat(validationForeignUniversityDetailsSchema())
    .concat(validationTrainingLocationsDetailsSchema())
    .concat(validationSecondaryEducationDiplomaDetailsSchema())
    .concat(validationDiplomaForPreviousHigherEducationSchema())
    .concat(validationDissertationDetails());
};
