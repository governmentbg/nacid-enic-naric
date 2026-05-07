import {
  ApplicationSubtype,
  AppPageContentWrapper,
  CircularTextLoader,
  PageContentBox,
  THUNK_STATUS,
} from "@duosoftbg/nacid-components";
import { Box } from "@mui/material";
import React, { useEffect } from "react";
import useAppSelector from "../../../../hooks/redux/base/useAppSelector";
import useAppDispatch from "../../../../hooks/redux/base/useAppDispatch";
import { resetHeRecognitionRequest } from "../../../../store/redux/slice/Forms/heRecognitionForm";
import ServiceStepper from "../common/stepper/ServiceStepper";
import { fetchLoggedUserDetails } from "../../../../store/redux/slice/AppData/loggedUser";

const HERecognitionNew = () => {
  const dispatch = useAppDispatch();

  const heRecognitionForm = useAppSelector((state) => {
    return state.Forms.HERecognitionForm;
  });

  useEffect(() => {
    dispatch(resetHeRecognitionRequest());
    dispatch(fetchLoggedUserDetails());
  }, [dispatch]);

  return (
    <AppPageContentWrapper>
      <PageContentBox>
        <Box sx={{ width: "100%" }}>
          {heRecognitionForm.dataStateStatus === THUNK_STATUS.INITIAL ? (
            <ServiceStepper
              serviceSteps={heRecognitionForm.steps}
              applicationSubtype={ApplicationSubtype.HE_RECOGNITION}
            />
          ) : (
            <CircularTextLoader />
          )}
        </Box>
      </PageContentBox>
    </AppPageContentWrapper>
  );
};

export default HERecognitionNew;
