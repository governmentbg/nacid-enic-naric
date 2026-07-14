import { initializeYup } from "@duosoftbg/nacid-components";
import * as yup from "yup";
import { DOSSIER_NUMBER_REGEX } from "../utils/regexExpressions";

export const creatAappCheckupValidationSchema = () => {
  initializeYup(yup);

  const schema = yup.object({
    dossierNumber: yup.string().required().matches(DOSSIER_NUMBER_REGEX),
    accessCode: yup.string().required(),
  });

  return schema;
};
