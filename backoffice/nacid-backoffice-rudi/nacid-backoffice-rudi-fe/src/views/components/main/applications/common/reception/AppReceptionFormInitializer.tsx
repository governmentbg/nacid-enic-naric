import { AlertSpg, CircularLoader, isNotEmpty, useAsyncCall, WithChildren } from "@duosoftbg/nacid-components";
import React, { useEffect, useState } from "react";
import { useTranslation } from "react-i18next";
import { initializeReception } from "../../../../../../axios/api/services";
import { AppType } from "@duosoftbg/nacid-backoffice-components";
import AppReceptionFormProvider from "./AppReceptionFormProvider";

type ReceptionFormInitializerProps = WithChildren<{ appType: AppType }>;

const AppReceptionFormInitializer = ({ appType, children }: ReceptionFormInitializerProps) => {
  const { t } = useTranslation();
  const { asyncCall } = useAsyncCall();

  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(false);

  useEffect(() => {
    asyncCall({
      promise: initializeReception(appType),
      onSuccess: (response) => {
        response["applicationId"] = -1;
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
      <AppReceptionFormProvider appType={appType} initialData={data}>
        {children}
      </AppReceptionFormProvider>
    );
  }
};

export default AppReceptionFormInitializer;
