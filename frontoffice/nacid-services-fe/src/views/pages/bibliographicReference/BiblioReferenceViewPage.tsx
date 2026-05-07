import { useTranslation } from "react-i18next";
import { useSearchParams } from "react-router-dom";
import PageWrapper from "../../components/common/layout/PageWrapper";
import { baseEndpointPaths, getApplicationById } from "../../../services/serviceCalls";
import BiblioReferenceView from "../../components/services/bibliographicReference/BiblioReferenceView";

const BiblioReferenceViewPage = ({ titleCode }) => {
  const { t } = useTranslation();
  const [searchParams] = useSearchParams();
  const id = searchParams.get("id");

  return (
    <PageWrapper title={t(`${titleCode}.view`)}>
      <BiblioReferenceView serviceFn={getApplicationById(baseEndpointPaths.bibliographicReference, id)} />
    </PageWrapper>
  );
};
export default BiblioReferenceViewPage;
