import {
  TextSection,
  GridContainer,
  GridItem,
  useAsyncCall,
  initialThunkState,
  AsyncCallArgs,
  fulfilledThunkState,
  rejectedThunkState,
  THUNK_STATUS,
  AlertSpg,
  TableSkeleton,
} from "@duosoftbg/nacid-components";
import { useTranslation } from "react-i18next";
import React, { useCallback, useEffect, useState } from "react";
import { getApplicationCertificate } from "../../../../services/appCertificateCalls";
import ApplicationCertificateTable from "./ApplicationCertificateTable";
import { useGoogleReCaptcha } from "react-google-recaptcha-v3";

const ApplicationCertificateList = ({ applicationId, dossierNumber, accessCode }) => {
  const { asyncCall } = useAsyncCall();
  const { t } = useTranslation();
  const [certificateState, setCertificateState] = useState(initialThunkState(null));
  const { executeRecaptcha } = useGoogleReCaptcha();

  const getAppCertificate = async () => {
    let token = null;
    if (!applicationId) {
      token = await executeRecaptcha("ServicesCertificateGet");
    }
    const readAsyncArgs: AsyncCallArgs = {
      promise: getApplicationCertificate(applicationId, dossierNumber, accessCode, token),
      withGlobalBackdrop: false,
      processResponseErrors: false,
      onSuccess: (response) => {
        setCertificateState(fulfilledThunkState(response.data));
      },
      onError: () => {
        setCertificateState(rejectedThunkState(null));
      },
    };
    asyncCall(readAsyncArgs);
  };

  const getAppCertificateCallback = useCallback(getAppCertificate, [
    applicationId,
    dossierNumber,
    accessCode,
    asyncCall,
    executeRecaptcha,
  ]);

  useEffect(() => {
    getAppCertificateCallback();
  }, [applicationId, dossierNumber, accessCode, getAppCertificateCallback, executeRecaptcha]);

  if (
    (!applicationId && !dossierNumber) ||
    (certificateState.status === THUNK_STATUS.FULFILLED && (!certificateState.data || certificateState.data === null))
  ) {
    return null;
  }
  return (
    <TextSection label={"t.application.certificate.details"} withDivider>
      <GridContainer mt={0}>
        <GridItem sm={12} md={12}>
          {(certificateState.status === THUNK_STATUS.PENDING || certificateState.status === THUNK_STATUS.INITIAL) && (
            <TableSkeleton />
          )}
          {certificateState.status === THUNK_STATUS.REJECTED && (
            <AlertSpg severity={"error"}>{t("m.generic.error.service.fail")}</AlertSpg>
          )}
          {certificateState.status === THUNK_STATUS.FULFILLED && (
            <ApplicationCertificateTable certificate={certificateState.data} />
          )}
        </GridItem>
      </GridContainer>
    </TextSection>
  );
};
export default ApplicationCertificateList;
