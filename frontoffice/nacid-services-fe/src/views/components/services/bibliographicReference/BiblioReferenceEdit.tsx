import useAppDispatch from "../../../../hooks/redux/base/useAppDispatch";
import { useTranslation } from "react-i18next";
import { useSearchParams } from "react-router-dom";
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
import { Alert, Box } from "@mui/material";
import ServiceStepper from "../common/stepper/ServiceStepper";
import { fetchBiblioReferenceForm } from "../../../../store/redux/slice/Forms/biblioReferenceForm";

const BiblioReferenceEdit = () => {
  const dispatch = useAppDispatch();
  const { t } = useTranslation();

  const [searchParams] = useSearchParams();
  const id = searchParams.get("id");

  const biblioReferenceForm = useAppSelector((state) => {
    return state.Forms.BiblioReferenceForm;
  });

  useEffect(() => {
    dispatch(fetchBiblioReferenceForm(id));
    dispatch(fetchLoggedUserDetails());
  }, [dispatch, id]);

  return (
    <AppPageContentWrapper>
      <PageContentBox>
        <Box sx={{ width: "100%" }}>
          {biblioReferenceForm.dataStateStatus === THUNK_STATUS.FULFILLED ? (
            <ServiceStepper
              serviceSteps={biblioReferenceForm.steps}
              applicationSubtype={ApplicationSubtype.BIBLIO_REFERENCE}
            />
          ) : null}
          {biblioReferenceForm.dataStateStatus === THUNK_STATUS.REJECTED ? (
            <Alert severity={"error"}>{t("m.generic.error.service.fail")}</Alert>
          ) : null}
          {biblioReferenceForm.dataStateStatus === THUNK_STATUS.PENDING ? <CircularTextLoader /> : null}
        </Box>
      </PageContentBox>
    </AppPageContentWrapper>
  );
};
export default BiblioReferenceEdit;
