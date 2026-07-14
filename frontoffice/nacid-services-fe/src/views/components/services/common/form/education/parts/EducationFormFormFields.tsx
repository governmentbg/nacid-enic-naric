import React, { useEffect } from "react";
import { useFormContext, useWatch } from "react-hook-form";
import { InputFormField, GridItem, ReferenceDataCode, SelectFormField } from "@duosoftbg/nacid-components";
import useAppDispatch from "../../../../../../../hooks/redux/base/useAppDispatch";
import useAppSelector from "../../../../../../../hooks/redux/base/useAppSelector";
import { educationFormThunk } from "../../../../../../../store/redux/slice/AppData/educationForm";

const EducationFormFormFields = () => {
  const { getValues } = useFormContext();
  const dispatch = useAppDispatch();

  const educationFormThunkState = useAppSelector((state) => {
    return state.AppData.EducationForm;
  });

  useEffect(() => {
    dispatch(educationFormThunk());
  }, [dispatch]);

  useWatch({ name: "educationForm.id" });

  return (
    <React.Fragment>
      <GridItem sm={4} md={3}>
        <SelectFormField
          addEmptyOption={true}
          fieldName={"educationForm.id"}
          labelCode={"l.educationForm"}
          selectOptions={educationFormThunkState.data.map((option) => {
            return { value: option.id, text: option.name, active: option.isActive };
          })}
        />
      </GridItem>
      {getValues().educationForm !== null && getValues().educationForm.id === ReferenceDataCode.OTHER ? (
        <GridItem sm={4} md={6}>
          <InputFormField fieldName={"educationFormOtherDetails"} labelCode={"l.educationFormOtherDetails"} />
        </GridItem>
      ) : null}
    </React.Fragment>
  );
};
export default EducationFormFormFields;
