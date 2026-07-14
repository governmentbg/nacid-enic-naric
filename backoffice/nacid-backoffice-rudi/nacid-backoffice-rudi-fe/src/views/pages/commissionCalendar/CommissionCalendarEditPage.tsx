import React, { useEffect, useState } from "react";
import { useTranslation } from "react-i18next";
import { useParams } from "react-router-dom";
import PageWrapper from "../../components/common/layout/PageWrapper";
import {
  AsyncCallArgs,
  useAsyncCall,
  CircularLoader,
  AlertSpg,
  useReloadWatcherReader,
  ReloadWatcherObject,
} from "@duosoftbg/nacid-components";
import { getCommissionCalendarFullNumber } from "../../../axios/api/services";
import CommissionCalendarEdit from "../../components/main/commissionCalendar/CommissionCalendarEdit";

const CommissionCalendarEditPage = () => {
  const { t } = useTranslation();
  const calendarId = useParams().calendarId;
  const { asyncCall } = useAsyncCall();
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(false);
  const [fullNumber, setFullNumber] = useState(null);
  const { reloadWatcher } = useReloadWatcherReader(ReloadWatcherObject.build("calendarCommonInformation", "edit"));

  useEffect(() => {
    if (calendarId) {
      const asyncCallArgs: AsyncCallArgs = {
        promise: getCommissionCalendarFullNumber(calendarId),
        processResponseErrors: false,
        onSuccess: (response) => {
          setFullNumber(response);
          setLoading(false);
          setError(false);
        },
        onError: () => {
          setFullNumber(null);
          setError(true);
          setLoading(false);
        },
      };
      asyncCall(asyncCallArgs);
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [calendarId, reloadWatcher]);

  if (loading) {
    return (
      <PageWrapper title={t("t.commission.calendar.edit")}>
        <CircularLoader />
      </PageWrapper>
    );
  }

  if (error) {
    return (
      <PageWrapper title={t("m.error")}>
        <AlertSpg severity="error">{t("m.error.serverFetchingError")}</AlertSpg>
      </PageWrapper>
    );
  }

  return (
    <PageWrapper title={t("t.commission.calendar.edit").concat(" ").concat(fullNumber)}>
      <CommissionCalendarEdit />
    </PageWrapper>
  );
};

export default CommissionCalendarEditPage;
