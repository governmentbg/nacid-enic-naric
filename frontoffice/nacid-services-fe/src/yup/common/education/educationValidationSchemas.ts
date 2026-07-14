import * as yup from "yup";
import { DECIMAL_NUMBER_VALIDATION_REGEX, YEAR_VALIDATION_REGEX } from "../../utils/regexExpressions";
import { initializeYup } from "../../utils/yupUtils";

export const createRudiEducationValidationSchema = () => {
  initializeYup(yup);

  const educationValidationSchema = yup.object({
    universitiesData: yup.array().of(
      yup.object().shape({
        name: yup.string().required(),
      })
    ),
    educationPlaces: yup.array().of(
      yup.object().shape({
        country: yup.object({
          id: yup.string().required(),
        }),
        city: yup.string().required(),
      })
    ),
    educationDuration: yup.string().optional().matches(DECIMAL_NUMBER_VALIDATION_REGEX),
    credits: yup.string().optional().matches(DECIMAL_NUMBER_VALIDATION_REGEX),
    educationDurationType: yup.mixed().when("educationDuration", {
      is: (duration) => duration && duration !== "",
      then: yup.object({
        id: yup.string().required(),
      }),
    }),
    originalGainedLevel: yup.string().required(),
    originalGainedLevelTranslated: yup.string().required(),
  });

  return educationValidationSchema;
};

export const createRudiEducationStartEndValidationSchema = () => {
  initializeYup(yup);

  const educationValidationSchema = yup.object({
    startOfEducation: yup.string().required().matches(YEAR_VALIDATION_REGEX),
    endOfEducation: yup.string().required().matches(YEAR_VALIDATION_REGEX),
  });
  return educationValidationSchema;
};

export const createWithPrevUniDiplomaValidationSchema = () => {
  initializeYup(yup);

  const withPrevDiplomaValidationSchema = yup.object({
    previousUniversityDiploma: yup.object({
      graduationYear: yup.string().optional().matches(YEAR_VALIDATION_REGEX),
    }),
  });
  return withPrevDiplomaValidationSchema;
};

export const createWithRecognitionCategoryValidationSchema = () => {
  initializeYup(yup);

  const schema = yup.object({
    recognitionCategory: yup.object({
      id: yup.string().required(),
    }),
  });
  return schema;
};

export const createWithSpecialitiesValidationSchema = () => {
  initializeYup(yup);

  const withSpecialitiesValidationSchema = yup.object({
    specialities: yup.array(),
    specialitySingle: yup.mixed().when("specialities", {
      is: (specialities) => specialities === null || specialities.length === 0,
      then: yup.object({
        name: yup.string().required(),
      }),
      otherwise: yup.object({
        name: yup.string().optional(),
      }),
    }),
  });

  return withSpecialitiesValidationSchema;
};
