import { initializeYup } from "@duosoftbg/nacid-backoffice-components";
import * as yup from "yup";
import { INPUT_LENGTH } from "@duosoftbg/nacid-components";
import i18n from "i18next";

export const vSarAppsFilterSchema = () => {
  initializeYup(yup);
  return yup.object().shape({
    entryNum: yup.string().max(INPUT_LENGTH.MAX_INPUT_LENGTH_S, i18n.t("validation.charCount.invalid.200")),
  });
};

export const vSarReceptionSchema = () => {
  initializeYup(yup);
  return yup.object({});
};

export const vSarMainDataSchema = () => {
  initializeYup(yup);
  return yup.object({});
};

export const vSarFoAppsAcceptSchema = () => {
  initializeYup(yup);
  return yup.object({});
};
