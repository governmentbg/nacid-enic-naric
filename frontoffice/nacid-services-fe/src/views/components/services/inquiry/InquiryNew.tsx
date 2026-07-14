import useAppDispatch from "../../../../hooks/redux/base/useAppDispatch";
import useAppSelector from "../../../../hooks/redux/base/useAppSelector";
import React, { useEffect } from "react";
import { fetchLoggedUserDetails } from "../../../../store/redux/slice/AppData/loggedUser";
import {
  ApplicationSubtype,
  AppPageContentWrapper,
  CircularTextLoader,
  CONTENT_MANAGEMENT_ID,
  InquiryKind,
  PageContentBox,
  THUNK_STATUS,
} from "@duosoftbg/nacid-components";
import { Box } from "@mui/material";
import ServiceStepper from "../common/stepper/ServiceStepper";
import { presetInquiryKinds, resetInquiryRequest } from "../../../../store/redux/slice/Forms/inquiryForm";

const InquiryNew = () => {
  const dispatch = useAppDispatch();

  const inquiryForm = useAppSelector((state) => {
    return state.Forms.InquiryForm;
  });

  const selectedService = useAppSelector((state) => {
    return state.SelectedService;
  });

  useEffect(() => {
    dispatch(resetInquiryRequest());
    dispatch(fetchLoggedUserDetails());
    if (selectedService.descriptionCode === CONTENT_MANAGEMENT_ID.SD_INQUIRY_PUBLICATION_CITINGS) {
      dispatch(presetInquiryKinds([InquiryKind.CITINGS]));
    } else if (selectedService.descriptionCode === CONTENT_MANAGEMENT_ID.SD_INQUIRY_IMPACT_FACTOR) {
      dispatch(presetInquiryKinds([InquiryKind.IMPACT_FACTOR]));
    }
  }, [dispatch, selectedService.descriptionCode]);

  return (
    <AppPageContentWrapper>
      <PageContentBox>
        <Box sx={{ width: "100%" }}>
          {inquiryForm.dataStateStatus === THUNK_STATUS.INITIAL ? (
            <ServiceStepper serviceSteps={inquiryForm.steps} applicationSubtype={ApplicationSubtype.INQUIRY} />
          ) : (
            <CircularTextLoader />
          )}
        </Box>
      </PageContentBox>
    </AppPageContentWrapper>
  );
};
export default InquiryNew;
