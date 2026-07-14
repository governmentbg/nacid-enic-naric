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
import {
  presetBiblioReferenceForeign,
  presetBiblioReferenceNacid,
  resetBiblioReferenceRequest,
} from "../../../../store/redux/slice/Forms/biblioReferenceForm";

const BiblioReferenceNew = () => {
  const dispatch = useAppDispatch();

  const biblioReferenceForm = useAppSelector((state) => {
    return state.Forms.BiblioReferenceForm;
  });

  const selectedService = useAppSelector((state) => {
    return state.SelectedService;
  });

  useEffect(() => {
    dispatch(resetBiblioReferenceRequest());
    dispatch(fetchLoggedUserDetails());
    if (selectedService.descriptionCode === CONTENT_MANAGEMENT_ID.SD_BIBLIOGRAPHIC_REFERENCES_NACID_DBS) {
      dispatch(presetBiblioReferenceNacid());
    } else if (selectedService.descriptionCode === CONTENT_MANAGEMENT_ID.SD_BIBLIOGRAPHIC_REFERENCES_FOREIGN_DBS) {
      dispatch(presetBiblioReferenceForeign());
    }
  }, [dispatch, selectedService.descriptionCode]);

  return (
    <AppPageContentWrapper>
      <PageContentBox>
        <Box sx={{ width: "100%" }}>
          {biblioReferenceForm.dataStateStatus === THUNK_STATUS.INITIAL ? (
            <ServiceStepper
              serviceSteps={biblioReferenceForm.steps}
              applicationSubtype={ApplicationSubtype.BIBLIO_REFERENCE}
            />
          ) : (
            <CircularTextLoader />
          )}
        </Box>
      </PageContentBox>
    </AppPageContentWrapper>
  );
};
export default BiblioReferenceNew;
