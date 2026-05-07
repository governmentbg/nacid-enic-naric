import * as yup from "yup";
import { initializeYup } from "@duosoftbg/nacid-backoffice-components";
import { REGEX_PATTERNS } from "@duosoftbg/nacid-components";
import i18n from "i18next";

export const createCommonReportSearchFiltersValidationSchema = () => {
  initializeYup(yup);

  //TODO
  const schema = yup.object({
    commission: yup.object({
      sessionNumberFrom: yup.string().matches(REGEX_PATTERNS.INTEGER_GREATER_THAN_0, {
        message: i18n.t("validation.field.invalid"),
        excludeEmptyString: true,
      }),
      sessionNumberTo: yup.string().matches(REGEX_PATTERNS.INTEGER_GREATER_THAN_0, {
        message: i18n.t("validation.field.invalid"),
        excludeEmptyString: true,
      }),
    }),
  });

  return schema;
};
