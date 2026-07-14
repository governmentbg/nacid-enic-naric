import React, { useEffect, useState } from "react";
import { useTranslation } from "react-i18next";
import { useParams } from "react-router-dom";
import PageWrapper from "../../components/common/layout/PageWrapper";
import { AsyncCallArgs, useAsyncCall, CircularLoader, AlertSpg } from "@duosoftbg/nacid-components";
import { getCommissionCalendarFullNumber } from "../../../axios/api/services";
import CommissionCalendarView from "../../components/main/commissionCalendar/CommissionCalendarView";

const CommissionCalendarViewPage = () => {
  const { t } = useTranslation();
  const calendarId = useParams().calendarId;
  const { asyncCall } = useAsyncCall();
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(false);
  const [fullNumber, setFullNumber] = useState(null);

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
  }, [calendarId]);

  if (loading) {
    return (
      <PageWrapper title={t("t.commission.calendar.view")}>
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
    <PageWrapper title={t("t.commission.calendar.view").concat(" ").concat(fullNumber)}>
      <CommissionCalendarView></CommissionCalendarView>
    </PageWrapper>
  );
};

export default CommissionCalendarViewPage;
