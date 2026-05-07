import useAppDispatch from "../../../../hooks/redux/base/useAppDispatch";
import { useTranslation } from "react-i18next";
import { useSearchParams } from "react-router-dom";
import useAppSelector from "../../../../hooks/redux/base/useAppSelector";
import React, { useEffect } from "react";
import {
  ApplicationSubtype,
  AppPageContentWrapper,
  CircularTextLoader,
  PageContentBox,
  THUNK_STATUS,
} from "@duosoftbg/nacid-components";
import { Alert, Box } from "@mui/material";
import ServiceStepper from "../common/stepper/ServiceStepper";
import { fetchRegprofForm } from "../../../../store/redux/slice/Forms/regprofForm";

const RegprofEdit = () => {
  const dispatch = useAppDispatch();
  const { t } = useTranslation();

  const [searchParams] = useSearchParams();
  const id = searchParams.get("id");

  const regprofForm = useAppSelector((state) => {
    return state.Forms.RegprofForm;
  });

  useEffect(() => {
    dispatch(fetchRegprofForm(id));
  }, [dispatch, id]);

  return (
    <AppPageContentWrapper>
      <PageContentBox>
        <Box sx={{ width: "100%" }}>
          {regprofForm.dataStateStatus === THUNK_STATUS.FULFILLED ? (
            <ServiceStepper
              serviceSteps={regprofForm.steps}
              applicationSubtype={ApplicationSubtype.REGULATED_PROFESSIONS}
            />
          ) : null}
          {regprofForm.dataStateStatus === THUNK_STATUS.REJECTED ? (
            <Alert severity={"error"}>{t("m.generic.error.service.fail")}</Alert>
          ) : null}
          {regprofForm.dataStateStatus === THUNK_STATUS.PENDING ? <CircularTextLoader /> : null}
        </Box>
      </PageContentBox>
    </AppPageContentWrapper>
  );
};
export default RegprofEdit;
