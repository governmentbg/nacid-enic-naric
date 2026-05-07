import { useTranslation } from "react-i18next";
import { useSearchParams } from "react-router-dom";
import PageWrapper from "../../components/common/layout/PageWrapper";
import HERecognitionView from "../../components/services/higherEducation/HERecognitionView";
import { baseEndpointPaths, getApplicationForCheckup } from "../../../services/serviceCalls";

const HERecognitionCheckupPage = ({ titleCode }) => {
  const { t } = useTranslation();
  const [searchParams] = useSearchParams();
  const dossierNumber = searchParams.get("dossierNumber");
  const accessCode = searchParams.get("accessCode");
  const captchaToken = searchParams.get("captchaToken");

  return (
    <PageWrapper title={t(`${titleCode}.view`)}>
      <HERecognitionView
        serviceFn={getApplicationForCheckup(baseEndpointPaths.heRecognition, dossierNumber, accessCode, captchaToken)}
      />
    </PageWrapper>
  );
};
export default HERecognitionCheckupPage;
