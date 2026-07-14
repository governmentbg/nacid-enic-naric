import React from "react";
import { useTranslation } from "react-i18next";
import PageWrapper from "../../../components/common/layout/PageWrapper";
import FoUdirecApps from "../../../components/main/applications/udirec/electronicSubmission/FoUdirecApps";
import { Title } from "@duosoftbg/nacid-components";

const FoUdirecApplicationsPage = () => {
  const { t } = useTranslation();

  return (
    <PageWrapper title={t("t.diploma.recognitions")}>
      <Title component={"h4"} variant={"h4"}>
        {t("l.e.apps")}
      </Title>
      <FoUdirecApps />
    </PageWrapper>
  );
};

export default FoUdirecApplicationsPage;
