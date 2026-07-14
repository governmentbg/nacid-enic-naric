import { CheckboxFormField, GridContainer, GridItem, FormSection } from "@duosoftbg/nacid-components";
import React from "react";
import EducationKindFormFields from "../parts/EducationKindFormFields";
import EducationEntriesSubsection from "./EducationEntriesSubsection";

const RegprofEducationFormSection = () => {
  return (
    <FormSection label={"t.regprof.education"}>
      <GridContainer>
        <GridItem md={6}>
          <CheckboxFormField labelCode={"l.regprof.education"} fieldName={"educationSelected"} />
        </GridItem>
      </GridContainer>
      <EducationKindFormFields />
      <EducationEntriesSubsection />
    </FormSection>
  );
};
export default RegprofEducationFormSection;
