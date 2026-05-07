import { useTranslation } from "react-i18next";
import useAppSelector from "../../../hooks/redux/base/useAppSelector";
import PageWrapper from "../../components/common/layout/PageWrapper";
import PublicAccessNew from "../../components/services/publicAccess/PublicAccessNew";

const PublicAccessNewPage = () => {
  const { t } = useTranslation();

  const selectedService = useAppSelector((state) => {
    return state.SelectedService;
  });

  return (
    <PageWrapper title={t(`${selectedService.titleCode}.new`)}>
      <PublicAccessNew />
    </PageWrapper>
  );
};
export default PublicAccessNewPage;
