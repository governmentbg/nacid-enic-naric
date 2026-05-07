import * as React from "react";
import { useParams } from "react-router-dom";
import ExpertEditDetails from "../../../../components/main/applications/common/tabs/closingData/parts/experts/sections/experts/components/edit/ExpertEditDetails";
import { AppType } from "@duosoftbg/nacid-backoffice-components";

type ExpertAddPageProps = {
  appType: AppType;
};

const ExpertAddPage = ({ appType }: ExpertAddPageProps) => {
  const params = useParams();
  const applicationId = params.id;
  return <ExpertEditDetails appType={appType} applicationId={applicationId}></ExpertEditDetails>;
};

export default ExpertAddPage;
