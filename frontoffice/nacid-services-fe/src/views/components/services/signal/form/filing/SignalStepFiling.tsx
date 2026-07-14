import React from "react";
import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import FilingForm from "../../../common/form/filing/FilingForm";
import { baseEndpointPaths } from "../../../../../../services/serviceCalls";

const SignalStepFiling = () => {
  const signalForm = useAppSelector((state) => {
    return state.Forms.SignalForm;
  });

  return <FilingForm basePath={baseEndpointPaths.signal} appId={signalForm.id} />;
};
export default SignalStepFiling;
