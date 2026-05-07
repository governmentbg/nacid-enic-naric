import { useTranslation } from "react-i18next";
import useAppDispatch from "../../../../hooks/redux/base/useAppDispatch";
import useAppSelector from "../../../../hooks/redux/base/useAppSelector";
import {
  FlagSelectFormField,
  BoxSpg,
  ButtonSpg,
  DateFormField,
  GridContainer,
  GridItem,
  InputFormField,
  useReactHookForm,
} from "@duosoftbg/nacid-components";
import React, { useEffect } from "react";
import { clearFilter, updateFilter } from "../../../../store/redux/slice/Forms/myCorrespondenceFilterForm";
import { FormProvider } from "react-hook-form";
import { toast } from "react-toastify";
import { Typography } from "@mui/material";
import { createMyCorrespondenceFilterValidationSchema } from "../../../../yup/myCorrespondence/myCorrespondenceFilterValidationSchema";
import { CorrespondenceListFilter } from "../../../../types/myCorrespondenceTypes";
import { initialMyCorrespondenceFilterValues } from "../../../../init/initialMyCorrespondenceFilterValues";

const MyCorrespondenceFilterForm = () => {
  const { t } = useTranslation();
  const dispatch = useAppDispatch();

  const myCorrespondenceFilterForm = useAppSelector((state) => {
    return state.Forms.MyCorrespondenceFilterForm;
  });

  const { methods } = useReactHookForm<CorrespondenceListFilter>({
    defaultValues: myCorrespondenceFilterForm,
    validationSchema: createMyCorrespondenceFilterValidationSchema,
  });

  const { reset } = methods;

  useEffect(() => {
    reset(myCorrespondenceFilterForm);
  }, [myCorrespondenceFilterForm, reset]);

  const onSubmit = (values) => {
    dispatch(updateFilter({ ...values, page: initialMyCorrespondenceFilterValues.page }));
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
              <DateFormField fieldName={"registrationDateFrom"} labelCode={"l.filter.registrationDateFrom"} />
            </GridItem>
            <GridItem>
              <DateFormField fieldName={"registrationDateTo"} labelCode={"l.filter.registrationDateTo"} />
            </GridItem>
            <GridItem>
              <InputFormField fieldName={"registrationNumber"} labelCode={"l.filter.registrationNumber"} />
            </GridItem>
          </GridContainer>
          <GridContainer spacing={1}>
            <GridItem>
              <DateFormField fieldName={"dateReadFrom"} labelCode={"l.filter.dateReadFrom"} />
            </GridItem>
            <GridItem>
              <DateFormField fieldName={"dateReadTo"} labelCode={"l.filter.dateReadTo"} />
            </GridItem>
            <GridItem>
              <FlagSelectFormField fieldName={"read"} labelCode={"l.filter.read"} addEmptyOption={true} />
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
export default MyCorrespondenceFilterForm;
