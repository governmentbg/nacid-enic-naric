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
import { resetRegprofRequest } from "../../../../store/redux/slice/Forms/regprofForm";
import { fetchLoggedUserDetails } from "../../../../store/redux/slice/AppData/loggedUser";

const RegprofNew = () => {
  const dispatch = useAppDispatch();

  const regprofForm = useAppSelector((state) => {
    return state.Forms.RegprofForm;
  });

  useEffect(() => {
    dispatch(resetRegprofRequest());
    dispatch(fetchLoggedUserDetails());
  }, [dispatch]);

  return (
    <AppPageContentWrapper>
      <PageContentBox>
        <Box sx={{ width: "100%" }}>
          {regprofForm.dataStateStatus === THUNK_STATUS.INITIAL ? (
            <ServiceStepper
              serviceSteps={regprofForm.steps}
              applicationSubtype={ApplicationSubtype.REGULATED_PROFESSIONS}
            />
          ) : (
            <CircularTextLoader />
          )}
        </Box>
      </PageContentBox>
    </AppPageContentWrapper>
  );
};
export default RegprofNew;
