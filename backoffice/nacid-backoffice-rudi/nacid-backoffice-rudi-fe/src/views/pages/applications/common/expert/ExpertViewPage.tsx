import * as React from "react";
import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import { AlertSpg, AsyncCallArgs, useAsyncCall } from "@duosoftbg/nacid-components";
import { getApplicationCommissionMember } from "../../../../../axios/api/services";
import PageWrapper from "../../../../components/common/layout/PageWrapper";
import { useTranslation } from "react-i18next";
import ExpertViewDetails from "../../../../components/main/applications/common/tabs/closingData/parts/experts/sections/experts/components/view/ExpertViewDetails";

const ExpertViewPage = ({ appType }) => {
  const { t } = useTranslation();
  const { asyncCall } = useAsyncCall();
  const params = useParams();

  const applicationId = params.id;
  const memberId = params.memberId;
  const [expert, setExpert] = useState(null);
  const [error, setError] = useState(false);

  useEffect(() => {
    const asyncCallArgs: AsyncCallArgs = {
      promise: getApplicationCommissionMember(memberId),
      withGlobalBackdrop: true,
      onSuccess: (response) => {
        setExpert(response);
        setError(false);
      },
      onError: () => {
        setExpert(null);
        setError(true);
      },
    };
    asyncCall(asyncCallArgs);

    // eslint-disable-next-line
    }, [memberId,applicationId]);

  if (error) {
    return (
      <PageWrapper title={t("m.error")}>
        <AlertSpg severity="error">{t("m.error.serverFetchingError")}</AlertSpg>
      </PageWrapper>
    );
  }

  return <ExpertViewDetails expert={expert} applicationId={applicationId} appType={appType} />;
};

export default ExpertViewPage;
