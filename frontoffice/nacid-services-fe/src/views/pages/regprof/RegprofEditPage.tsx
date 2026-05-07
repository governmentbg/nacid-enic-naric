import { useTranslation } from "react-i18next";
import useAppSelector from "../../../hooks/redux/base/useAppSelector";
import PageWrapper from "../../components/common/layout/PageWrapper";
import RegprofEdit from "../../components/services/regprof/RegprofEdit";

const RegprofEditPage = () => {
  const { t } = useTranslation();

  const selectedService = useAppSelector((state) => {
    return state.SelectedService;
  });

  return (
    <PageWrapper title={t(`${selectedService.titleCode}.edit`)}>
      <RegprofEdit />
    </PageWrapper>
  );
};

export default RegprofEditPage;
