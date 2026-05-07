import { CheckboxFormField, FormSection, GridContainer, GridItem } from "@duosoftbg/nacid-components";
import React from "react";

const LegitimacySection = () => {
  return (
    <FormSection label={"l.trainingLocationExam.legitimacy"}>
      <GridContainer spacing={4} mt={0}>
        <GridItem sm={12} md={6}>
          <CheckboxFormField fieldName={"isLegitimate"} labelCode={"l.trainingLocationExam.isLegitimate"} />
        </GridItem>
      </GridContainer>
    </FormSection>
  );
};
export default LegitimacySection;
