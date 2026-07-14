import {
  AsyncCallArgs,
  BoxSpg,
  DividerSpg,
  GridContainer,
  GridItem,
  useAsyncCall,
  ButtonSpg,
  SmallFontAwesomeIcon,
} from "@duosoftbg/nacid-components";
import { Alert, Button } from "@mui/material";
import EvaluationsFormSection from "./EvaluationsFormSection";
import React, { useEffect, useState } from "react";
import { useTranslation } from "react-i18next";
import { toast } from "react-toastify";
import DraftReceiptButton from "./DraftReceiptButton";
import {
  calculateFeesForId,
  evaluateApplicationForId,
  fileApplicationForId,
  finalizeApplicationForId,
} from "../../../../../../services/serviceCalls";
import { handleFilingErrors } from "../../../../../../yup/utils/filingUtils";
import { useNavigate } from "react-router-dom";
import { faFileSignature } from "@fortawesome/free-solid-svg-icons";
import ApplicationFeesSection from "./ApplicationFeesSection";
import {
  createAppSignUrl,
  createAppViewUrlWithBasePath,
  createMyApplicationsUrl,
} from "../../../../../../utils/applicationUrlUtils";

const FilingForm = ({ basePath, appId }) => {
  const { t } = useTranslation();
  const { asyncCall } = useAsyncCall();
  const navigate = useNavigate();
  const [evaluations, setEvaluations] = useState([]);
  const [fees, setFees] = useState({});
  const [feesState, setFeesState] = useState({ loading: true, error: false });

  useEffect(() => {
    const evalArgs: AsyncCallArgs = {
      promise: evaluateApplicationForId(basePath, appId),
      processResponseErrors: false,
      withGlobalBackdrop: true,
      onSuccess: (response) => setEvaluations(response.data),
    };
    const calcFeesArgs: AsyncCallArgs = {
      promise: calculateFeesForId(basePath, appId),
      processResponseErrors: false,
      withGlobalBackdrop: false,
      onSuccess: (response) => {
        setFees(response.data);
        setFeesState({ loading: false, error: false });
      },
      onError: () => setFeesState({ loading: false, error: true }),
    };
    asyncCall(evalArgs);
    asyncCall(calcFeesArgs);
  }, [appId, basePath, setEvaluations, asyncCall]);

  const finalizeApplication = () => {
    const fileApplicationAsyncCall: AsyncCallArgs = {
      withGlobalBackdrop: true,
      promise: finalizeApplicationForId(basePath, appId),
      processResponseErrors: false,
      onSuccess: (response) => {
        if (response.data.length > 1) {
          navigate(createMyApplicationsUrl());
          toast.success(t("m.finalize.application.multiple.success", { tempNumber: response.data.join(", ") }), {
            autoClose: 15000,
          });
        } else {
          navigate(createAppSignUrl(appId));
          toast.success(t("m.finalize.application.success", { tempNumber: response.data[0] }), { autoClose: 10000 });
        }
      },
      onError: (errResponse) => handleFilingErrors(errResponse),
    };
    asyncCall(fileApplicationAsyncCall);
  };

  const fileApplication = () => {
    const fileApplicationAsyncCall: AsyncCallArgs = {
      withGlobalBackdrop: true,
      promise: fileApplicationForId(basePath, appId),
      processResponseErrors: false,
      onSuccess: (response) => {
        if (response.data.length > 1) {
          navigate(createMyApplicationsUrl());
          toast.success(t("m.filing.application.multiple.success", { tempNumber: response.data.join(", ") }), {
            autoClose: 15000,
          });
        } else {
          navigate(createAppViewUrlWithBasePath(appId, basePath));
          toast.success(t("m.filing.application.success", { tempNumber: response.data[0] }), { autoClose: 10000 });
        }
      },
      onError: (errResponse) => handleFilingErrors(errResponse),
    };
    asyncCall(fileApplicationAsyncCall);
  };

  const allEvaluationsPositive = () => {
    return evaluations.map((ev) => ev.evaluationValue).reduce((ev1, ev2) => ev1 && ev2);
  };

  if (evaluations.length === 0) {
    return (
      <BoxSpg mt={4}>
        <Alert severity={"info"}>{t("m.loading")}</Alert>
        <DividerSpg my={4} />
        <BoxSpg mt={10}></BoxSpg>
      </BoxSpg>
    );
  }
  return (
    <BoxSpg>
      <ApplicationFeesSection fees={fees} feesState={feesState} />
      <EvaluationsFormSection evaluations={evaluations} />
      <DividerSpg my={4} />
      <BoxSpg>
        {allEvaluationsPositive() ? (
          <>
            <Button variant={"contained"} onClick={fileApplication}>
              {t("l.btn.file")}
            </Button>
            <ButtonSpg
              variant={"contained"}
              onClick={finalizeApplication}
              ml={3}
              startIcon={<SmallFontAwesomeIcon icon={faFileSignature} />}
            >
              {t("l.btn.finalize.sign")}
            </ButtonSpg>
            <DraftReceiptButton basePath={basePath} appId={appId} />
          </>
        ) : (
          <GridContainer mt={0}>
            <GridItem sm={6} md={6}>
              <Alert severity={"info"} sx={{ paddingTop: 0, paddingBottom: 0 }}>
                {t("m.correct.errors.to.submit")}
              </Alert>
            </GridItem>
            <GridItem sm={4} md={4}>
              <DraftReceiptButton basePath={basePath} appId={appId} />
            </GridItem>
          </GridContainer>
        )}
      </BoxSpg>
    </BoxSpg>
  );
};
export default FilingForm;
