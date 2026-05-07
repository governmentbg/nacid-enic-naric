import * as React from "react";
import ExpertsListTable from "./components/ExpertsListTable";
import DeleteExpertDialog from "./components/dialog/DeleteExpertDialog";

const ExpertsSection = ({ applicationId, appType }) => {
  return (
    <>
      <DeleteExpertDialog />
      <ExpertsListTable applicationId={applicationId} appType={appType}></ExpertsListTable>
    </>
  );
};

export default ExpertsSection;
