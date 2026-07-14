import React from "react";
import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import FilingForm from "../../../common/form/filing/FilingForm";
import { baseEndpointPaths } from "../../../../../../services/serviceCalls";

const BiblioReferenceStepFiling = () => {
  const biblioReferenceForm = useAppSelector((state) => {
    return state.Forms.BiblioReferenceForm;
  });

  return <FilingForm basePath={baseEndpointPaths.bibliographicReference} appId={biblioReferenceForm.id} />;
};

export default BiblioReferenceStepFiling;
