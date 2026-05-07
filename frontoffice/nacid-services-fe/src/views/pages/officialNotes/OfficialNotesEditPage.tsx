import { useTranslation } from "react-i18next";
import useAppSelector from "../../../hooks/redux/base/useAppSelector";
import PageWrapper from "../../components/common/layout/PageWrapper";
import OfficialNotesEdit from "../../components/services/officialNotes/OfficialNotesEdit";

const OfficialNotesEditPage = () => {
  const { t } = useTranslation();

  const selectedService = useAppSelector((state) => {
    return state.SelectedService;
  });

  return (
    <PageWrapper title={t(`${selectedService.titleCode}.edit`)}>
      <OfficialNotesEdit />
    </PageWrapper>
  );
};
export default OfficialNotesEditPage;
