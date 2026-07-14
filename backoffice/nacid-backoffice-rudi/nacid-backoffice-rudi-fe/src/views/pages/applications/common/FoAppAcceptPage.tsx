import React, { useEffect, useState } from "react";
import { useTranslation } from "react-i18next";
import PageWrapper from "../../../components/common/layout/PageWrapper";
import FoAppAccept from "../../../components/main/applications/common/accept/FoAppAccept";
import { AppType } from "@duosoftbg/nacid-backoffice-components";
import { useParams } from "react-router-dom";
import { AlertSpg, AsyncCallArgs, CircularLoader, useAsyncCall } from "@duosoftbg/nacid-components";
import { checkIfFoAppIsForAcception } from "../../../../axios/api/services";

type FoAppAcceptPageProps = {
  appType: AppType;
};

const FoAppAcceptPage = ({ appType }: FoAppAcceptPageProps) => {
  const { t } = useTranslation();
  const { id } = useParams();
  const { asyncCall } = useAsyncCall();
  const [loading, setLoading] = useState(true);
  const [exists, setExists] = useState(false);

  useEffect(() => {
    if (id) {
      const asyncCallArgs: AsyncCallArgs = {
        promise: checkIfFoAppIsForAcception(appType, id),
        processResponseErrors: false,
        onSuccess: () => {
          setExists(true);
          setLoading(false);
        },
        onError: () => {
          setExists(false);
          setLoading(false);
        },
      };
      asyncCall(asyncCallArgs);
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [id]);

  if (loading) {
    return (
      <PageWrapper title={t("t.eApplication.accept")}>
        <CircularLoader />
      </PageWrapper>
    );
  }

  if (!exists) {
    return (
      <PageWrapper title={t("t.eApplication.accept")}>
        <AlertSpg severity="error">{t("m.application.not.for.accept")}</AlertSpg>
      </PageWrapper>
    );
  }

  return (
    <PageWrapper title={t("t.eApplication.accept")}>
      <FoAppAccept appType={appType} />
    </PageWrapper>
  );
};

export default FoAppAcceptPage;
