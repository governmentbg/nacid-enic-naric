import { GridContainer, GridItem, InputFormField } from "@duosoftbg/nacid-components";
import React from "react";

const ApplicantTitleFormFields = () => {
  return (
    <GridContainer spacing={4} mt={0}>
      <GridItem>
        <InputFormField fieldName={"applicantTitleBefore"} labelCode={"l.applicant.applicantTitleBefore"} />
      </GridItem>
      <GridItem>
        <InputFormField fieldName={"applicantTitleAfter"} labelCode={"l.applicant.applicantTitleAfter"} />
      </GridItem>
    </GridContainer>
  );
};
export default ApplicantTitleFormFields;
