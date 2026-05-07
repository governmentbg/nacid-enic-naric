import { initializeYup, InquiryKind } from "@duosoftbg/nacid-components";
import * as yup from "yup";
import { YEAR_VALIDATION_REGEX } from "../utils/regexExpressions";
import i18n from "i18next";

export const createInquiryDetailsValidationSchema = () => {
  initializeYup(yup);

  const inquiryDetailsValidationSchema = yup.object({
    inquiryKinds: yup.array().min(1),
    periodFrom: yup.string().required().matches(YEAR_VALIDATION_REGEX),
    periodTo: yup.string().required().matches(YEAR_VALIDATION_REGEX),
    previousInquiryNum: yup.mixed().when("inquiryKinds", {
      is: (kinds) =>
        kinds.filter((kind) => kind === InquiryKind.IMPACT_FACTOR_CITINGS).length > 0 &&
        kinds.filter((kind) => kind === InquiryKind.CITINGS).length === 0,
      then: yup.string().required(i18n.t("validation.previousInquiryNum.or.CITINGS")),
    }),
  });

  return inquiryDetailsValidationSchema;
};
