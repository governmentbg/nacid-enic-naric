import PageWrapper from "../../components/common/layout/PageWrapper";
import HERecognitionNew from "../../components/services/higherEducation/HERecognitionNew";
import { useTranslation } from "react-i18next";
import useAppSelector from "../../../hooks/redux/base/useAppSelector";

const HERecognitionNewPage = () => {
  const { t } = useTranslation();

  const selectedService = useAppSelector((state) => {
    return state.SelectedService;
  });

  return (
    <PageWrapper title={t(`${selectedService.titleCode}.new`)}>
      <HERecognitionNew />
    </PageWrapper>
  );
};

export default HERecognitionNewPage;
