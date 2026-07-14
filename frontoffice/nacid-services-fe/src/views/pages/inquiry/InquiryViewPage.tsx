import { useTranslation } from "react-i18next";
import { useSearchParams } from "react-router-dom";
import PageWrapper from "../../components/common/layout/PageWrapper";
import { baseEndpointPaths, getApplicationById } from "../../../services/serviceCalls";
import InquiryView from "../../components/services/inquiry/InquiryView";

const InquiryViewPage = ({ titleCode }) => {
  const { t } = useTranslation();
  const [searchParams] = useSearchParams();
  const id = searchParams.get("id");

  return (
    <PageWrapper title={t(`${titleCode}.view`)}>
      <InquiryView serviceFn={getApplicationById(baseEndpointPaths.inquiry, id)} />
    </PageWrapper>
  );
};
export default InquiryViewPage;
