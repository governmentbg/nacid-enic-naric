import * as React from "react";
import { useTranslation } from "react-i18next";
import { CardSpg } from "@duosoftbg/nacid-components";
import CardContent from "@mui/material/CardContent";
import ExpertSpecificDataView from "./ExpertSpecificDataView";
import ExpertPositionDataView from "./ExpertPositionDataView";
import PageWrapper from "../../../../../../../../../../../common/layout/PageWrapper";
import { AppType } from "@duosoftbg/nacid-backoffice-components";
import ApplicationSummary from "../../../../../../../../components/ApplicationSummary";

type ExpertViewDetailsProps = {
  expert: any;
  applicationId: number | string;
  appType: AppType;
};

const ExpertViewDetails = ({ applicationId, expert, appType }: ExpertViewDetailsProps) => {
  const { t } = useTranslation();
  return (
    <PageWrapper title={t("t.application.experts.view")}>
      <ApplicationSummary applicationId={applicationId} appType={appType} />
      {expert && (
        <CardSpg my={4} style={{ overflow: "visible" }}>
          <CardContent style={{ padding: 24, position: "relative" }}>
            <ExpertSpecificDataView expert={expert} />
            <ExpertPositionDataView expert={expert} />
          </CardContent>
        </CardSpg>
      )}
    </PageWrapper>
  );
};

export default ExpertViewDetails;
