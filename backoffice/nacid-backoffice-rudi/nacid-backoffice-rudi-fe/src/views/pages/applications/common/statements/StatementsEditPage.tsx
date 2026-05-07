import * as React from "react";
import { useParams } from "react-router-dom";
import StatementEdit from "../../../../components/main/applications/common/tabs/closingData/parts/statements/sections/statements/components/edit/StatementEdit";
import { AppType } from "@duosoftbg/nacid-backoffice-components";

type StatementsEditPageProps = {
  appType: AppType;
};

const StatementsEditPage = ({ appType }: StatementsEditPageProps) => {
  const params = useParams();
  const applicationId = params.id;
  const statementId = params.statementId;

  return <StatementEdit appType={appType} applicationId={applicationId} statementId={statementId}></StatementEdit>;
};

export default StatementsEditPage;
