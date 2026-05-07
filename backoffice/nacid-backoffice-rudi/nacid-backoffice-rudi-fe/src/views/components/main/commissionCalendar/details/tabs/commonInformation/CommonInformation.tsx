import {
  AlertSpg,
  AsyncCallArgs,
  BoxSpg,
  CircularLoader,
  DateTimeFormField,
  DividerSpg,
  END_DATE,
  GridContainer,
  GridItem,
  isNotEmpty,
  NomenclatureAutocompleteFormField,
  ReloadWatcherObject,
  TextareaFormField,
  useAsyncCall,
  useFormDirtyStateSetter,
  useReactHookForm,
  useRedirect,
  useReloadWatcherWriter,
} from "@duosoftbg/nacid-components";
import { useTranslation } from "react-i18next";
import { useParams } from "react-router-dom";
import { FormProvider } from "react-hook-form";
import { CommissionCalendarDetails } from "../../../../../../../types/commissionCalendar/commissionCalendarTypes";
import { commissionCalendarInitialValues } from "../../../../../../../init/commissionCalendar/commissionCalendarInitialValues";
import { createCommissionCalendarValidationSchema } from "../../../../../../../yup/schema/commissionCalendar/commissionCalendarValidationSchema";
import React, { useEffect, useState } from "react";
import {
  createCommissionCalendar,
  selectCommissionCalendar,
  updateCommissionCalendar,
} from "../../../../../../../axios/api/services";
import { Button, Typography } from "@mui/material";
import { toast } from "react-toastify";
import useAppSelector from "../../../../../../../hooks/redux/base/useAppSelector";
import { commissionCalendarStatusesThunk } from "../../../../../../../store/redux/slice/AppData/commissionCalendarStatuses";
import { ApiEndpoints } from "../../../../../../../axios/api/endpoints";

const CommonInformation = () => {
  const calendarId = useParams().calendarId;
  const { t } = useTranslation();
  const { asyncCall } = useAsyncCall();
  const { redirect } = useRedirect();
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(false);
  const { updateReloadWatcher } = useReloadWatcherWriter();

  const thunkState = useAppSelector((state) => {
    return state.AppData.commissionCalendarStatuses;
  });

  const { methods, handleSubmit } = useReactHookForm<CommissionCalendarDetails>({
    defaultValues: commissionCalendarInitialValues,
    validationSchema: createCommissionCalendarValidationSchema,
  });

  useFormDirtyStateSetter({ methods });

  useEffect(() => {
    if (calendarId) {
      const asyncCallArgs: AsyncCallArgs = {
        promise: selectCommissionCalendar(calendarId),
        onSuccess: (response) => {
          methods.reset(response);
          setLoading(false);
          setError(false);
        },
        onError: () => {
          setError(true);
          setLoading(false);
        },
      };
      asyncCall(asyncCallArgs);
    } else {
      methods.reset(commissionCalendarInitialValues);
      setLoading(false);
      setError(false);
    }
    // eslint-disable-next-line
  }, [calendarId]);

  const onSubmit = (values) => {
    const asyncCreation: AsyncCallArgs = {
      withGlobalBackdrop: true,
      promise: calendarId ? updateCommissionCalendar(values) : createCommissionCalendar(values),
      reactHooksForm: { methods },
      onSuccess: (response) => {
        if (isNotEmpty(calendarId)) {
          methods.reset(response, {
            keepIsSubmitted: true,
            keepSubmitCount: true,
          });
        } else {
          redirect(ApiEndpoints.commissionCalendar.editPage + response.id);
        }
        toast.success(t("m.create.success"));
        updateReloadWatcher(ReloadWatcherObject.build("calendarCommonInformation", "edit"));
      },
    };
    asyncCall(asyncCreation);
  };

  if (loading) {
    return (
      <BoxSpg>
        <BoxSpg my={5} textAlign={"center"}>
          {t("t.commonInformation")}
        </BoxSpg>
        <BoxSpg>
          <CircularLoader />
        </BoxSpg>
      </BoxSpg>
    );
  }

  if (error) {
    return (
      <BoxSpg>
        <AlertSpg severity="error">{t("m.error.serverFetchingError")}</AlertSpg>
      </BoxSpg>
    );
  }

  return (
    <BoxSpg>
      <BoxSpg my={5} textAlign={"center"}>
        {t("t.commonInformation")}
      </BoxSpg>

      <BoxSpg>
        <FormProvider {...methods}>
          <form onSubmit={handleSubmit(onSubmit)}>
            <GridContainer spacing={3} mt={0}>
              <GridItem sm={4} md={4}>
                <DateTimeFormField
                  minDate={new Date()}
                  maxDate={END_DATE}
                  required={true}
                  fieldName={"sessionTime"}
                  labelCode={"l.sessionTime"}
                />
              </GridItem>
              <GridItem sm={4} md={4}>
                <NomenclatureAutocompleteFormField
                  required={true}
                  initialValue={methods.getValues("status.id")}
                  fieldName={"status.id"}
                  labelCode={"l.commissionStatus"}
                  thunkFn={commissionCalendarStatusesThunk}
                  thunkState={thunkState}
                />
              </GridItem>
              <GridItem sm={12} md={12}>
                <TextareaFormField required={false} fieldName={"notes"} labelCode={"l.notes"} />
              </GridItem>
            </GridContainer>
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
      </BoxSpg>
    </BoxSpg>
  );
};

export default CommonInformation;
