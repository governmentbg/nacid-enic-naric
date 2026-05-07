import { CheckboxFormField, GridContainer, GridItem, FormSection } from "@duosoftbg/nacid-components";
import React from "react";
import ExperienceProfessionFormFields from "../parts/ExperienceProfessionFormFields";
import ExperienceDocumentArrayFormFields from "../parts/ExperienceDocumentArrayFormFields";
import ExperienceCalculator from "../parts/ExperienceCalculator";

const RegprofExperienceFormSection = () => {
  return (
    <FormSection label={"t.regprof.experience"}>
      <GridContainer>
        <GridItem md={6}>
          <CheckboxFormField labelCode={"l.regprof.experience"} fieldName={"experienceSelected"} />
        </GridItem>
      </GridContainer>
      <ExperienceProfessionFormFields />
      <ExperienceDocumentArrayFormFields />
      <ExperienceCalculator />
    </FormSection>
  );
};
export default RegprofExperienceFormSection;
