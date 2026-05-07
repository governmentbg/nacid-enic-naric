import React from "react";
import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import FilingForm from "../../../common/form/filing/FilingForm";
import { baseEndpointPaths } from "../../../../../../services/serviceCalls";

const DocDegreesStepFiling = () => {
  const docDegreesForm = useAppSelector((state) => {
    return state.Forms.DocDegreesForm;
  });
  return <FilingForm basePath={baseEndpointPaths.docDegrees} appId={docDegreesForm.id} />;
};

export default DocDegreesStepFiling;
