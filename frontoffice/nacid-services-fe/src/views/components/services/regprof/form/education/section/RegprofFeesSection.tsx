import { useEffect, useState } from "react";
import { useWatch } from "react-hook-form";
import { AsyncCallArgs, useAsyncCall } from "@duosoftbg/nacid-components";
import { calculateRegprofFees } from "../../../../../../../services/serviceCalls";
import FeesSideButton from "../../../../common/form/FeesSideButton";

const RegprofFeesSection = () => {
  const { asyncCall } = useAsyncCall();

  const [fees, setFees] = useState({});
  const [feesState, setFeesState] = useState({ loading: true, error: false });

  const serviceType = useWatch({ name: "serviceType.id" });

  useEffect(() => {
    const calcFeesArgs: AsyncCallArgs = {
      promise: calculateRegprofFees(serviceType),
      processResponseErrors: false,
      withGlobalBackdrop: false,
      onSuccess: (response) => {
        setFees(response.data);
        setFeesState({ loading: false, error: false });
      },
      onError: () => setFeesState({ loading: false, error: true }),
    };
    asyncCall(calcFeesArgs);
  }, [serviceType, asyncCall]);

  return <FeesSideButton fees={fees} feesState={feesState} />;
};
export default RegprofFeesSection;
