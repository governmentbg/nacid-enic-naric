import * as yup from "yup";
import { initializeYup } from "@duosoftbg/nacid-backoffice-components";
import i18n from "i18next";
import { INPUT_LENGTH } from "@duosoftbg/nacid-components";

export const createCommissionCalendarValidationSchema = () => {
  initializeYup(yup);
  return yup.object({
    sessionTime: yup.string().required(),
    status: yup.object({
      id: yup.string().required().max(INPUT_LENGTH.MAX_INPUT_LENGTH_20, i18n.t("validation.charCount.invalid.20")),
    }),
  });
};
