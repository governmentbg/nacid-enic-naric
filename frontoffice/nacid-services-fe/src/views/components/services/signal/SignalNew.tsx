import useAppSelector from "../../../../hooks/redux/base/useAppSelector";
import React, { useEffect } from "react";
import { fetchLoggedUserDetails } from "../../../../store/redux/slice/AppData/loggedUser";
import {
  ApplicationSubtype,
  AppPageContentWrapper,
  CircularTextLoader,
  PageContentBox,
  THUNK_STATUS,
} from "@duosoftbg/nacid-components";
import { Box } from "@mui/material";
import ServiceStepper from "../common/stepper/ServiceStepper";
import useAppDispatch from "../../../../hooks/redux/base/useAppDispatch";
import { resetSignalRequest } from "../../../../store/redux/slice/Forms/signalForm";

const SignalNew = () => {
  const dispatch = useAppDispatch();

  const signalForm = useAppSelector((state) => {
    return state.Forms.SignalForm;
  });

  useEffect(() => {
    dispatch(resetSignalRequest());
    dispatch(fetchLoggedUserDetails());
  }, [dispatch]);

  return (
    <AppPageContentWrapper>
      <PageContentBox>
        <Box sx={{ width: "100%" }}>
          {signalForm.dataStateStatus === THUNK_STATUS.INITIAL ? (
            <ServiceStepper serviceSteps={signalForm.steps} applicationSubtype={ApplicationSubtype.SIGNAL} />
          ) : (
            <CircularTextLoader />
          )}
        </Box>
      </PageContentBox>
    </AppPageContentWrapper>
  );
};
export default SignalNew;
