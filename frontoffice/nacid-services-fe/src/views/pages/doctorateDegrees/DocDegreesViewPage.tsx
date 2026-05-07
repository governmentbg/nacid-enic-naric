import PageWrapper from "../../components/common/layout/PageWrapper";
import { useTranslation } from "react-i18next";
import DocDegreesView from "../../components/services/doctorateDegrees/DocDegreesView";
import { baseEndpointPaths, getApplicationById } from "../../../services/serviceCalls";
import { useSearchParams } from "react-router-dom";

const DocDegreesViewPage = ({ titleCode }) => {
  const { t } = useTranslation();
  const [searchParams] = useSearchParams();
  const id = searchParams.get("id");

  return (
    <PageWrapper title={t(`${titleCode}.view`)}>
      <DocDegreesView serviceFn={getApplicationById(baseEndpointPaths.docDegrees, id)} />
    </PageWrapper>
  );
};
export default DocDegreesViewPage;
