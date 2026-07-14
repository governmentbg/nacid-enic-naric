import { initializeYup } from "@duosoftbg/nacid-components";
import * as yup from "yup";

export const createSuggestionDetailsValidationSchema = () => {
  initializeYup(yup);

  const suggestionDetailsValidationSchema = yup.object({
    suggestion: yup.string().required(),
  });

  return suggestionDetailsValidationSchema;
};
