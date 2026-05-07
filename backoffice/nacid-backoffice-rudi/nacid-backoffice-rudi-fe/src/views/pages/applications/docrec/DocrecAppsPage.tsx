import React from "react";
import { useTranslation } from "react-i18next";
import PageWrapper from "../../../components/common/layout/PageWrapper";
import DocrecApps from "../../../components/main/applications/docrec/DocrecApps";

const DocrecAppsPage = () => {
  const { t } = useTranslation();

  return (
    <PageWrapper title={t("t.doctoral.degrees.recognitions")}>
      <DocrecApps />
    </PageWrapper>
  );
};

export default DocrecAppsPage;
