import React from "react";
import { useTranslation } from "react-i18next";
import PageWrapper from "../../components/common/layout/PageWrapper";
import CommissionCalendarEdit from "../../components/main/commissionCalendar/CommissionCalendarEdit";

const CommissionCalendarCreatePage = () => {
  const { t } = useTranslation();
  return (
    <PageWrapper title={t("t.commission.calendar.create")}>
      <CommissionCalendarEdit />
    </PageWrapper>
  );
};

export default CommissionCalendarCreatePage;
