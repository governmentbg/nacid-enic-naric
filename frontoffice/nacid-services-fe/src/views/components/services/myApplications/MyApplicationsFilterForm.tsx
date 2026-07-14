import {
  GridContainer,
  GridItem,
  BoxSpg,
  ButtonSpg,
  useReactHookForm,
  DateFormField,
  InputFormField,
  SelectFormField,
  ApplicationSubtype,
} from "@duosoftbg/nacid-components";
import { ApplicationListFilter } from "../../../../types/myApplicationsTypes";
import useAppSelector from "../../../../hooks/redux/base/useAppSelector";
import { toast } from "react-toastify";
import { FormProvider } from "react-hook-form";
import { useTranslation } from "react-i18next";
import { Typography } from "@mui/material";
import React, { useEffect } from "react";
import { createMyApplicationsFilterValidationSchema } from "../../../../yup/myApplications/myApplicationsFilterValidationSchema";
import useAppDispatch from "../../../../hooks/redux/base/useAppDispatch";
import { updateFilter, clearFilter } from "../../../../store/redux/slice/Forms/myApplicationsFilterForm";
import { initialMyApplicationsFilterValues } from "../../../../init/initialMyApplicationsFilterValues";
import StatuteAuthenticityRecommendationFormFields from "./StatuteAuthenticityRecommendationFormFields";

const MyRequestsFilterForm = ({ statuses }) => {
  const { t } = useTranslation();
  const dispatch = useAppDispatch();

  const myApplicationsFilterForm = useAppSelector((state) => {
    return state.Forms.MyApplicationsFilterForm;
  });

  const { methods } = useReactHookForm<ApplicationListFilter>({
    defaultValues: myApplicationsFilterForm,
    validationSchema: createMyApplicationsFilterValidationSchema,
  });

  const { reset } = methods;

  useEffect(() => {
    reset(myApplicationsFilterForm);
  }, [myApplicationsFilterForm, reset]);

  const onSubmit = (values) => {
    dispatch(updateFilter({ ...values, page: initialMyApplicationsFilterValues.page }));
  };

  const onClear = () => {
    dispatch(clearFilter());
  };

  return (
    <BoxSpg m={2}>
      <FormProvider {...methods}>
        <form
          onSubmit={methods.handleSubmit(onSubmit, (errors) => {
            console.log(errors);
            toast.error(t("m.validation.errors.present"));
          })}
        >
          <GridContainer spacing={1}>
            <GridItem>
              <SelectFormField
                fieldName={"lastStatusName"}
                labelCode={"l.filter.status"}
                addEmptyOption={true}
                selectOptions={statuses.map((stat) => {
                  return { value: stat, text: stat };
                })}
              />
            </GridItem>
            <GridItem>
              <SelectFormField
                fieldName={"applicationSubtype"}
                labelCode={"l.filter.applicationSubtype"}
                addEmptyOption={true}
                selectOptions={Object.keys(ApplicationSubtype).map((key) => {
                  return { value: key.valueOf(), text: t("l.application.subtype." + key.valueOf()) };
                })}
              />
            </GridItem>
            <GridItem>
              <StatuteAuthenticityRecommendationFormFields />
            </GridItem>
          </GridContainer>
          <GridContainer spacing={1}>
            <GridItem>
              <DateFormField fieldName={"dateCreatedFrom"} labelCode={"l.filter.dateCreatedFrom"} />
            </GridItem>
            <GridItem>
              <DateFormField fieldName={"dateCreatedTo"} labelCode={"l.filter.dateCreatedTo"} />
            </GridItem>
            <GridItem>
              <InputFormField fieldName={"tempNumber"} labelCode={"l.filter.tempNumber"} />
            </GridItem>
          </GridContainer>
          <GridContainer spacing={1}>
            <GridItem>
              <DateFormField fieldName={"entryDateFrom"} labelCode={"l.filter.entryDateFrom"} />
            </GridItem>
            <GridItem>
              <DateFormField fieldName={"entryDateTo"} labelCode={"l.filter.entryDateTo"} />
            </GridItem>
            <GridItem>
              <InputFormField fieldName={"entryNumber"} labelCode={"l.filter.entryNumber"} />
            </GridItem>
          </GridContainer>
          <GridContainer spacing={1}>
            <GridItem sm={12} md={12}>
              <Typography align={"right"}>
                <ButtonSpg type={"submit"} variant={"contained"}>
                  {t("l.btn.search")}
                </ButtonSpg>
                <ButtonSpg type={"button"} variant={"outlined"} ml={2} onClick={onClear}>
                  {t("l.btn.clear")}
                </ButtonSpg>
              </Typography>
            </GridItem>
          </GridContainer>
        </form>
      </FormProvider>
    </BoxSpg>
  );
};
export default MyRequestsFilterForm;
