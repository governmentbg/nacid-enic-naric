import { AlertSpg, AsyncCallArgs, CircularLoader, isNotEmpty, useAsyncCall } from "@duosoftbg/nacid-components";
import { useParams } from "react-router-dom";
import React, { useEffect, useState } from "react";
import { useTranslation } from "react-i18next";
import MainDataFormProvider from "./MainDataFormProvider";
import { selectAppMainData } from "../../../../../../../axios/api/services";

const MainDataFormInitializer = ({ appType, children }) => {
  const { id } = useParams();
  const { t } = useTranslation();
  const { asyncCall } = useAsyncCall();

  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(false);

  useEffect(() => {
    if (id) {
      const asyncCallArgs: AsyncCallArgs = {
        promise: selectAppMainData(id),
        onSuccess: (response) => {
          setData(response);
          setLoading(false);
          setError(false);
        },
        onError: () => {
          setError(true);
          setLoading(false);
        },
      };
      asyncCall(asyncCallArgs);
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [id]);

  if (loading) {
    return <CircularLoader />;
  }

  if (error) {
    return <AlertSpg severity="error">{t("m.error.serverFetchingError")}</AlertSpg>;
  }

  if (isNotEmpty(data)) {
    return (
      <MainDataFormProvider appType={appType} id={id} initialData={data}>
        {children}
      </MainDataFormProvider>
    );
  }
};

export default MainDataFormInitializer;
