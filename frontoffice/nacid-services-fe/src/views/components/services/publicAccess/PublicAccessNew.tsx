import useAppDispatch from "../../../../hooks/redux/base/useAppDispatch";
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
import { resetPublicAccessRequest } from "../../../../store/redux/slice/Forms/publicAccessForm";

const PublicAccessNew = () => {
  const dispatch = useAppDispatch();

  const publicAccessForm = useAppSelector((state) => {
    return state.Forms.PublicAccessForm;
  });

  useEffect(() => {
    dispatch(resetPublicAccessRequest());
    dispatch(fetchLoggedUserDetails());
  }, [dispatch]);

  return (
    <AppPageContentWrapper>
      <PageContentBox>
        <Box sx={{ width: "100%" }}>
          {publicAccessForm.dataStateStatus === THUNK_STATUS.INITIAL ? (
            <ServiceStepper
              serviceSteps={publicAccessForm.steps}
              applicationSubtype={ApplicationSubtype.PUBLIC_ACCESS}
            />
          ) : (
            <CircularTextLoader />
          )}
        </Box>
      </PageContentBox>
    </AppPageContentWrapper>
  );
};
export default PublicAccessNew;
