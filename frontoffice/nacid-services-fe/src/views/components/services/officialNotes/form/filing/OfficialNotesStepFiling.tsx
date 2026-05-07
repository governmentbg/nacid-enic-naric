import React from "react";
import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import FilingForm from "../../../common/form/filing/FilingForm";
import { baseEndpointPaths } from "../../../../../../services/serviceCalls";

const OfficialNotesStepFiling = () => {
  const officialNotesForm = useAppSelector((state) => {
    return state.Forms.OfficialNotesForm;
  });

  return <FilingForm basePath={baseEndpointPaths.officialNotes} appId={officialNotesForm.id} />;
};
export default OfficialNotesStepFiling;
