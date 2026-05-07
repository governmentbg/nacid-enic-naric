import * as yup from "yup";
import { initializeYup } from "@duosoftbg/nacid-backoffice-components";

export const createCommissionParticipationValidationSchema = () => {
  initializeYup(yup);
  return yup.object({
    notified: yup.boolean().required(),
    participated: yup.boolean().required(),
  });
};
