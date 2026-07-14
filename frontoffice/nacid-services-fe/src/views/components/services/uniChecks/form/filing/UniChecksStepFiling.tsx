import React from "react";
import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import FilingForm from "../../../common/form/filing/FilingForm";
import { baseEndpointPaths } from "../../../../../../services/serviceCalls";

const UniChecksStepFiling = () => {
  const uniChecksForm = useAppSelector((state) => {
    return state.Forms.UniChecksForm;
  });

  return <FilingForm basePath={baseEndpointPaths.uniChecks} appId={uniChecksForm.id} />;
};
export default UniChecksStepFiling;
