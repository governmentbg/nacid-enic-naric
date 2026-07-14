import React, { useEffect } from "react";
import { InputFormField, SelectFormField, GridItem, GridContainer } from "@duosoftbg/nacid-components";
import useAppDispatch from "../../../../../../../hooks/redux/base/useAppDispatch";
import { durationUnitThunk } from "../../../../../../../store/redux/slice/AppData/durationUnit";
import useAppSelector from "../../../../../../../hooks/redux/base/useAppSelector";

const EducationDurationFormFields = ({ startEndFormFields }) => {
  const dispatch = useAppDispatch();

  const durationUnitThunkState = useAppSelector((state) => {
    return state.AppData.DurationUnit;
  });

  useEffect(() => {
    dispatch(durationUnitThunk());
  }, [dispatch]);

  return (
    <GridContainer spacing={4} mt={0}>
      {startEndFormFields}
      <GridItem sm={4} md={3}>
        <InputFormField fieldName={"educationDuration"} labelCode={"l.educationDuration"} />
      </GridItem>
      <GridItem sm={4} md={3}>
        <SelectFormField
          fieldName={"educationDurationType.id"}
          labelCode={"l.educationDurationType"}
          addEmptyOption={false}
          selectOptions={durationUnitThunkState.data.map((option) => {
            return { value: option.id, text: option.name, active: option.isActive };
          })}
        />
      </GridItem>
    </GridContainer>
  );
};

export default EducationDurationFormFields;
