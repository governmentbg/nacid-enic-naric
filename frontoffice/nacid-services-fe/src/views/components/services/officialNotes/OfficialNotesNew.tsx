import useAppDispatch from "../../../../hooks/redux/base/useAppDispatch";
import useAppSelector from "../../../../hooks/redux/base/useAppSelector";
import React, { useEffect } from "react";
import { fetchLoggedUserDetails } from "../../../../store/redux/slice/AppData/loggedUser";
import {
  ApplicationSubtype,
  AppPageContentWrapper,
  CircularTextLoader,
  CONTENT_MANAGEMENT_ID,
  OfficialNoteKind,
  PageContentBox,
  THUNK_STATUS,
} from "@duosoftbg/nacid-components";
import { Box } from "@mui/material";
import ServiceStepper from "../common/stepper/ServiceStepper";
import {
  presetOfficialNotesKinds,
  resetOfficialNotesRequest,
} from "../../../../store/redux/slice/Forms/officialNotesForm";

const OfficialNotesNew = () => {
  const dispatch = useAppDispatch();

  const officialNotesForm = useAppSelector((state) => {
    return state.Forms.OfficialNotesForm;
  });

  const selectedService = useAppSelector((state) => {
    return state.SelectedService;
  });

  useEffect(() => {
    dispatch(resetOfficialNotesRequest());
    dispatch(fetchLoggedUserDetails());
    if (selectedService.descriptionCode === CONTENT_MANAGEMENT_ID.SD_OFFICIAL_NOTES_DISSERTATION_THESIS) {
      dispatch(presetOfficialNotesKinds([OfficialNoteKind.DISSERTATION_NOTE]));
    } else if (selectedService.descriptionCode === CONTENT_MANAGEMENT_ID.SD_OFFICIAL_NOTES_ACADEMIC_POSITION) {
      dispatch(presetOfficialNotesKinds([OfficialNoteKind.POSITION_NOTE]));
    }
  }, [dispatch, selectedService.descriptionCode]);

  return (
    <AppPageContentWrapper>
      <PageContentBox>
        <Box sx={{ width: "100%" }}>
          {officialNotesForm.dataStateStatus === THUNK_STATUS.INITIAL ? (
            <ServiceStepper
              serviceSteps={officialNotesForm.steps}
              applicationSubtype={ApplicationSubtype.OFFICIAL_NOTE}
            />
          ) : (
            <CircularTextLoader />
          )}
        </Box>
      </PageContentBox>
    </AppPageContentWrapper>
  );
};
export default OfficialNotesNew;
