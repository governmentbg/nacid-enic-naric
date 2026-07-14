import { useTranslation } from "react-i18next";
import useAppSelector from "../../hooks/redux/base/useAppSelector";
import PageWrapper from "../components/common/layout/PageWrapper";
import { AppPageContentWrapper } from "@duosoftbg/nacid-components";
import ServiceInfo from "../components/services/common/info/ServiceInfo";

const ServiceInfoPage = () => {
  const { t } = useTranslation();

  const selectedService = useAppSelector((state) => {
    return state.SelectedService;
  });

  return (
    <PageWrapper title={t(selectedService.titleCode)}>
      <AppPageContentWrapper>
        <ServiceInfo descriptionCode={selectedService.descriptionCode} baseUrl={selectedService.baseHref} />
      </AppPageContentWrapper>
    </PageWrapper>
  );
};
export default ServiceInfoPage;
