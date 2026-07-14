import { useTranslation } from "react-i18next";
import useAppSelector from "../../../hooks/redux/base/useAppSelector";
import PageWrapper from "../../components/common/layout/PageWrapper";
import PublicAccessEdit from "../../components/services/publicAccess/PublicAccessEdit";

const PublicAccessEditPage = () => {
  const { t } = useTranslation();

  const selectedService = useAppSelector((state) => {
    return state.SelectedService;
  });

  return (
    <PageWrapper title={t(`${selectedService.titleCode}.edit`)}>
      <PublicAccessEdit />
    </PageWrapper>
  );
};
export default PublicAccessEditPage;
