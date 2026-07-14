import PageWrapper from "../../components/common/layout/PageWrapper";
import { AppPageContentWrapper } from "@duosoftbg/nacid-components";
import ApplicationSignForm from "./ApplicationSignForm";
import { useTranslation } from "react-i18next";

const ApplicationSignPage = () => {
  const { t } = useTranslation();

  return (
    <PageWrapper title={t("t.page.app.sign")}>
      <AppPageContentWrapper>
        <ApplicationSignForm />
      </AppPageContentWrapper>
    </PageWrapper>
  );
};
export default ApplicationSignPage;
