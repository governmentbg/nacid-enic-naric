import { AlertSpg, CircularLoader, isNotEmpty, useAsyncCall, WithChildren } from "@duosoftbg/nacid-components";
import React, { useEffect, useState } from "react";
import { useTranslation } from "react-i18next";
import { initializeFoAppsAcceptForm } from "../../../../../../axios/api/services";
import { AppType } from "@duosoftbg/nacid-backoffice-components";
import FoAppAcceptFormProvider from "./FoAppAcceptFormProvider";
import { useParams } from "react-router-dom";

type SarAcceptFormInitializerProps = WithChildren<{ appType: AppType; activeTab: number }>;

const FoAppAcceptFormInitializer = ({ activeTab, appType, children }: SarAcceptFormInitializerProps) => {
  const { t } = useTranslation();
  const { asyncCall } = useAsyncCall();
  const { id } = useParams();

  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(false);

  useEffect(() => {
    asyncCall({
      promise: initializeFoAppsAcceptForm(appType, id),
      onSuccess: (response) => {
        response["applicationId"] = -2;
        setData(response);
        setLoading(false);
        setError(false);
      },
      onError: () => {
        setError(true);
        setLoading(false);
      },
    });
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  if (loading) {
    return <CircularLoader mt={3} />;
  }

  if (error) {
    return <AlertSpg severity="error">{t("m.error.serverFetchingError")}</AlertSpg>;
  }

  if (isNotEmpty(data)) {
    return (
      <FoAppAcceptFormProvider activeTab={activeTab} appType={appType} initialData={data}>
        {children}
      </FoAppAcceptFormProvider>
    );
  }
};

export default FoAppAcceptFormInitializer;
