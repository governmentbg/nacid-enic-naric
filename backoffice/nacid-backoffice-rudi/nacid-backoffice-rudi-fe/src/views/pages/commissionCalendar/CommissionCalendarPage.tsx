import React from "react";
import { useTranslation } from "react-i18next";
import PageWrapper from "../../components/common/layout/PageWrapper";
import CommissionCalendar from "../../components/main/commissionCalendar/CommissionCalendar";

const CommissionCalendarPage = () => {
  const { t } = useTranslation();

  return (
    <PageWrapper title={t("t.commission.calendar")}>
      <CommissionCalendar />
    </PageWrapper>
  );
};

export default CommissionCalendarPage;
