import * as yup from "yup";
import { initializeYup } from "@duosoftbg/nacid-backoffice-components";

export const createGraduationDocumentTypeModalSchema = () => {
  initializeYup(yup);

  //TODO
  const schema = yup.object({});

  return schema;
};
