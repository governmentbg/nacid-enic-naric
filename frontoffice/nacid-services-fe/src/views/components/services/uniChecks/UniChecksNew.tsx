import useAppDispatch from "../../../../hooks/redux/base/useAppDispatch";
import useAppSelector from "../../../../hooks/redux/base/useAppSelector";
import React, { useEffect } from "react";
import { fetchLoggedUserDetails } from "../../../../store/redux/slice/AppData/loggedUser";
import {
  ApplicationSubtype,
  AppPageContentWrapper,
  CircularTextLoader,
  CONTENT_MANAGEMENT_ID,
  PageContentBox,
  THUNK_STATUS,
} from "@duosoftbg/nacid-components";
import { Box } from "@mui/material";
import ServiceStepper from "../common/stepper/ServiceStepper";
import { presetUniCheckServiceKind, resetUniChecksRequest } from "../../../../store/redux/slice/Forms/uniChecksForm";

const UniChecksNew = () => {
  const dispatch = useAppDispatch();

  const uniChecksForm = useAppSelector((state) => {
    return state.Forms.UniChecksForm;
  });

  const selectedService = useAppSelector((state) => {
    return state.SelectedService;
  });

  useEffect(() => {
    dispatch(resetUniChecksRequest());
    dispatch(fetchLoggedUserDetails());
    if (selectedService.descriptionCode === CONTENT_MANAGEMENT_ID.SD_UNI_CHECKS_ACADEMIC_STATUS) {
      dispatch(presetUniCheckServiceKind("statute"));
    } else if (selectedService.descriptionCode === CONTENT_MANAGEMENT_ID.SD_UNI_CHECKS_DOCUMENT_AUTHENTICITY) {
      dispatch(presetUniCheckServiceKind("authenticity"));
    } else if (selectedService.descriptionCode === CONTENT_MANAGEMENT_ID.SD_UNI_CHECKS_ISSUE_RECOMMENDATION) {
      dispatch(presetUniCheckServiceKind("recommendation"));
    }
  }, [dispatch, selectedService]);

  return (
    <AppPageContentWrapper>
      <PageContentBox>
        <Box sx={{ width: "100%" }}>
          {uniChecksForm.dataStateStatus === THUNK_STATUS.INITIAL ? (
            <ServiceStepper serviceSteps={uniChecksForm.steps} applicationSubtype={ApplicationSubtype.UNI_CHECKS} />
          ) : (
            <CircularTextLoader />
          )}
        </Box>
      </PageContentBox>
    </AppPageContentWrapper>
  );
};
export default UniChecksNew;
