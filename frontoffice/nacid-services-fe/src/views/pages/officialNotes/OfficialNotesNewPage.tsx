import { useTranslation } from "react-i18next";
import useAppSelector from "../../../hooks/redux/base/useAppSelector";
import PageWrapper from "../../components/common/layout/PageWrapper";
import OfficialNotesNew from "../../components/services/officialNotes/OfficialNotesNew";

const OfficialNotesNewPage = () => {
  const { t } = useTranslation();

  const selectedService = useAppSelector((state) => {
    return state.SelectedService;
  });

  return (
    <PageWrapper title={t(`${selectedService.titleCode}.new`)}>
      <OfficialNotesNew />
    </PageWrapper>
  );
};
export default OfficialNotesNewPage;
