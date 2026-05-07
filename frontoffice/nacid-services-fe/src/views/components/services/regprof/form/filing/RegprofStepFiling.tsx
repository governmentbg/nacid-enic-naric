import React from "react";
import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import FilingForm from "../../../common/form/filing/FilingForm";
import { baseEndpointPaths } from "../../../../../../services/serviceCalls";

const RegprofStepFiling = () => {
  const regprofForm = useAppSelector((state) => {
    return state.Forms.RegprofForm;
  });

  return <FilingForm basePath={baseEndpointPaths.regprof} appId={regprofForm.id} />;
};

export default RegprofStepFiling;
