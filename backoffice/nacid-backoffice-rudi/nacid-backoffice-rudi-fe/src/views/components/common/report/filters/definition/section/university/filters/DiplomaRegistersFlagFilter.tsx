import { CheckboxFormField, GridItem } from "@duosoftbg/nacid-components";
import React from "react";

const DiplomaRegistersFlagFilter = ({ baseField }) => {
  return (
    <GridItem sm={12} md={3} pt={2}>
      <CheckboxFormField
        fieldName={`${baseField}.onlyWithDiplomaRegisters`}
        labelCode={"l.reportFilter.onlyWithDiplomaRegisters"}
      />
    </GridItem>
  );
};
export default DiplomaRegistersFlagFilter;
