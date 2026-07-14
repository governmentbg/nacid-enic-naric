import * as yup from "yup";
import { NAME_VALIDATION_REGEX, NUMBER_VALIDATION_REGEX } from "../../utils/regexExpressions";
import { initializeYup } from "../../utils/yupUtils";
import { IdentifierType } from "@duosoftbg/nacid-frontoffice-components";
import { ReferenceDataCode } from "@duosoftbg/nacid-components";

export const createNaturalPersonLibValidationSchema = () => {
  initializeYup(yup);

  const naturalPersonLibValidationSchema = yup
    .object({})
    .concat(createCommonNaturalPersonValidationSchema())
    .concat(createNaturalPersonIdentifierValidationSchema());
  return naturalPersonLibValidationSchema;
};

export const createNaturalPersonFullValidationSchema = () => {
  initializeYup(yup);

  const naturalPersonFullValidationSchema = yup
    .object({
      birthCountry: yup.object({
        id: yup.string().required(),
      }),
      birthPlace: yup.mixed().when("birthCountry.id", {
        is: (id) => id !== "BG",
        then: yup.string().required(),
      }),
      birthSettlement: yup.mixed().when("birthCountry.id", {
        is: (id) => id === "BG",
        then: yup.object({
          id: yup.string().required(),
        }),
      }),
      citizenship: yup.object({
        id: yup.string().required(),
      }),
    })
    .concat(createCommonNaturalPersonValidationSchema())
    .concat(createNaturalPersonIdentifierValidationSchema());
  return naturalPersonFullValidationSchema;
};

export const createCommonNaturalPersonValidationSchema = () => {
  initializeYup(yup);

  const naturalPersonCommonValidationSchema = yup.object({
    firstName: yup.string().required().matches(NAME_VALIDATION_REGEX),
    middleName: yup.mixed().when(["citizenship", "birthCountry"], {
      is: (citizenship, birthCountry) =>
        citizenship && citizenship.id === "BG" && birthCountry && birthCountry.id === "BG",
      then: yup.string().required().nullable().matches(NAME_VALIDATION_REGEX),
      otherwise: yup.string().optional().nullable().matches(NAME_VALIDATION_REGEX),
    }),
    lastName: yup.mixed().when(["citizenship", "personalIdType"], {
      is: (citizenship, personalIdType) =>
        (citizenship && citizenship.id === "BG") || personalIdType === IdentifierType.NATIONAL_ID,
      then: yup.string().required().nullable().matches(NAME_VALIDATION_REGEX),
      otherwise: yup.string().optional().nullable().matches(NAME_VALIDATION_REGEX),
    }),
    dateOfBirth: yup.string().required(),
    email: yup.string().optional().email(),
  });

  return naturalPersonCommonValidationSchema;
};

export const createNaturalPersonIdentifierValidationSchema = () => {
  initializeYup(yup);

  const naturalPersonIdSchema = yup.object({
    personalIdType: yup.mixed().oneOf(Object.values(IdentifierType)),
    personalId: yup.mixed().when(["personalIdType", "foreignerIdentifierKind"], {
      is: (personalIdType, foreignerIdentifierKind) =>
        personalIdType !== IdentifierType.DOCUMENT_ID ||
        foreignerIdentifierKind?.id !== ReferenceDataCode.FOREIGN_IDENTIFIER_TYPE_NACID_GENERATED,
      then: yup.string().required(),
    }),
    foreignerIdentifierKind: yup.mixed().when("personalIdType", {
      is: (idType) => idType === IdentifierType.DOCUMENT_ID,
      then: yup.object({
        id: yup.string().required(),
      }),
    }),
    foreignerIdentifierCountry: yup.mixed().when(["personalIdType", "foreignerIdentifierKind"], {
      is: (personalIdType, foreignerIdentifierKind) =>
        personalIdType === IdentifierType.DOCUMENT_ID &&
        foreignerIdentifierKind?.id !== ReferenceDataCode.FOREIGN_IDENTIFIER_TYPE_NACID_GENERATED,
      then: yup.object({
        id: yup.string().required(),
      }),
    }),
  });
  return naturalPersonIdSchema;
};

export const createNaturalPersonNamesValidationSchema = () => {
  initializeYup(yup);

  const naturalPersonNamesValidationSchema = yup.object({
    firstName: yup.string().required().matches(NAME_VALIDATION_REGEX),
    middleName: yup.string().optional().nullable().matches(NAME_VALIDATION_REGEX),
    lastName: yup.string().required().matches(NAME_VALIDATION_REGEX),
  });
  return naturalPersonNamesValidationSchema;
};

export const createCompanyValidationSchema = () => {
  initializeYup(yup);

  const companySchema = yup.object({
    companyName: yup.string().required(),
    companyIdentifier: yup.string().required().matches(NUMBER_VALIDATION_REGEX),
    companySettlement: yup.object({
      id: yup.string().required(),
    }),
  });

  return companySchema;
};

export const createUniversityValidationSchema = () => {
  initializeYup(yup);

  const uniSchema = yup.object({
    universityName: yup.string().required(),
    universityIdentifier: yup.string().required(),
    universitySettlement: yup.object({
      id: yup.string().required(),
    }),
  });

  return uniSchema;
};
