import { initializeYup } from "@duosoftbg/nacid-backoffice-components";
import * as yup from "yup";
import { INPUT_LENGTH } from "@duosoftbg/nacid-components";
import i18n from "i18next";

export const vCommissionMemberSchema = () => {
  initializeYup(yup);
  return yup.object({
    processStatus: yup.boolean().required(),
    commissionMember: yup.object({
      id: yup.string().required(),
    }),
    commissionMemberPosition: yup.object({
      id: yup.string().nullable().required(),
    }),
  });
};

export const vAttachmentsSchema = () => {
  initializeYup(yup);
  return yup.object({});
};

export const vCommissionMemberStatementSchema = () => {
  initializeYup(yup);
  return yup.object({});
};

export const vStatusDataSchema = () => {
  initializeYup(yup);
  return yup.object({});
};

export const vUniExaminationSchema = () => {
  initializeYup(yup);
  return yup.object({});
};

export const vProgramExamSchema = () => {
  initializeYup(yup);
  return yup.object({});
};

export const vTrainingLocationExamSchema = () => {
  initializeYup(yup);
  return yup.object({});
};

export const vDiplomaExamSchema = () => {
  initializeYup(yup);
  return yup.object({});
};

export const vFoBaseAppsFilterSchema = () => {
  initializeYup(yup);
  return yup.object({
    entryNumber: yup.string().max(INPUT_LENGTH.MAX_INPUT_LENGTH_S, i18n.t("validation.charCount.invalid.200")),
  });
};
