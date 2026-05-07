import * as yup from "yup";
import i18n from "i18next";
import { initializeYup, INPUT_LENGTH, REGEX_PATTERNS } from "@duosoftbg/nacid-components";

export const createCommissionMembersFilterValidationSchema = () => {
  initializeYup(yup);

  return yup.object().shape({
    id: yup.string().matches(REGEX_PATTERNS.INTEGER_GREATER_THAN_0, {
      message: i18n.t("validation.field.invalid"),
      excludeEmptyString: true,
    }),
    firstName: yup
      .string()
      .max(INPUT_LENGTH.MAX_INPUT_LENGTH_S, i18n.t("validation.field.invalid"))
      .matches(REGEX_PATTERNS.NAME_VALIDATION_REGEX, i18n.t("validation.field.invalid")),
    lastName: yup
      .string()
      .max(INPUT_LENGTH.MAX_INPUT_LENGTH_S, i18n.t("validation.field.invalid"))
      .matches(REGEX_PATTERNS.NAME_VALIDATION_REGEX, i18n.t("validation.field.invalid")),
  });
};
