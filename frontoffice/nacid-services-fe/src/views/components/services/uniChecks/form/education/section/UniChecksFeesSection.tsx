import { useEffect, useState } from "react";
import { useWatch } from "react-hook-form";
import useAppSelector from "../../../../../../../hooks/redux/base/useAppSelector";
import { AsyncCallArgs, useAsyncCall } from "@duosoftbg/nacid-components";
import { calculateUniChecksFees } from "../../../../../../../services/serviceCalls";
import FeesSideButton from "../../../../common/form/FeesSideButton";

const UniChecksFeesSection = () => {
  const { asyncCall } = useAsyncCall();

  const [fees, setFees] = useState({});
  const [feesState, setFeesState] = useState({ loading: true, error: false });

  const serviceType = useWatch({ name: "serviceType.id" });
  const statute = useWatch({ name: "statute" });
  const authenticity = useWatch({ name: "authenticity" });
  const recommendation = useWatch({ name: "recommendation" });

  const uniChecksForm = useAppSelector((state) => {
    return state.Forms.UniChecksForm;
  });

  const applicantType = uniChecksForm.applicantDetails.applicant.applicantType;

  useEffect(() => {
    const calcFeesArgs: AsyncCallArgs = {
      promise: calculateUniChecksFees(statute, authenticity, recommendation, serviceType, applicantType),
      processResponseErrors: false,
      withGlobalBackdrop: false,
      onSuccess: (response) => {
        setFees(response.data);
        setFeesState({ loading: false, error: false });
      },
      onError: () => setFeesState({ loading: false, error: true }),
    };
    asyncCall(calcFeesArgs);
  }, [applicantType, serviceType, statute, authenticity, recommendation, asyncCall]);

  return <FeesSideButton fees={fees} feesState={feesState} />;
};
export default UniChecksFeesSection;
