import * as React from "react";
import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import { AlertSpg, AsyncCallArgs, useAsyncCall } from "@duosoftbg/nacid-components";
import { getApplicationCommissionMemberStatement } from "../../../../../axios/api/services";
import PageWrapper from "../../../../components/common/layout/PageWrapper";
import { useTranslation } from "react-i18next";
import StatementView from "../../../../components/main/applications/common/tabs/closingData/parts/statements/sections/statements/components/view/StatementView";

const StatementViewPage = () => {
  const { t } = useTranslation();
  const { asyncCall } = useAsyncCall();
  const params = useParams();

  const applicationId = params.id;
  const statementId = params.statementId;
  const [statement, setStatement] = useState(null);
  const [error, setError] = useState(false);

  useEffect(() => {
    const asyncCallArgs: AsyncCallArgs = {
      promise: getApplicationCommissionMemberStatement(statementId),
      withGlobalBackdrop: true,
      onSuccess: (response) => {
        setStatement(response);
        setError(false);
      },
      onError: () => {
        setStatement(null);
        setError(true);
      },
    };
    asyncCall(asyncCallArgs);

    // eslint-disable-next-line
    }, [statementId,applicationId]);

  if (error) {
    return (
      <PageWrapper title={t("m.error")}>
        <AlertSpg severity="error">{t("m.error.serverFetchingError")}</AlertSpg>
      </PageWrapper>
    );
  }

  return <StatementView statement={statement} />;
};

export default StatementViewPage;
