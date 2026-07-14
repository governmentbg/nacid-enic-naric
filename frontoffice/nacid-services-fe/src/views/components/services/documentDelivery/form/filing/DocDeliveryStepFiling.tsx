import React from "react";
import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import FilingForm from "../../../common/form/filing/FilingForm";
import { baseEndpointPaths } from "../../../../../../services/serviceCalls";

const DocDeliveryStepFiling = () => {
  const docDeliveryForm = useAppSelector((state) => {
    return state.Forms.DocDeliveryForm;
  });

  return <FilingForm basePath={baseEndpointPaths.documentDelivery} appId={docDeliveryForm.id} />;
};
export default DocDeliveryStepFiling;
