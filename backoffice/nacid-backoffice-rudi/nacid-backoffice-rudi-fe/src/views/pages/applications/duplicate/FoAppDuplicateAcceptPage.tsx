import React from "react";
import { useTranslation } from "react-i18next";
import PageWrapper from "../../../components/common/layout/PageWrapper";
import {
  AppSubTypeCode,
  AppType,
  AppTypeCode,
  FoAppDuplicatesAcceptRenderer,
} from "@duosoftbg/nacid-backoffice-components";

const FoAppDuplicateAcceptPage = () => {
  const { t } = useTranslation();

  const appType = AppType.DUPLICATE_RUDI;
  const appTypeCode = AppTypeCode.RUDI;
  const appSubTypeCode = AppSubTypeCode.DUPLICATES_RUDI;

  return (
    <PageWrapper title={t("t.eApplication.accept")}>
      <FoAppDuplicatesAcceptRenderer appType={appType} appTypeCode={appTypeCode} appSubTypeCode={appSubTypeCode} />
    </PageWrapper>
  );
};

export default FoAppDuplicateAcceptPage;
