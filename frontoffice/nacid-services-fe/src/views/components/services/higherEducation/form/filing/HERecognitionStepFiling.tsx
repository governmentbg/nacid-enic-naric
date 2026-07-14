import React from "react";
import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import FilingForm from "../../../common/form/filing/FilingForm";
import { baseEndpointPaths } from "../../../../../../services/serviceCalls";

const HERecognitionStepFiling = () => {
  const heRecognitionForm = useAppSelector((state) => {
    return state.Forms.HERecognitionForm;
  });

  return <FilingForm basePath={baseEndpointPaths.heRecognition} appId={heRecognitionForm.id} />;
};
export default HERecognitionStepFiling;
