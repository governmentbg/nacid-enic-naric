import { initializeYup } from "@duosoftbg/nacid-backoffice-components";
import * as yup from "yup";
import i18n from "i18next";

export const validationDiplomaForPreviousHigherEducationSchema = () => {
  initializeYup(yup);
  const schema = yup.object({
    prevDiplomaSpeciality: yup.string().optional().nullable().max(255, i18n.t("m.validation.field.longer.than.255")),
    prevDiplomaNotes: yup.string().optional().nullable().max(2000, i18n.t("m.validation.field.longer.than.2000")),
  });

  return schema;
};
