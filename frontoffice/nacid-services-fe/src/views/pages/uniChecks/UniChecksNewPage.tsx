import { useTranslation } from "react-i18next";
import useAppSelector from "../../../hooks/redux/base/useAppSelector";
import PageWrapper from "../../components/common/layout/PageWrapper";
import UniChecksNew from "../../components/services/uniChecks/UniChecksNew";

const UniChecksNewPage = () => {
  const { t } = useTranslation();

  const selectedService = useAppSelector((state) => {
    return state.SelectedService;
  });

  return (
    <PageWrapper title={t(`${selectedService.titleCode}.new`)}>
      <UniChecksNew />
    </PageWrapper>
  );
};
export default UniChecksNewPage;
