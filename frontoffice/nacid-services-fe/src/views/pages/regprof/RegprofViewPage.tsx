import { useTranslation } from "react-i18next";
import PageWrapper from "../../components/common/layout/PageWrapper";
import RegprofView from "../../components/services/regprof/RegprofView";
import { baseEndpointPaths, getApplicationById } from "../../../services/serviceCalls";
import { useSearchParams } from "react-router-dom";

const RegprofViewPage = ({ titleCode }) => {
  const { t } = useTranslation();
  const [searchParams] = useSearchParams();
  const id = searchParams.get("id");

  return (
    <PageWrapper title={t(`${titleCode}.view`)}>
      <RegprofView serviceFn={getApplicationById(baseEndpointPaths.regprof, id)} />
    </PageWrapper>
  );
};

export default RegprofViewPage;
