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
import { fetchDocDegreesFormForEdit } from "../../../../store/redux/slice/Forms/docDegreesForm";

const DocDegreesEdit = () => {
  const dispatch = useAppDispatch();
  const { t } = useTranslation();

  const [searchParams] = useSearchParams();
  const id = searchParams.get("id");

  const docDegreesForm = useAppSelector((state) => {
    return state.Forms.DocDegreesForm;
  });

  useEffect(() => {
    dispatch(fetchDocDegreesFormForEdit(id));
  }, [dispatch, id]);

  return (
    <AppPageContentWrapper>
      <PageContentBox>
        <Box sx={{ width: "100%" }}>
          {docDegreesForm.dataStateStatus === THUNK_STATUS.FULFILLED ? (
            <ServiceStepper serviceSteps={docDegreesForm.steps} applicationSubtype={ApplicationSubtype.DOC_DEGREES} />
          ) : null}
          {docDegreesForm.dataStateStatus === THUNK_STATUS.REJECTED ? (
            <Alert severity={"error"}>{t("m.generic.error.service.fail")}</Alert>
          ) : null}
          {docDegreesForm.dataStateStatus === THUNK_STATUS.PENDING ? <CircularTextLoader /> : null}
        </Box>
      </PageContentBox>
    </AppPageContentWrapper>
  );
};
export default DocDegreesEdit;
