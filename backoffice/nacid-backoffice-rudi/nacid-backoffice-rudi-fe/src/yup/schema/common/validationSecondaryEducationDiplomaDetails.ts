import { initializeYup } from "@duosoftbg/nacid-backoffice-components";
import * as yup from "yup";
import i18n from "i18next";

export const validationSecondaryEducationDiplomaDetailsSchema = () => {
  initializeYup(yup);
  const schema = yup.object({
    schoolCity: yup.string().optional().nullable().max(100, i18n.t("m.validation.field.longer.than.100")),
    schoolName: yup.string().optional().nullable().max(255, i18n.t("m.validation.field.longer.than.255")),
    schoolNotes: yup.string().optional().nullable().max(2000, i18n.t("m.validation.field.longer.than.2000")),
  });

  return schema;
};
