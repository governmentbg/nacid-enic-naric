import { GridItem, GridContainer, FormSection, InputFormField } from "@duosoftbg/nacid-components";
import EducationDurationFormFields from "./parts/EducationDurationFormFields";
import EducationFormFormFields from "./parts/EducationFormFormFields";
import React from "react";

const EducationFormSection = ({
  specialityFormSubsection = null,
  eduBeginningFormFields = null,
  startEndFormFields,
  qualificationFormFields,
  sectionLabelCode,
}) => {
  return (
    <FormSection label={sectionLabelCode}>
      {eduBeginningFormFields}
      {specialityFormSubsection}
      {qualificationFormFields}
      <EducationDurationFormFields startEndFormFields={startEndFormFields} />
      <GridContainer spacing={4} mt={0}>
        <EducationFormFormFields />
        <GridItem sm={4} md={3}>
          <InputFormField fieldName={"credits"} labelCode={"l.credits"} />
        </GridItem>
      </GridContainer>
    </FormSection>
  );
};

export default EducationFormSection;
