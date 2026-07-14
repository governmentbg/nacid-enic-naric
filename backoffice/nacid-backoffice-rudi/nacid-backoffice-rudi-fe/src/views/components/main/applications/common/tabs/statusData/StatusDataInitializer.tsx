import { useTranslation } from "react-i18next";
import { AlertSpg, AsyncCallArgs, BoxSpg, CircularLoader, useAsyncCall } from "@duosoftbg/nacid-components";
import { useParams } from "react-router-dom";
import React, { useEffect, useState } from "react";
import { examineStatusInitialData } from "../../../../../../../axios/api/services";
import StatusTabsData from "./StatusTabsData";
import StatusInitialExamination from "./StatusInitialExamination";

const StatusDataInitializer = ({ appType }) => {
  const { t } = useTranslation();
  const { asyncCall } = useAsyncCall();

  const { id: applicationId } = useParams();

  const [error, setError] = useState(false);
  const [loading, setLoading] = useState(true);
  const [data, setData] = useState(null);

  useEffect(() => {
    const asyncCallArgs: AsyncCallArgs = {
      promise: examineStatusInitialData(applicationId),
      processResponseErrors: false,
      onSuccess: (response) => {
        setData(response);
        setError(false);
        setLoading(false);
      },
      onError: () => {
        setError(true);
        setLoading(false);
      },
    };

    asyncCall(asyncCallArgs);
    // eslint-disable-next-line
    }, []);

  if (loading) {
    return (
      <BoxSpg mt={12}>
        <CircularLoader />
      </BoxSpg>
    );
  }

  if (error) {
    return (
      <BoxSpg mt={6}>
        <AlertSpg severity="error">{t("m.error.serverFetchingError")}</AlertSpg>
      </BoxSpg>
    );
  }

  return (
    <>
      {data && data.allAccomplished && <StatusTabsData appType={appType} />}
      {data && !data.allAccomplished && <StatusInitialExamination constraint={data} />}
    </>
  );
};
export default StatusDataInitializer;
