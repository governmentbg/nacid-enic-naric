import React from "react";
import { useTranslation } from "react-i18next";
import PageWrapper from "../../../components/common/layout/PageWrapper";
import {
  AppSubTypeCode,
  AppType,
  AppTypeCode,
  FoAppAdditionalDocumentsAcceptRenderer,
} from "@duosoftbg/nacid-backoffice-components";

const FoAppAdditionalDocumentsAcceptPage = () => {
  const { t } = useTranslation();

  const appType = AppType.ADDITIONAL_DOCUMENTS_RUDI;
  const appTypeCode = AppTypeCode.RUDI;
  const appSubTypeCode = AppSubTypeCode.ADDITIONAL_DOCUMENTS_RUDI;

  return (
    <PageWrapper title={t("t.eApplication.accept")}>
      <FoAppAdditionalDocumentsAcceptRenderer
        appType={appType}
        appTypeCode={appTypeCode}
        appSubTypeCode={appSubTypeCode}
      />
    </PageWrapper>
  );
};

export default FoAppAdditionalDocumentsAcceptPage;
