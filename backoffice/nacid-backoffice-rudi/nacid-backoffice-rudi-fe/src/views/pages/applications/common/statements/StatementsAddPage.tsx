import * as React from "react";
import { useParams } from "react-router-dom";
import StatementEdit from "../../../../components/main/applications/common/tabs/closingData/parts/statements/sections/statements/components/edit/StatementEdit";
import { AppType } from "@duosoftbg/nacid-backoffice-components";

type StatementsAddPageProps = {
  appType: AppType;
};

const StatementsAddPage = ({ appType }: StatementsAddPageProps) => {
  const params = useParams();
  const applicationId = params.id;
  return <StatementEdit appType={appType} applicationId={applicationId} />;
};

export default StatementsAddPage;
