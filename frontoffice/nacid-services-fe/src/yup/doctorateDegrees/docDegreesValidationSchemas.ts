import { initializeYup } from "@duosoftbg/nacid-components";
import * as yup from "yup";
import {
  createRudiEducationValidationSchema,
  createWithRecognitionCategoryValidationSchema,
  createWithPrevUniDiplomaValidationSchema,
} from "../common/education/educationValidationSchemas";
import { NUMBER_VALIDATION_REGEX, YEAR_VALIDATION_REGEX } from "../utils/regexExpressions";

export const createDocEducationValidationSchema = () => {
  initializeYup(yup);

  const educationValidationSchema = yup
    .object({
      graduationWay: yup.array().min(1),
      dissertationTheme: mixedHasDissertation(),
      dissertationThemeEn: mixedHasDissertation(),
      dissertationDate: mixedHasDissertation(),
      dissertationLanguage: mixedHasDissertationObjectId(),
      dissertationBiblioTitlesCount: mixedHasDissertationNumber(),
      dissertationPagesCount: mixedHasDissertationNumber(),
      dissertationAnnotation: mixedHasDissertation(),
      dissertationAnnotationEn: mixedHasDissertation(),
      startOfEducation: yup.mixed().when("recognitionCategory.id", {
        is: (id) => "DOC" === id,
        then: yup.string().required().matches(YEAR_VALIDATION_REGEX),
        otherwise: yup.string().optional().matches(YEAR_VALIDATION_REGEX),
      }),
      endOfEducation: yup.mixed().when("recognitionCategory.id", {
        is: (id) => "DOC" === id,
        then: yup.string().required().matches(YEAR_VALIDATION_REGEX),
        otherwise: yup.string().optional().matches(YEAR_VALIDATION_REGEX),
      }),
    })
    .concat(createRudiEducationValidationSchema())
    .concat(createWithRecognitionCategoryValidationSchema())
    .concat(createWithPrevUniDiplomaValidationSchema());

  return educationValidationSchema;
};

const mixedHasDissertation = () => {
  return yup.mixed().when("graduationWay", {
    is: (graduationWay) => hasDissertation(graduationWay),
    then: yup.string().required(),
  });
};

const mixedHasDissertationNumber = () => {
  return yup.mixed().when("graduationWay", {
    is: (graduationWay) => hasDissertation(graduationWay),
    then: yup.string().required().matches(NUMBER_VALIDATION_REGEX),
  });
};

const mixedHasDissertationObjectId = () => {
  return yup.mixed().when("graduationWay", {
    is: (graduationWay) => hasDissertation(graduationWay),
    then: yup.object({
      id: yup.string().required(),
    }),
  });
};

const hasDissertation = (graduationWay) => {
  //TODO - create constant for DIS
  return graduationWay && graduationWay.filter((gr) => gr.id === "DIS").length > 0;
};
