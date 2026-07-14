import { useTranslation } from "react-i18next";
import { useSearchParams } from "react-router-dom";
import { baseEndpointPaths, getApplicationForCheckup } from "../../../services/serviceCalls";
import PageWrapper from "../../components/common/layout/PageWrapper";
import RegprofView from "../../components/services/regprof/RegprofView";

const RegprofCheckupPage = ({ titleCode }) => {
  const { t } = useTranslation();
  const [searchParams] = useSearchParams();
  const dossierNumber = searchParams.get("dossierNumber");
  const accessCode = searchParams.get("accessCode");
  const captchaToken = searchParams.get("captchaToken");

  return (
    <PageWrapper title={t(`${titleCode}.view`)}>
      <RegprofView
        serviceFn={getApplicationForCheckup(baseEndpointPaths.regprof, dossierNumber, accessCode, captchaToken)}
      />
    </PageWrapper>
  );
};
export default RegprofCheckupPage;
