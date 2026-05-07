import {
  CheckboxFormField,
  FormSection,
  GridContainer,
  GridItem,
  NomenclatureAutocompleteFormField,
} from "@duosoftbg/nacid-components";
import React from "react";
import { useFormContext } from "react-hook-form";
import { trainingProgramTypesThunk } from "../../../../../../../../../../store/redux/slice/AppData/trainingProgramTypes";
import { useSelector } from "react-redux";

const LegitimacySection = () => {
  const { getValues } = useFormContext();

  const trainingProgramTypesThunkState = useSelector((state) => {
    return state["AppData"].trainingProgramTypes;
  });

  return (
    <FormSection label={"l.program.legitimacy"}>
      <GridContainer spacing={4} mt={0}>
        <GridItem sm={12} md={6}>
          <CheckboxFormField fieldName={"isLegitimate"} labelCode={"l.program.isLegitimate"} />
        </GridItem>
      </GridContainer>
      <GridContainer spacing={4} mt={0}>
        <GridItem sm={12} md={6}>
          <NomenclatureAutocompleteFormField
            onlyActive={true}
            required={true}
            initialValue={getValues("programTypeId")}
            fieldName={"programTypeId"}
            labelCode={"l.program.programType"}
            thunkFn={trainingProgramTypesThunk}
            thunkState={trainingProgramTypesThunkState}
            sortColumn={"index"}
          />
        </GridItem>
      </GridContainer>
    </FormSection>
  );
};
export default LegitimacySection;
