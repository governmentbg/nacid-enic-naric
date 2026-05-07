import PageWrapper from "../../components/common/layout/PageWrapper";
import { useTranslation } from "react-i18next";
import HERecognitionView from "../../components/services/higherEducation/HERecognitionView";
import { useSearchParams } from "react-router-dom";
import { getApplicationById, baseEndpointPaths } from "../../../services/serviceCalls";

const HERecognitionViewPage = ({ titleCode }) => {
  const { t } = useTranslation();
  const [searchParams] = useSearchParams();
  const id = searchParams.get("id");

  return (
    <PageWrapper title={t(`${titleCode}.view`)}>
      <HERecognitionView serviceFn={getApplicationById(baseEndpointPaths.heRecognition, id)} />
    </PageWrapper>
  );
};
export default HERecognitionViewPage;
