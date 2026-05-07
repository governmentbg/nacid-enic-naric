import { initializeYup } from "@duosoftbg/nacid-backoffice-components";
import * as yup from "yup";
import i18n from "i18next";

export const validationDiplomaTypeDetailsSchema = () => {
  initializeYup(yup);
  const schema = yup.object({
    diplomaNumber: yup.string().optional().nullable().max(50, i18n.t("m.validation.field.longer.than.50")),
    diplomaSeries: yup.string().optional().nullable().max(20, i18n.t("m.validation.field.longer.than.20")),
    diplomaRegistrationNumber: yup.string().optional().nullable().max(20, i18n.t("m.validation.field.longer.than.20")),
  });

  return schema;
};
