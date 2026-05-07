import {
  BoxSpg,
  initialThunkState,
  useAsyncCall,
  AsyncCallArgs,
  rejectedThunkState,
  fulfilledThunkState,
} from "@duosoftbg/nacid-components";
import { useEffect, useState } from "react";
import { getCorrespondenceDetails } from "../../../../../services/myCorrespondenceCalls";
import DmsDetails from "../dms/DmsDetails";
import CorrespondenceBaseDetails from "./CorrespondenceBaseDetails";

const CorrespondenceReadView = ({ correspondence }) => {
  const { asyncCall } = useAsyncCall();
  const [detailsState, setDetailsState] = useState(initialThunkState({}));

  useEffect(() => {
    const readAsyncArgs: AsyncCallArgs = {
      promise: getCorrespondenceDetails(correspondence.applicationId, correspondence.id),
      withGlobalBackdrop: true,
      processResponseErrors: false,
      onSuccess: (response) => {
        setDetailsState(fulfilledThunkState(response.data));
      },
      onError: () => {
        setDetailsState(rejectedThunkState({}));
      },
    };
    asyncCall(readAsyncArgs);
  }, [correspondence, asyncCall]);

  return (
    <BoxSpg>
      <CorrespondenceBaseDetails correspondence={correspondence} />
      <DmsDetails dmsDetailsState={detailsState} label={"t.application.correspondence.doc.details"} />
    </BoxSpg>
  );
};
export default CorrespondenceReadView;
