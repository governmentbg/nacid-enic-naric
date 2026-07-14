import { useTranslation } from "react-i18next";
import { useSearchParams } from "react-router-dom";
import PageWrapper from "../../components/common/layout/PageWrapper";
import { baseEndpointPaths, getApplicationById } from "../../../services/serviceCalls";
import PublicAccessView from "../../components/services/publicAccess/PublicAccessView";

const PublicAccessViewPage = ({ titleCode }) => {
  const { t } = useTranslation();
  const [searchParams] = useSearchParams();
  const id = searchParams.get("id");

  return (
    <PageWrapper title={t(`${titleCode}.view`)}>
      <PublicAccessView serviceFn={getApplicationById(baseEndpointPaths.publicAccess, id)} />
    </PageWrapper>
  );
};
export default PublicAccessViewPage;
