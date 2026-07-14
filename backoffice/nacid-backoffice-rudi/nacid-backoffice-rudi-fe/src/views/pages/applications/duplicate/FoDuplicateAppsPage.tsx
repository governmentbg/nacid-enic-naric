import { useTranslation } from "react-i18next";
import PageWrapper from "../../../components/common/layout/PageWrapper";
import { Title } from "@duosoftbg/nacid-components";
import FoDuplicateApps from "../../../components/main/applications/duplicate/electronicSubmission/FoDuplicateApps";

const FoDuplicateAppsPage = () => {
  const { t } = useTranslation();

  return (
    <PageWrapper title={t("t.eDuplicate")}>
      <Title component={"h4"} variant={"h4"}>
        {t("t.eApplications.short")}
      </Title>
      <FoDuplicateApps />
    </PageWrapper>
  );
};

export default FoDuplicateAppsPage;
