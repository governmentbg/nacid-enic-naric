import React from "react";
import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import FilingForm from "../../../common/form/filing/FilingForm";
import { baseEndpointPaths } from "../../../../../../services/serviceCalls";

const SuggestionStepFiling = () => {
  const suggestionForm = useAppSelector((state) => {
    return state.Forms.SuggestionForm;
  });

  return <FilingForm basePath={baseEndpointPaths.suggestion} appId={suggestionForm.id} />;
};
export default SuggestionStepFiling;
