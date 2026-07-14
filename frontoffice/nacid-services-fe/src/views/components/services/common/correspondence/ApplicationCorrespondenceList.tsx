import React, { useCallback, useEffect, useState } from "react";
import {
  AlertSpg,
  AsyncCallArgs,
  TextSection,
  fulfilledThunkState,
  GridContainer,
  GridItem,
  initialThunkState,
  rejectedThunkState,
  TableSkeleton,
  THUNK_STATUS,
  useAsyncCall,
} from "@duosoftbg/nacid-components";
import { getApplicationCorrespondence } from "../../../../../services/myCorrespondenceCalls";
import ApplicationCorrespondenceTable from "./ApplicationCorrespondenceTable";
import { useTranslation } from "react-i18next";

const ApplicationCorrespondenceList = ({ applicationId }) => {
  const { asyncCall } = useAsyncCall();
  const { t } = useTranslation();
  const [correspondenceState, setCorrespondenceState] = useState(initialThunkState([]));

  const getAppCorrespondence = () => {
    const readAsyncArgs: AsyncCallArgs = {
      promise: getApplicationCorrespondence(applicationId),
      withGlobalBackdrop: true,
      processResponseErrors: false,
      onSuccess: (response) => {
        setCorrespondenceState(fulfilledThunkState(response.data));
      },
      onError: () => {
        setCorrespondenceState(rejectedThunkState([]));
      },
    };
    asyncCall(readAsyncArgs);
  };

  const getAppCorrespondenceCallback = useCallback(getAppCorrespondence, [applicationId, asyncCall]);

  useEffect(() => {
    if (applicationId) {
      getAppCorrespondenceCallback();
    }
  }, [applicationId, getAppCorrespondenceCallback]);

  if (
    !applicationId ||
    (correspondenceState.status === THUNK_STATUS.FULFILLED &&
      (!correspondenceState.data || correspondenceState.data.length === 0))
  ) {
    return null;
  }
  return (
    <TextSection label={"t.application.correspondence.details"} withDivider>
      <GridContainer mt={0}>
        <GridItem sm={12} md={12}>
          {(correspondenceState.status === THUNK_STATUS.PENDING ||
            correspondenceState.status === THUNK_STATUS.INITIAL) && <TableSkeleton />}
          {correspondenceState.status === THUNK_STATUS.REJECTED && (
            <AlertSpg severity={"error"}>{t("m.generic.error.service.fail")}</AlertSpg>
          )}
          {correspondenceState.status === THUNK_STATUS.FULFILLED && (
            <ApplicationCorrespondenceTable
              correspondence={correspondenceState.data}
              onReadChanged={() => getAppCorrespondence()}
            />
          )}
        </GridItem>
      </GridContainer>
    </TextSection>
  );
};
export default ApplicationCorrespondenceList;
