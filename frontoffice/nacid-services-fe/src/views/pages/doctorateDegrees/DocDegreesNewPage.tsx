import PageWrapper from "../../components/common/layout/PageWrapper";
import { useTranslation } from "react-i18next";
import DocDegreesNew from "../../components/services/doctorateDegrees/DocDegreesNew";
import useAppSelector from "../../../hooks/redux/base/useAppSelector";

const DocDegreesNewPage = () => {
  const { t } = useTranslation();

  const selectedService = useAppSelector((state) => {
    return state.SelectedService;
  });

  return (
    <PageWrapper title={t(`${selectedService.titleCode}.new`)}>
      <DocDegreesNew />
    </PageWrapper>
  );
};

export default DocDegreesNewPage;
