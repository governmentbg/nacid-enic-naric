import { useTranslation } from "react-i18next";
import { useSearchParams } from "react-router-dom";
import PageWrapper from "../../components/common/layout/PageWrapper";
import { baseEndpointPaths, getApplicationById } from "../../../services/serviceCalls";
import SignalView from "../../components/services/signal/SignalView";

const SignalViewPage = ({ titleCode }) => {
  const { t } = useTranslation();
  const [searchParams] = useSearchParams();
  const id = searchParams.get("id");

  return (
    <PageWrapper title={t(`${titleCode}.view`)}>
      <SignalView serviceFn={getApplicationById(baseEndpointPaths.signal, id)} />
    </PageWrapper>
  );
};
export default SignalViewPage;
