import * as React from "react";
import { useParams } from "react-router-dom";
import ExpertEditDetails from "../../../../components/main/applications/common/tabs/closingData/parts/experts/sections/experts/components/edit/ExpertEditDetails";
import { AppType } from "@duosoftbg/nacid-backoffice-components";

type ExpertEditPageProps = {
  appType: AppType;
};

const ExpertEditPage = ({ appType }: ExpertEditPageProps) => {
  const params = useParams();
  const applicationId = params.id;
  const memberId = params.memberId;

  return <ExpertEditDetails appType={appType} applicationId={applicationId} memberId={memberId}></ExpertEditDetails>;
};

export default ExpertEditPage;
