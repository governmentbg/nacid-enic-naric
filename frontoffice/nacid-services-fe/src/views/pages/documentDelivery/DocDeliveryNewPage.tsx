import { useTranslation } from "react-i18next";
import useAppSelector from "../../../hooks/redux/base/useAppSelector";
import PageWrapper from "../../components/common/layout/PageWrapper";
import DocDeliveryNew from "../../components/services/documentDelivery/DocDeliveryNew";

const DocDeliveryNewPage = () => {
  const { t } = useTranslation();

  const selectedService = useAppSelector((state) => {
    return state.SelectedService;
  });

  return (
    <PageWrapper title={t(`${selectedService.titleCode}.new`)}>
      <DocDeliveryNew />
    </PageWrapper>
  );
};
export default DocDeliveryNewPage;
