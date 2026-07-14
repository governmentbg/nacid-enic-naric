import { useTranslation } from "react-i18next";
import PageWrapper from "../../components/common/layout/PageWrapper";
import DocDegreesEdit from "../../components/services/doctorateDegrees/DocDegreesEdit";
import useAppSelector from "../../../hooks/redux/base/useAppSelector";

const DocDegreesEditPage = () => {
  const { t } = useTranslation();

  const selectedService = useAppSelector((state) => {
    return state.SelectedService;
  });

  return (
    <PageWrapper title={t(`${selectedService.titleCode}.edit`)}>
      <DocDegreesEdit />
    </PageWrapper>
  );
};
export default DocDegreesEditPage;
