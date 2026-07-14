import { useTranslation } from "react-i18next";
import useAppSelector from "../../../hooks/redux/base/useAppSelector";
import PageWrapper from "../../components/common/layout/PageWrapper";
import SuggestionNew from "../../components/services/suggestion/SuggestionNew";

const SuggestionNewPage = () => {
  const { t } = useTranslation();

  const selectedService = useAppSelector((state) => {
    return state.SelectedService;
  });

  return (
    <PageWrapper title={t(`${selectedService.titleCode}.new`)}>
      <SuggestionNew />
    </PageWrapper>
  );
};
export default SuggestionNewPage;
