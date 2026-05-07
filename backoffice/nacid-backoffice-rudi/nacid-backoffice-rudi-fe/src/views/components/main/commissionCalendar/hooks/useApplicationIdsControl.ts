import { AsyncCallArgs, useAsyncCall } from "@duosoftbg/nacid-components";

import { useEffect, useState } from "react";
import { getCommissionApplicationIdsByCalendarId } from "../../../../../axios/api/services";

const useApplicationIdsControl = ({ calendarId }) => {
  const { asyncCall } = useAsyncCall();
  const [applicationIds, setApplicationIds] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(false);

  useEffect(() => {
    const asyncCallArgs: AsyncCallArgs = {
      promise: getCommissionApplicationIdsByCalendarId(calendarId),
      onSuccess: (response) => {
        setApplicationIds(response);
        setLoading(false);
        setError(false);
      },
      onError: () => {
        setError(true);
        setLoading(false);
      },
    };
    asyncCall(asyncCallArgs);

    // eslint-disable-next-line
    }, [calendarId]);

  return {
    applicationIds,
    setApplicationIds,
    loading,
    error,
  };
};

export default useApplicationIdsControl;
