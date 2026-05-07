import React from "react";

import { InputFormField, DateFormField, GridItem, GridContainer, FormSection } from "@duosoftbg/nacid-components";

const DiplomaFormSection = () => {
  return (
    <FormSection label={"t.diploma.details"}>
      <GridContainer spacing={4} mt={0}>
        <GridItem>
          <InputFormField fieldName={"diploma.series"} labelCode={"l.diploma.series"} />
        </GridItem>
        <GridItem>
          <InputFormField fieldName={"diploma.number"} labelCode={"l.diploma.number"} />
        </GridItem>
        <GridItem>
          <InputFormField fieldName={"diploma.registrationNumber"} labelCode={"l.diploma.registrationNumber"} />
        </GridItem>
      </GridContainer>
      <GridContainer spacing={4} mt={0}>
        <GridItem>
          <DateFormField fieldName={"diploma.date"} labelCode={"l.diploma.date"} />
        </GridItem>
      </GridContainer>
    </FormSection>
  );
};

export default DiplomaFormSection;
