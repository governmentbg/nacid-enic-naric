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
import { resetDocDeliveryRequest } from "../../../../store/redux/slice/Forms/docDeliveryForm";

const DocDeliveryNew = () => {
  const dispatch = useAppDispatch();

  const docDeliveryForm = useAppSelector((state) => {
    return state.Forms.DocDeliveryForm;
  });

  useEffect(() => {
    dispatch(resetDocDeliveryRequest());
    dispatch(fetchLoggedUserDetails());
  }, [dispatch]);

  return (
    <AppPageContentWrapper>
      <PageContentBox>
        <Box sx={{ width: "100%" }}>
          {docDeliveryForm.dataStateStatus === THUNK_STATUS.INITIAL ? (
            <ServiceStepper
              serviceSteps={docDeliveryForm.steps}
              applicationSubtype={ApplicationSubtype.DOCUMENT_SERVICE}
            />
          ) : (
            <CircularTextLoader />
          )}
        </Box>
      </PageContentBox>
    </AppPageContentWrapper>
  );
};
export default DocDeliveryNew;
