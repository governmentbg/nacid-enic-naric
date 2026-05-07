import { useTranslation } from "react-i18next";
import PageWrapper from "../../components/common/layout/PageWrapper";
import HERecognitionEdit from "../../components/services/higherEducation/HERecognitionEdit";
import useAppSelector from "../../../hooks/redux/base/useAppSelector";

const HERecognitionEditPage = () => {
  const { t } = useTranslation();

  const selectedService = useAppSelector((state) => {
    return state.SelectedService;
  });

  return (
    <PageWrapper title={t(`${selectedService.titleCode}.edit`)}>
      <HERecognitionEdit />
    </PageWrapper>
  );
};

export default HERecognitionEditPage;
