import { useEffect, useState } from "react";
import { useWatch } from "react-hook-form";
import { AsyncCallArgs, useAsyncCall } from "@duosoftbg/nacid-components";
import { calculateOfficialNotesFees } from "../../../../../../../services/serviceCalls";
import FeesSideButton from "../../../../common/form/FeesSideButton";

const OfficialNotesFeesSection = () => {
  const { asyncCall } = useAsyncCall();

  const [fees, setFees] = useState({});
  const [feesState, setFeesState] = useState({ loading: true, error: false });

  const kinds = useWatch({ name: "officialNotesKinds" });
  const serviceType = useWatch({ name: "serviceType.id" });

  useEffect(() => {
    const calcFeesArgs: AsyncCallArgs = {
      promise: calculateOfficialNotesFees(kinds, serviceType),
      processResponseErrors: false,
      withGlobalBackdrop: false,
      onSuccess: (response) => {
        setFees(response.data);
        setFeesState({ loading: false, error: false });
      },
      onError: () => setFeesState({ loading: false, error: true }),
    };
    asyncCall(calcFeesArgs);
  }, [kinds, serviceType, asyncCall]);

  return <FeesSideButton fees={fees} feesState={feesState} />;
};
export default OfficialNotesFeesSection;
