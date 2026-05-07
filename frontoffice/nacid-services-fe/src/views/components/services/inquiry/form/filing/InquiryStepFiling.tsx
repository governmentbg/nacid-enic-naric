import React from "react";
import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import FilingForm from "../../../common/form/filing/FilingForm";
import { baseEndpointPaths } from "../../../../../../services/serviceCalls";

const InquiryStepFiling = () => {
  const inquiryForm = useAppSelector((state) => {
    return state.Forms.InquiryForm;
  });

  return <FilingForm basePath={baseEndpointPaths.inquiry} appId={inquiryForm.id} />;
};
export default InquiryStepFiling;
