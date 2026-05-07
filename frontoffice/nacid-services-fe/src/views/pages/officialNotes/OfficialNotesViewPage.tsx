import { useTranslation } from "react-i18next";
import { useSearchParams } from "react-router-dom";
import PageWrapper from "../../components/common/layout/PageWrapper";
import { baseEndpointPaths, getApplicationById } from "../../../services/serviceCalls";
import OfficialNotesView from "../../components/services/officialNotes/OfficialNotesView";

const OfficialNotesViewPage = ({ titleCode }) => {
  const { t } = useTranslation();
  const [searchParams] = useSearchParams();
  const id = searchParams.get("id");

  return (
    <PageWrapper title={t(`${titleCode}.view`)}>
      <OfficialNotesView serviceFn={getApplicationById(baseEndpointPaths.officialNotes, id)} />
    </PageWrapper>
  );
};
export default OfficialNotesViewPage;
