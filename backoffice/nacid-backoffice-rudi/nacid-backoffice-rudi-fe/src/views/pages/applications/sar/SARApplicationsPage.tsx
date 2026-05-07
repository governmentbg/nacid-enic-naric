import React from "react";
import { useTranslation } from "react-i18next";
import PageWrapper from "../../../components/common/layout/PageWrapper";
import SARApps from "../../../components/main/applications/sar/SARApps";

const SARApplicationsPage = () => {
  const { t } = useTranslation();

  return (
    <PageWrapper title={t("t.sar.applications")}>
      <SARApps />
    </PageWrapper>
  );
};

export default SARApplicationsPage;
