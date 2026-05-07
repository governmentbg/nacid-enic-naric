import { useTranslation } from "react-i18next";
import { useSearchParams } from "react-router-dom";
import { baseEndpointPaths, getApplicationById } from "../../../services/serviceCalls";
import PageWrapper from "../../components/common/layout/PageWrapper";
import UniChecksView from "../../components/services/uniChecks/UniChecksView";

const UniChecksViewPage = ({ titleCode }) => {
  const { t } = useTranslation();
  const [searchParams] = useSearchParams();
  const id = searchParams.get("id");

  return (
    <PageWrapper title={t(`${titleCode}.view`)}>
      <UniChecksView serviceFn={getApplicationById(baseEndpointPaths.uniChecks, id)} />
    </PageWrapper>
  );
};
export default UniChecksViewPage;
