import useAppDispatch from "../../../../hooks/redux/base/useAppDispatch";
import { useSearchParams } from "react-router-dom";
import useAppSelector from "../../../../hooks/redux/base/useAppSelector";
import React, { useEffect } from "react";
import { fetchHeRecognitionForm } from "../../../../store/redux/slice/Forms/heRecognitionForm";
import {
  AppPageContentWrapper,
  PageContentBox,
  CircularTextLoader,
  THUNK_STATUS,
  ApplicationSubtype,
} from "@duosoftbg/nacid-components";
import { Alert, Box } from "@mui/material";
import ServiceStepper from "../common/stepper/ServiceStepper";
import { useTranslation } from "react-i18next";
import { fetchLoggedUserDetails } from "../../../../store/redux/slice/AppData/loggedUser";

const HERecognitionEdit = () => {
  const dispatch = useAppDispatch();
  const { t } = useTranslation();

  const [searchParams] = useSearchParams();
  const id = searchParams.get("id");

  const heRecognitionForm = useAppSelector((state) => {
    return state.Forms.HERecognitionForm;
  });

  useEffect(() => {
    dispatch(fetchHeRecognitionForm(id));
    dispatch(fetchLoggedUserDetails());
  }, [dispatch, id]);

  return (
    <AppPageContentWrapper>
      <PageContentBox>
        <Box sx={{ width: "100%" }}>
          {heRecognitionForm.dataStateStatus === THUNK_STATUS.FULFILLED ? (
            <ServiceStepper
              serviceSteps={heRecognitionForm.steps}
              applicationSubtype={ApplicationSubtype.HE_RECOGNITION}
            />
          ) : null}
          {heRecognitionForm.dataStateStatus === THUNK_STATUS.REJECTED ? (
            <Alert severity={"error"}>{t("m.generic.error.service.fail")}</Alert>
          ) : null}
          {heRecognitionForm.dataStateStatus === THUNK_STATUS.PENDING ? <CircularTextLoader /> : null}
        </Box>
      </PageContentBox>
    </AppPageContentWrapper>
  );
};
export default HERecognitionEdit;
