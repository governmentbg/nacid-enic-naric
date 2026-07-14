import * as yup from "yup";
import { YEAR_VALIDATION_REGEX } from "../utils/regexExpressions";
import i18n from "i18next";
import { initializeYup, isEmpty } from "@duosoftbg/nacid-components";

export const createBiblioReferenceDetailsValidationSchema = () => {
  initializeYup(yup);

  const biblioReferenceDetailsValidationSchema = yup.object({
    foreignSearch: yup.boolean().test("required", i18n.t("validation.field.select"), function (value) {
      const { nacidSearch } = this.parent;
      if (!nacidSearch) return value !== null && value;
      return true;
    }),
    nacidSearch: yup.boolean().test("required", i18n.t("validation.field.select"), function (value) {
      const { foreignSearch } = this.parent;
      if (!foreignSearch) return value !== null && value;
      return true;
    }),
    foreignSearchKind: yup
      .string()
      .nullable(true)
      .test("required", i18n.t("validation.field.required"), function (value) {
        const { foreignSearch } = this.parent;
        if (foreignSearch) return value !== null && !isEmpty(value);
        return true;
      }),
    nacidSearchKind: yup
      .string()
      .nullable(true)
      .test("required", i18n.t("validation.field.required"), function (value) {
        const { nacidSearch } = this.parent;
        if (nacidSearch) return value !== null && !isEmpty(value);
        return true;
      }),
    keywords: yup.string().required(),
    searchLanguages: yup.array().min(1),
    searchFrom: yup.string().required().matches(YEAR_VALIDATION_REGEX),
    searchTo: yup.string().required().matches(YEAR_VALIDATION_REGEX),
    theme: yup.string().required(),
  });

  return biblioReferenceDetailsValidationSchema;
};
