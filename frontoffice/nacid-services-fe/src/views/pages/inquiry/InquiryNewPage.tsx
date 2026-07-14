import { useTranslation } from "react-i18next";
import useAppSelector from "../../../hooks/redux/base/useAppSelector";
import PageWrapper from "../../components/common/layout/PageWrapper";
import InquiryNew from "../../components/services/inquiry/InquiryNew";

const InquiryNewPage = () => {
  const { t } = useTranslation();

  const selectedService = useAppSelector((state) => {
    return state.SelectedService;
  });

  return (
    <PageWrapper title={t(`${selectedService.titleCode}.new`)}>
      <InquiryNew />
    </PageWrapper>
  );
};
export default InquiryNewPage;
