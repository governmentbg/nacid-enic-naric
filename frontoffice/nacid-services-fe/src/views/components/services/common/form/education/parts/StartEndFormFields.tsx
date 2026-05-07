import { GridItem, YearFormField } from "@duosoftbg/nacid-components";
import React from "react";

const StartEndFormFields = ({ required }) => {
  return (
    <>
      <GridItem sm={4} md={3}>
        <YearFormField required={required} fieldName={"startOfEducation"} labelCode={"l.startOfEducation"} />
      </GridItem>
      <GridItem sm={4} md={3}>
        <YearFormField required={required} fieldName={"endOfEducation"} labelCode={"l.endOfEducation"} />
      </GridItem>
    </>
  );
};
export default StartEndFormFields;
