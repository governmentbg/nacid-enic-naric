import React from "react";
import { useTranslation } from "react-i18next";
import PageWrapper from "../../../components/common/layout/PageWrapper";
import { AppType, FoAppView } from "@duosoftbg/nacid-backoffice-components";

type FoAppViewPageProps = {
  appType: AppType;
};

const FoAppViewPage = ({ appType }: FoAppViewPageProps) => {
  const { t } = useTranslation();

  return (
    <PageWrapper title={t("t.eApplication.view")}>
      <FoAppView appType={appType} />
    </PageWrapper>
  );
};

export default FoAppViewPage;
