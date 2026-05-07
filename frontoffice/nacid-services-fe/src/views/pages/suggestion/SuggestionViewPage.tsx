import { useTranslation } from "react-i18next";
import { useSearchParams } from "react-router-dom";
import PageWrapper from "../../components/common/layout/PageWrapper";
import { baseEndpointPaths, getApplicationById } from "../../../services/serviceCalls";
import SuggestionView from "../../components/services/suggestion/SuggestionView";

const SuggestionViewPage = ({ titleCode }) => {
  const { t } = useTranslation();
  const [searchParams] = useSearchParams();
  const id = searchParams.get("id");

  return (
    <PageWrapper title={t(`${titleCode}.view`)}>
      <SuggestionView serviceFn={getApplicationById(baseEndpointPaths.suggestion, id)} />
    </PageWrapper>
  );
};
export default SuggestionViewPage;
