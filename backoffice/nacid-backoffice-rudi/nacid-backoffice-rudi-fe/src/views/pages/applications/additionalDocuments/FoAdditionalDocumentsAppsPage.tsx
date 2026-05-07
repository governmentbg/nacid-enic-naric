import { useTranslation } from "react-i18next";
import PageWrapper from "../../../components/common/layout/PageWrapper";
import { Title } from "@duosoftbg/nacid-components";
import FoAdditionalDocumentsApps from "../../../components/main/applications/additionalDocuments/electronicSubmission/FoAdditionalDocumentsApps";

const FoAdditionalDocumentsAppsPage = () => {
  const { t } = useTranslation();

  return (
    <PageWrapper title={t("t.eAdditionalDocuments")}>
      <Title component={"h4"} variant={"h4"}>
        {t("t.eApplications.short")}
      </Title>
      <FoAdditionalDocumentsApps />
    </PageWrapper>
  );
};

export default FoAdditionalDocumentsAppsPage;
