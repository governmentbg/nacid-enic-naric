import { initializeYup } from "@duosoftbg/nacid-backoffice-components";
import * as yup from "yup";
import { INPUT_LENGTH } from "@duosoftbg/nacid-components";
import i18n from "i18next";

export const vDocrecAppsFilterSchema = () => {
  initializeYup(yup);
  return yup.object().shape({
    entryNum: yup.string().max(INPUT_LENGTH.MAX_INPUT_LENGTH_S, i18n.t("validation.charCount.invalid.200")),
  });
};

export const vDocrecReceptionSchema = () => {
  initializeYup(yup);
  return yup.object({});
};

export const vDocrecMainDataSchema = () => {
  initializeYup(yup);
  return yup.object({});
};

export const vDocrecFoAppsAcceptSchema = () => {
  initializeYup(yup);
  return yup.object({});
};
