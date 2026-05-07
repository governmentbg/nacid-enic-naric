import { useTranslation } from "react-i18next";
import useAppSelector from "../../../hooks/redux/base/useAppSelector";
import PageWrapper from "../../components/common/layout/PageWrapper";
import BiblioReferenceEdit from "../../components/services/bibliographicReference/BiblioReferenceEdit";

const BiblioReferenceEditPage = () => {
  const { t } = useTranslation();

  const selectedService = useAppSelector((state) => {
    return state.SelectedService;
  });

  return (
    <PageWrapper title={t(`${selectedService.titleCode}.edit`)}>
      <BiblioReferenceEdit />
    </PageWrapper>
  );
};
export default BiblioReferenceEditPage;
