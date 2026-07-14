import { Title } from "@duosoftbg/nacid-components";
import React from "react";
import { useTranslation } from "react-i18next";
import PageWrapper from "../../../components/common/layout/PageWrapper";
import FoSARApps from "../../../components/main/applications/sar/electronicSubmission/FoSARApps";

const FoSARApplicationsPage = () => {
  const { t } = useTranslation();

  return (
    <PageWrapper title={t("t.sar.applications")}>
      <Title component={"h4"} variant={"h4"}>
        {t("l.e.apps")}
      </Title>
      <FoSARApps />
    </PageWrapper>
  );
};

export default FoSARApplicationsPage;
