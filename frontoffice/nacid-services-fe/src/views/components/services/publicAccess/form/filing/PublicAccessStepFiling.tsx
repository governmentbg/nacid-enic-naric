import React from "react";
import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import FilingForm from "../../../common/form/filing/FilingForm";
import { baseEndpointPaths } from "../../../../../../services/serviceCalls";

const PublicAccessStepFiling = () => {
  const publicAccessForm = useAppSelector((state) => {
    return state.Forms.PublicAccessForm;
  });

  return <FilingForm basePath={baseEndpointPaths.publicAccess} appId={publicAccessForm.id} />;
};
export default PublicAccessStepFiling;
