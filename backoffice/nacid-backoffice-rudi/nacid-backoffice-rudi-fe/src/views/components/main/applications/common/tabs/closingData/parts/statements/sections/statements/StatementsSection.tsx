import { AbdocsTransferAttachmentsDialog } from "@duosoftbg/nacid-backoffice-components";
import * as React from "react";
import DeleteStatementDialog from "./components/dialog/DeleteStatementDialog";
import StatementsListTable from "./components/StatementsListTable";

const StatementsSection = ({ applicationId, appType }) => {
  return (
    <>
      <AbdocsTransferAttachmentsDialog />
      <DeleteStatementDialog />
      <StatementsListTable applicationId={applicationId} appType={appType}></StatementsListTable>
    </>
  );
};

export default StatementsSection;
