import {
  useReactHookForm,
  GridContainer,
  GridItem,
  InputFormField,
  AppPageContentWrapper,
  useAsyncCall,
  AsyncCallArgs,
  AlertSpg,
  showGlobalBackdrop,
  hideGlobalBackdrop,
} from "@duosoftbg/nacid-components";
import { AppCheckup } from "../../../types/appCheckupTypes";
import { toast } from "react-toastify";
import { FormProvider } from "react-hook-form";
import { useTranslation } from "react-i18next";
import { Button, Typography } from "@mui/material";
import React from "react";
import PageWrapper from "../../components/common/layout/PageWrapper";
import { creatAappCheckupValidationSchema } from "../../../yup/appCheckup/appCheckupValidationSchema";
import { buildAppCheckupUrl, getAppSubtypeForCheckup } from "../../../services/appCheckupCalls";
import { useGoogleReCaptcha } from "react-google-recaptcha-v3";
import { useNavigate } from "react-router-dom";
import useAppDispatch from "../../../hooks/redux/base/useAppDispatch";
import { getApplicationBaseUrl } from "../../../utils/applicationUrlUtils";

const ApplicationCheckupPage = () => {
  const { t } = useTranslation();
  const { asyncCall } = useAsyncCall();
  const { executeRecaptcha } = useGoogleReCaptcha();
  const navigate = useNavigate();
  const dispatch = useAppDispatch();

  const { methods } = useReactHookForm<AppCheckup>({
    defaultValues: { dossierNumber: "", accessCode: "" },
    validationSchema: creatAappCheckupValidationSchema,
  });

  const onSubmit = async (values) => {
    dispatch(showGlobalBackdrop());
    try {
      const captcha = await executeRecaptcha("AppCheckup");
      const urlCaptcha = await executeRecaptcha("AppCheckup");

      const submitVals = { ...values, captchaToken: captcha };
      const asyncAppCheckupSubtype: AsyncCallArgs = {
        withGlobalBackdrop: true,
        promise: getAppSubtypeForCheckup(submitVals),
        reactHooksForm: { methods },
        onSuccess: (response) => {
          if (response.data === "") {
            toast.error(t("m.appCheckup.notFound.or.notAllowed"));
          } else {
            const baseAppUrl = getApplicationBaseUrl(response.data);
            const url = buildAppCheckupUrl({ ...values, captchaToken: urlCaptcha }, baseAppUrl);
            navigate(url);
          }
        },
      };
      asyncCall(asyncAppCheckupSubtype);
    } catch (err) {
      dispatch(hideGlobalBackdrop());
      toast.error(t("m.generic.error.service.fail"));
    }
  };

  return (
    <PageWrapper title={t("t.page.appCheckup")}>
      <AppPageContentWrapper>
        <FormProvider {...methods}>
          <form
            onSubmit={methods.handleSubmit(onSubmit, (errors) => {
              console.log(errors);
              toast.error(t("m.validation.errors.present"));
            })}
          >
            <GridContainer>
              <GridItem sm={6} md={6}>
                <AlertSpg severity={"info"} mb={2}>
                  <Typography align={"justify"}>{t("m.appCheckup.instructions")}</Typography>
                </AlertSpg>
              </GridItem>
              <GridItem sm={6} md={6} pr={0}>
                <GridContainer mt={0}>
                  <GridItem sm={12} md={12}>
                    <InputFormField
                      fieldName={"dossierNumber"}
                      labelCode={"l.appCheckup.dossierNumber"}
                      required={true}
                    />
                  </GridItem>
                </GridContainer>
                <GridContainer>
                  <GridItem sm={12} md={12}>
                    <InputFormField fieldName={"accessCode"} labelCode={"l.appCheckup.accessCode"} required={true} />
                  </GridItem>
                </GridContainer>
                <GridContainer>
                  <GridItem sm={12} md={12}>
                    <Typography align={"center"}>
                      <Button type={"submit"} variant={"contained"}>
                        {t("l.btn.search")}
                      </Button>
                    </Typography>
                  </GridItem>
                </GridContainer>
              </GridItem>
            </GridContainer>
          </form>
        </FormProvider>
      </AppPageContentWrapper>
    </PageWrapper>
  );
};
export default ApplicationCheckupPage;
