import { AsyncCallArgs, useAsyncCall } from "@duosoftbg/nacid-components";

import { useEffect, useState } from "react";
import { getCommissionMembersByCalendarId } from "../../../../../axios/api/services";

const useMembersControl = ({ calendarId }) => {
  const { asyncCall } = useAsyncCall();
  const [members, setMembers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(false);

  useEffect(() => {
    const asyncCallArgs: AsyncCallArgs = {
      promise: getCommissionMembersByCalendarId(calendarId),
      onSuccess: (response) => {
        setMembers(response);
        setLoading(false);
        setError(false);
      },
      onError: () => {
        setMembers([]);
        setError(true);
        setLoading(false);
      },
    };
    asyncCall(asyncCallArgs);

    // eslint-disable-next-line
    }, [calendarId]);

  return {
    members,
    setMembers,
    loading,
    error,
  };
};

export default useMembersControl;
