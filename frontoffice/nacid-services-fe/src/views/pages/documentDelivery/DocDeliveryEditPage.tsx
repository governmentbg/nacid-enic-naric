import { useTranslation } from "react-i18next";
import useAppSelector from "../../../hooks/redux/base/useAppSelector";
import PageWrapper from "../../components/common/layout/PageWrapper";
import DocDeliveryEdit from "../../components/services/documentDelivery/DocDeliveryEdit";

const DocDeliveryEditPage = () => {
  const { t } = useTranslation();

  const selectedService = useAppSelector((state) => {
    return state.SelectedService;
  });

  return (
    <PageWrapper title={t(`${selectedService.titleCode}.edit`)}>
      <DocDeliveryEdit />
    </PageWrapper>
  );
};
export default DocDeliveryEditPage;
