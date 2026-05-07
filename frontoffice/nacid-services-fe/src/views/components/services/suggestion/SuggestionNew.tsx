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
import { resetSuggestionRequest } from "../../../../store/redux/slice/Forms/suggestionForm";

const SuggestionNew = () => {
  const dispatch = useAppDispatch();

  const suggestionForm = useAppSelector((state) => {
    return state.Forms.SuggestionForm;
  });

  useEffect(() => {
    dispatch(resetSuggestionRequest());
    dispatch(fetchLoggedUserDetails());
  }, [dispatch]);

  return (
    <AppPageContentWrapper>
      <PageContentBox>
        <Box sx={{ width: "100%" }}>
          {suggestionForm.dataStateStatus === THUNK_STATUS.INITIAL ? (
            <ServiceStepper serviceSteps={suggestionForm.steps} applicationSubtype={ApplicationSubtype.SUGGESTION} />
          ) : (
            <CircularTextLoader />
          )}
        </Box>
      </PageContentBox>
    </AppPageContentWrapper>
  );
};
export default SuggestionNew;
