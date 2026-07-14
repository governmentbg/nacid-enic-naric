import { useTranslation } from "react-i18next";
import { useSearchParams } from "react-router-dom";
import PageWrapper from "../../components/common/layout/PageWrapper";
import { baseEndpointPaths, getApplicationById } from "../../../services/serviceCalls";
import DocDeliveryView from "../../components/services/documentDelivery/DocDeliveryView";

const DocDeliveryViewPage = ({ titleCode }) => {
  const { t } = useTranslation();
  const [searchParams] = useSearchParams();
  const id = searchParams.get("id");

  return (
    <PageWrapper title={t(`${titleCode}.view`)}>
      <DocDeliveryView serviceFn={getApplicationById(baseEndpointPaths.documentDelivery, id)} />
    </PageWrapper>
  );
};
export default DocDeliveryViewPage;
