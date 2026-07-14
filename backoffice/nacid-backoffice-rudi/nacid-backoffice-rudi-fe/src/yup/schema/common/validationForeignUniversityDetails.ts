import { initializeYup } from "@duosoftbg/nacid-backoffice-components";
import * as yup from "yup";
import i18n from "i18next";

export const validationForeignUniversityDetailsSchema = () => {
  initializeYup(yup);
  const schema = yup.object({
    primaryUniversity: yup
      .object()
      .nullable()
      .shape({
        universityNameTranslated: yup
          .string()
          .optional()
          .nullable()
          .max(255, i18n.t("m.validation.field.longer.than.255")),
        universityContact: yup.string().optional().nullable().max(300, i18n.t("m.validation.field.longer.than.300")),
      }),
  });

  return schema;
};
