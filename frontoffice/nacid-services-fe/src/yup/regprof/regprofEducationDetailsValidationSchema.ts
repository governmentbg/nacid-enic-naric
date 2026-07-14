import { initializeYup, EducationType } from "@duosoftbg/nacid-components";
import * as yup from "yup";
import i18n from "i18next";

const createEducationEntryADQValidationSchema = () => {
  initializeYup(yup);
  const educationEntryADQValidationSchema = yup.object({
    oldEducationInstitutionName: yup
      .string()
      .test("oldEducationInstitutionName", i18n.t("validation.field.required"), function (value) {
        const { newEducationInstitutionName } = this.parent;
        if (!newEducationInstitutionName) return value !== null && value !== "";
        return true;
      }),
    newEducationInstitutionName: yup
      .string()
      .test("newEducationInstitutionName", i18n.t("validation.field.required"), function (value) {
        const { oldEducationInstitutionName } = this.parent;
        if (!oldEducationInstitutionName) return value !== null && value !== "";
        return true;
      }),
    professionalQualification: yup.string().required(),
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
    documentKind: yup.object({
      id: yup.string().required(),
    }),
  });
  return educationEntryADQValidationSchema;
};

const createEducationEntryHigherProfValidationSchema = () => {
  initializeYup(yup);
  const educationEntryHigherProfValidationSchema = yup.object({}).concat(createEducationEntryADQValidationSchema());
  return educationEntryHigherProfValidationSchema;
};

const createEducationValidationSchema = () => {
  initializeYup(yup);

  const educationValidationSchema = yup.object({
    kind: yup.mixed().nullable().oneOf(Object.values(EducationType)),
    educationEntryHigher: yup.mixed().when("kind", {
      is: (kind) => kind === EducationType.HIGHER_EDUCATION,
      then: createEducationEntryHigherProfValidationSchema(),
    }),
    educationEntrySecondary: yup.mixed().when("kind", {
      is: (kind) =>
        kind === EducationType.SECONDARY_PROFESSIONAL_EDUCATION || kind === EducationType.PROFESSIONAL_EDUCATION,
      then: createEducationEntryHigherProfValidationSchema(),
    }),
    educationEntryADQ: yup.mixed().when("kind", {
      is: (kind) => kind === EducationType.AFTER_DIPLOMA_QUALIFICATION,
      then: createEducationEntryADQValidationSchema(),
    }),
  });
  return educationValidationSchema;
};

const createWorkPeriodsValidationSchema = () => {
  initializeYup(yup);
  const wpValidationSchema = yup.array().of(
    yup.object().shape({
      fromDate: yup.string().required(),
      toDate: yup.string().required(),
      workDayHours: yup.object({
        id: yup.string().required(),
      }),
    })
  );
  return wpValidationSchema;
};

export const createExperienceDocumentsOnlyPeriodsValidationSchema = () => {
  return yup.array().of(
    yup.object().shape({
      workPeriods: createWorkPeriodsValidationSchema(),
    })
  );
};

const createExperienceValidationSchema = () => {
  initializeYup(yup);

  const experienceValidationSchema = yup.object({
    profession: yup.string().required(),
    experienceDocuments: yup.array().of(
      yup.object().shape({
        type: yup.object({
          id: yup.string().required(),
        }),
        institutionName: yup.string().required(),
        workPeriods: createWorkPeriodsValidationSchema(),
      })
    ),
  });
  return experienceValidationSchema;
};

export const createRegprofEducationDetailsValidationSchema = () => {
  initializeYup(yup);

  const educationDetailsValidationSchema = yup.object({
    serviceType: yup.object({
      id: yup.string().required(),
    }),
    country: yup.object({
      id: yup.string().required(),
    }),
    education: yup.mixed().when("educationSelected", {
      is: true,
      then: createEducationValidationSchema(),
    }),
    experience: yup.mixed().when("experienceSelected", {
      is: true,
      then: createExperienceValidationSchema(),
    }),
    nonRevokedRightToPractice: yup.boolean().required().oneOf([true], i18n.t("validation.field.required")),
    professionalQualificationRequested: yup.string().required(),
  });

  return educationDetailsValidationSchema;
};
