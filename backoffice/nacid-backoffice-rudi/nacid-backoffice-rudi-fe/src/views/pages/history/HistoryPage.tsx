import { useTranslation } from "react-i18next";
import PageWrapper from "../../components/common/layout/PageWrapper";
import { History, HistoryModules } from "@duosoftbg/nacid-backoffice-components";

const HistoryPage = () => {
  const { t } = useTranslation();

  return (
    <PageWrapper title={t("t.page.history")}>
      <History applicationName={HistoryModules.RUDI} />
    </PageWrapper>
  );
};
export default HistoryPage;
