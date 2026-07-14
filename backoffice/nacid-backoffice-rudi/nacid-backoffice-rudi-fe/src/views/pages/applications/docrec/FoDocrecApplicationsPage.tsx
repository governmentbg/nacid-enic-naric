import React from "react";
import { useTranslation } from "react-i18next";
import PageWrapper from "../../../components/common/layout/PageWrapper";
import FoDocrecApps from "../../../components/main/applications/docrec/electronicSubmission/FoDocrecApps";
import { Title } from "@duosoftbg/nacid-components";

const FoDocrecApplicationsPage = () => {
  const { t } = useTranslation();

  return (
    <PageWrapper title={t("t.doctoral.degrees.recognitions")}>
      <Title component={"h4"} variant={"h4"}>
        {t("l.e.apps")}
      </Title>
      <FoDocrecApps />
    </PageWrapper>
  );
};

export default FoDocrecApplicationsPage;
