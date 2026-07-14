import * as React from "react";
import { useEffect, useState } from "react";
import {
  AlertSpg,
  AsyncCallArgs,
  CardSpg,
  DividerSpg,
  GridContainer,
  GridItem,
  isArrayNotEmpty,
  useAsyncCall,
  useReactHookForm,
  useScrollOnValidationError,
  ValidationErrors,
} from "@duosoftbg/nacid-components";
import { useTranslation } from "react-i18next";
import CardContent from "@mui/material/CardContent";
import { FormProvider } from "react-hook-form";
import { Button, Typography } from "@mui/material";
import { toast } from "react-toastify";
import ExpertSpecificDataEdit from "./ExpertSpecificDataEdit";
import ExpertPositionDataEdit from "./ExpertPositionDataEdit";
import { ApplicationCommissionMemberDetails } from "../../../../../../../../../../../../../types/applications/common/commissionMembers/applicationCommissionMemberTypes";
import { applicationCommissionMemberInitialValues } from "../../../../../../../../../../../../../init/application/commissionMembers/applicationCommissionMemberInitialValues";
import {
  getApplicationCommissionMember,
  saveApplicationCommissionMember,
} from "../../../../../../../../../../../../../axios/api/services";
import PageWrapper from "../../../../../../../../../../../common/layout/PageWrapper";
import { useNavigate } from "react-router-dom";
import { vCommissionMemberSchema } from "../../../../../../../../../../../../../yup/schema/applications/common/schemas";
import { AppType, NavigationUtils } from "@duosoftbg/nacid-backoffice-components";
import ApplicationSummary from "../../../../../../../../components/ApplicationSummary";

type ExpertEditDetailsProps = {
  appType: AppType;
  applicationId: number | string;
  memberId?: number | string;
};

const ExpertEditDetails = ({ applicationId, memberId = null, appType }: ExpertEditDetailsProps) => {
  const { t } = useTranslation();
  const { asyncCall } = useAsyncCall();
  const [error, setError] = useState(false);
  const navigate = useNavigate();

  const { methods, handleSubmit } = useReactHookForm<ApplicationCommissionMemberDetails>({
    defaultValues: applicationCommissionMemberInitialValues,
    validationSchema: vCommissionMemberSchema,
  });

  useScrollOnValidationError({ methods });

  useEffect(() => {
    if (memberId) {
      const asyncCallArgs: AsyncCallArgs = {
        promise: getApplicationCommissionMember(memberId),
        withGlobalBackdrop: true,
        onSuccess: (response) => {
          methods.reset(response);
          setError(false);
        },
        onError: () => {
          methods.reset(applicationCommissionMemberInitialValues);
          setError(true);
        },
      };
      asyncCall(asyncCallArgs);
    } else {
      methods.reset(applicationCommissionMemberInitialValues);
      setError(false);
    }
    // eslint-disable-next-line
    }, [memberId,applicationId]);

  const onSubmit = () => {
    initsOnSubmit();
    const asyncCreation: AsyncCallArgs = {
      withGlobalBackdrop: true,
      promise: saveApplicationCommissionMember(applicationId, methods.getValues()),
      processResponseErrors: true,
      reactHooksForm: { methods },
      onSuccess: (response) => {
        navigate(NavigationUtils.editApplication(appType, applicationId));
        toast.success(t("m.create.success"));
      },
    };
    asyncCall(asyncCreation);
  };
  const initsOnSubmit = () => {
    if (isArrayNotEmpty(methods.getValues("specialities"))) {
      let applicationCommissionMemberSpecialities = methods.getValues("specialities").map(function (speciality) {
        return { id: null, speciality: speciality };
      });
      methods.setValue("applicationCommissionMemberSpecialities", applicationCommissionMemberSpecialities);
    } else {
      methods.setValue("applicationCommissionMemberSpecialities", []);
    }
    methods.setValue("qualification", methods.getValues("qualificationObject.id"));
  };

  if (error) {
    return (
      <PageWrapper title={t("m.error")}>
        <AlertSpg severity="error">{t("m.error.serverFetchingError")}</AlertSpg>
      </PageWrapper>
    );
  }
  return (
    <PageWrapper title={memberId ? t("t.application.experts.edit") : t("t.application.experts.add")}>
      <ApplicationSummary applicationId={applicationId} appType={appType} />
      <CardSpg my={4} style={{ overflow: "visible" }}>
        <CardContent style={{ padding: 24, position: "relative" }}>
          <FormProvider {...methods}>
            <form onSubmit={handleSubmit(onSubmit)}>
              <ValidationErrors />
              <ExpertSpecificDataEdit />
              <ExpertPositionDataEdit applicationId={applicationId} />
              <DividerSpg my={4} />
              <GridContainer spacing={3}>
                <GridItem sm={12} md={12}>
                  <Typography align={"right"}>
                    <Button type={"submit"} variant={"contained"}>
                      {t("l.btn.save")}
                    </Button>
                  </Typography>
                </GridItem>
              </GridContainer>
            </form>
          </FormProvider>
        </CardContent>
      </CardSpg>
    </PageWrapper>
  );
};

export default ExpertEditDetails;
