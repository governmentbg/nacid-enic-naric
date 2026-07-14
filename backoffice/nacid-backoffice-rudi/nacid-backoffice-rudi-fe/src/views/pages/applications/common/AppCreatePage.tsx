import React from "react";
import { useTranslation } from "react-i18next";
import PageWrapper from "../../../components/common/layout/PageWrapper";
import AppReception from "../../../components/main/applications/common/reception/AppReception";
import { AppType } from "@duosoftbg/nacid-backoffice-components";

type AppCreatePageProps = {
  appType: AppType;
};

const AppCreatePage = ({ appType }: AppCreatePageProps) => {
  const { t } = useTranslation();

  return (
    <PageWrapper title={t("t.newApplication")}>
      <AppReception appType={appType} />
    </PageWrapper>
  );
};

export default AppCreatePage;
