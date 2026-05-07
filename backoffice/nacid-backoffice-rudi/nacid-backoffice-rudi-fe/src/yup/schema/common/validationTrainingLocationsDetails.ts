import { initializeYup } from "@duosoftbg/nacid-backoffice-components";
import * as yup from "yup";
import i18n from "i18next";

export const validationTrainingLocationsDetailsSchema = () => {
  initializeYup(yup);
  const schema = yup.object({
    trainingLocations: yup.array().of(
      yup.object().shape({
        city: yup.string().optional().nullable().max(30, i18n.t("m.validation.field.longer.than.30")),
      }),
    ),
  });

  return schema;
};
