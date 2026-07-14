import useAppDispatch from "../../../../hooks/redux/base/useAppDispatch";
import useAppSelector from "../../../../hooks/redux/base/useAppSelector";
import React, { useEffect } from "react";
import {
  ApplicationSubtype,
  AppPageContentWrapper,
  CircularTextLoader,
  PageContentBox,
  THUNK_STATUS,
} from "@duosoftbg/nacid-components";
import { Box } from "@mui/material";
import ServiceStepper from "../common/stepper/ServiceStepper";
import { resetDocDegreesRequest } from "../../../../store/redux/slice/Forms/docDegreesForm";
import { fetchLoggedUserDetails } from "../../../../store/redux/slice/AppData/loggedUser";

const DocDegreesNew = () => {
  const dispatch = useAppDispatch();

  const docDegreesForm = useAppSelector((state) => {
    return state.Forms.DocDegreesForm;
  });

  useEffect(() => {
    dispatch(resetDocDegreesRequest());
    dispatch(fetchLoggedUserDetails());
  }, [dispatch]);

  return (
    <AppPageContentWrapper>
      <PageContentBox>
        <Box sx={{ width: "100%" }}>
          {docDegreesForm.dataStateStatus === THUNK_STATUS.INITIAL ? (
            <ServiceStepper serviceSteps={docDegreesForm.steps} applicationSubtype={ApplicationSubtype.DOC_DEGREES} />
          ) : (
            <CircularTextLoader />
          )}
        </Box>
      </PageContentBox>
    </AppPageContentWrapper>
  );
};
export default DocDegreesNew;
