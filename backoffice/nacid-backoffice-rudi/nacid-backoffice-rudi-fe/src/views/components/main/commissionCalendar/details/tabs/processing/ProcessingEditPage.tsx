import {
  AlertSpg,
  AsyncCallArgs,
  CardSpg,
  DividerSpg,
  FormSection,
  FreeSoloAutocompleteFormField,
  GridContainer,
  GridItem,
  isArrayEmpty,
  isEmpty,
  isNotEmpty,
  NomenclatureAutocompleteFormField,
  SimpleFetchAutocompleteFormField,
  TextareaFormField,
  useAsyncCall,
  useReactHookForm,
  useRedirect,
} from "@duosoftbg/nacid-components";
import { FormProvider, useFormContext, useWatch } from "react-hook-form";
import { useTranslation } from "react-i18next";
import { useParams } from "react-router-dom";
import React, { useEffect, useState } from "react";
import {
  getCalendarProcessData,
  getLegalReasonByApplicationAndStatus,
  saveCalendarProcessData,
} from "../../../../../../../axios/api/services";
import PageWrapper from "../../../../../common/layout/PageWrapper";
import CardContent from "@mui/material/CardContent";
import { ProcessDataDetails } from "../../../../../../../types/commissionCalendar/commissionCalendarTypes";
import { commissionCalendarProcessDataInitialValues } from "../../../../../../../init/commissionCalendar/commissionCalendarProcessDataInitialValues";
import { createCommissionCalendarProcessDataValidationSchema } from "../../../../../../../yup/schema/commissionCalendar/commissionCalendarProcessDataValidationSchema";
import { Button, Typography } from "@mui/material";
import {
  AppSubTypeCode,
  AppTypeCode,
  CoreApiServicesBase,
  educationLevelsThunk,
} from "@duosoftbg/nacid-backoffice-components";
import { useSelector } from "react-redux";
import useAppSelector from "../../../../../../../hooks/redux/base/useAppSelector";
import { applicationRecognizedQualificationsThunk } from "../../../../../../../store/redux/slice/AppData/applicationRecognizedQualifications";
import { profGroupsWithAreasThunk } from "../../../../../../../store/redux/slice/AppData/profGroupsWithAreas";
import ApplicantInfoEdit from "./ApplicantInfoEdit";
import { toast } from "react-toastify";
import { ApiEndpoints } from "../../../../../../../axios/api/endpoints";
import SpecialitiesFilter from "../../../../../common/search/filters/definition/autocomplete/SpecialitiesFilter";

const ProcessingEditPage = ({ appType }) => {
  const { t } = useTranslation();
  const { asyncCall } = useAsyncCall();
  const { redirect } = useRedirect();

  const params = useParams();
  const calendarId = params.calendarId;
  const applicationId = params.applicationId;
  const [error, setError] = useState(false);
  const [applicationDocflowNumber, setApplicationDocflowNumber] = useState("");

  const { methods, handleSubmit } = useReactHookForm<ProcessDataDetails>({
    defaultValues: commissionCalendarProcessDataInitialValues,
    validationSchema: createCommissionCalendarProcessDataValidationSchema,
  });

  useEffect(() => {
    const asyncCallArgs: AsyncCallArgs = {
      promise: getCalendarProcessData(calendarId, applicationId),
      withGlobalBackdrop: true,
      onSuccess: (response) => {
        methods.reset(response);
        setError(false);
      },
      onError: () => {
        setError(true);
      },
    };
    asyncCall(asyncCallArgs);

    // eslint-disable-next-line
    }, [calendarId, applicationId]);

  useEffect(() => {
    const asyncCallArgs: AsyncCallArgs = {
      promise: CoreApiServicesBase.getDocflowNumberByApplicationId(applicationId),
      withGlobalBackdrop: true,
      onSuccess: (response) => {
        setApplicationDocflowNumber(response);
      },
    };
    asyncCall(asyncCallArgs);

    // eslint-disable-next-line
    }, [calendarId, applicationId]);

  const onSubmit = (values) => {
    const asyncCreation: AsyncCallArgs = {
      withGlobalBackdrop: true,
      promise: saveCalendarProcessData(values),
      processResponseErrors: true,
      reactHooksForm: { methods },
      onSuccess: (response) => {
        redirect(ApiEndpoints.commissionCalendar.editPage + calendarId);
        toast.success(t("m.create.success"));
      },
    };
    asyncCall(asyncCreation);
  };

  if (error) {
    return (
      <PageWrapper title={t("m.error")}>
        <AlertSpg severity="error">{t("m.error.serverFetchingError")}</AlertSpg>
      </PageWrapper>
    );
  }

  return (
    <PageWrapper
      title={
        applicationDocflowNumber
          ? t("t.commission.calendar.processing.edit").concat(" ").concat(applicationDocflowNumber)
          : ""
      }
    >
      <CardSpg my={4} style={{ overflow: "visible" }}>
        <CardContent style={{ padding: 24, position: "relative" }}>
          <FormProvider {...methods}>
            <form onSubmit={handleSubmit(onSubmit)}>
              <FormSection label={"t.common.data"}>
                <GridContainer spacing={3} mt={0}>
                  <CommissionCalendarApplicationStatusesField appType={appType} />
                </GridContainer>
                <StatusDependentComponents applicationId={applicationId} />
                <GridContainer spacing={3} mt={0}>
                  <ApplicantInfoEdit></ApplicantInfoEdit>
                </GridContainer>
                <GridContainer spacing={3} mt={0}>
                  <GridItem sm={12} md={12}>
                    <TextareaFormField rows={2} fieldName={"motives"} labelCode={"l.motives"} />
                  </GridItem>
                </GridContainer>
              </FormSection>

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

const RecognizedQualificationField = () => {
  const recognizedQualificationsThunkState = useAppSelector((state) => {
    return state.AppData.applicationRecognizedQualifications;
  });

  return (
    <GridItem sm={12} md={12}>
      <FreeSoloAutocompleteFormField
        fieldName={"recognizedQualification"}
        labelCode={"l.recognized.recognizedQualification"}
        thunkFn={applicationRecognizedQualificationsThunk}
        thunkState={recognizedQualificationsThunkState}
      />
    </GridItem>
  );
};

const RecognizedEduLevelField = () => {
  const { getValues } = useFormContext();
  const recognizedEduLevel = getValues().recognizedEduLevel;

  const educationLevelsThunkState = useSelector((state) => {
    return state["ThunkData"].educationLevels;
  });

  return (
    <GridItem sm={12} md={12}>
      <NomenclatureAutocompleteFormField
        onlyActive={true}
        initialValue={recognizedEduLevel}
        fieldName={"recognizedEduLevel"}
        labelCode={"l.recognized.educationLevel"}
        thunkFn={educationLevelsThunk}
        thunkState={educationLevelsThunkState}
      />
    </GridItem>
  );
};

const RecognizedProfGroupField = () => {
  const { getValues } = useFormContext();
  const recognizedProfGroupId = getValues().recognizedProfGroupId;

  const recognizedProfGroupWithAreas = useAppSelector((state) => {
    return state.AppData.profGroupsWithAreas;
  });

  return (
    <GridItem sm={12} md={12}>
      <NomenclatureAutocompleteFormField
        onlyActive={true}
        initialValue={recognizedProfGroupId}
        fieldName={"recognizedProfGroupId"}
        labelCode={"l.recognized.profGroup"}
        thunkFn={profGroupsWithAreasThunk}
        thunkState={recognizedProfGroupWithAreas}
      />
    </GridItem>
  );
};

const CommissionCalendarApplicationStatusesField = ({ appType }) => {
  const { getValues } = useFormContext();
  const statusCode = getValues().statusCode;

  return (
    <GridItem sm={12} md={12}>
      <SimpleFetchAutocompleteFormField
        initialValue={statusCode}
        fieldName={"statusCode"}
        labelCode={"l.calendar.decision"}
        autocompleteFn={() => CoreApiServicesBase.getLegalApplicationStatuses(AppTypeCode.RUDI, AppSubTypeCode.UDIREC)}
      />
    </GridItem>
  );
};

const LegalReasonField = ({ applicationId, statusCode }) => {
  const { getValues } = useFormContext();
  const legalReasonId = getValues().legalReasonId;

  return (
    <GridItem sm={12} md={12}>
      <SimpleFetchAutocompleteFormField
        initialValue={legalReasonId}
        fieldName={"legalReasonId"}
        labelCode={"l.legal.reason"}
        onlyActive={true}
        autocompleteFn={() => getLegalReasonByApplicationAndStatus(applicationId, statusCode)}
      />
    </GridItem>
  );
};

const StatusDependentComponents = ({ applicationId }) => {
  const { asyncCall } = useAsyncCall();
  const { setValue } = useFormContext();
  const statusCode = useWatch({ name: "statusCode" });
  const [hasLegalReasons, setHasLegalReasons] = useState(null);

  useEffect(() => {
    if (isNotEmpty(statusCode)) {
      const asyncCallArgs: AsyncCallArgs = {
        promise: getLegalReasonByApplicationAndStatus(applicationId, statusCode),
        onSuccess: (response) => {
          if (isArrayEmpty(response)) {
            setHasLegalReasons(false);
            setValue("legalReasonId", commissionCalendarProcessDataInitialValues.legalReasonId);
          } else {
            setHasLegalReasons(true);
            setValue("recognizedEduLevel", commissionCalendarProcessDataInitialValues.recognizedEduLevel);
            setValue("recognizedProfGroupId", commissionCalendarProcessDataInitialValues.recognizedProfGroupId);
            setValue("specialities", commissionCalendarProcessDataInitialValues.specialities);
            setValue("recognizedQualification", commissionCalendarProcessDataInitialValues.recognizedQualification);
          }
        },
      };
      asyncCall(asyncCallArgs);
    } else {
      setValue("legalReasonId", commissionCalendarProcessDataInitialValues.legalReasonId);
      setValue("recognizedEduLevel", commissionCalendarProcessDataInitialValues.recognizedEduLevel);
      setValue("recognizedProfGroupId", commissionCalendarProcessDataInitialValues.recognizedProfGroupId);
      setValue("specialities", commissionCalendarProcessDataInitialValues.specialities);
      setValue("recognizedQualification", commissionCalendarProcessDataInitialValues.recognizedQualification);
    }

    // eslint-disable-next-line
  }, [statusCode]);

  if (isEmpty(statusCode) || hasLegalReasons === null) {
    return null;
  }

  if (hasLegalReasons) {
    return (
      <GridContainer spacing={3} mt={0}>
        <LegalReasonField applicationId={applicationId} statusCode={statusCode} />
      </GridContainer>
    );
  }

  if (!hasLegalReasons) {
    return (
      <>
        <GridContainer spacing={3} mt={0}>
          <RecognizedEduLevelField />
          <RecognizedProfGroupField />
        </GridContainer>
        <GridItem sm={12} md={12}>
          <SpecialitiesFilter />
        </GridItem>
        <GridContainer spacing={3} mt={0}>
          <RecognizedQualificationField />
        </GridContainer>
      </>
    );
  }

  return null;
};
export default ProcessingEditPage;
