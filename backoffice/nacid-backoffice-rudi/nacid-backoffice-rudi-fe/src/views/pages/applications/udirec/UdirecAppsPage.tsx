import React from "react";
import { useTranslation } from "react-i18next";
import PageWrapper from "../../../components/common/layout/PageWrapper";
import UdirecApps from "../../../components/main/applications/udirec/UdirecApps";

const UdirecAppsPage = () => {
  const { t } = useTranslation();

  return (
    <PageWrapper title={t("t.diploma.recognitions")}>
      <UdirecApps />
    </PageWrapper>
  );
};

export default UdirecAppsPage;
