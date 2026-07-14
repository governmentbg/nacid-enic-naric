import { GridContainer, GridItem, InputFormField, FormSection } from "@duosoftbg/nacid-components";
import React from "react";

const ApplicantIncomingNumberFormSection = () => {
  return (
    <FormSection label={"t.uniChecks.applicantIncomingNumber"}>
      <GridContainer>
        <GridItem>
          <InputFormField fieldName={"applicantIncomingNumber"} labelCode={"l.uniChecks.applicantIncomingNumber"} />
        </GridItem>
        <GridItem>
          <InputFormField fieldName={"nacidOutgoingNumber"} labelCode={"l.uniChecks.nacidOutgoingNumber"} />
        </GridItem>
      </GridContainer>
    </FormSection>
  );
};
export default ApplicantIncomingNumberFormSection;
