import React from "react";
import UniversityArrayFormFields from "./parts/UniversityArrayFormFields";
import { FormSection } from "@duosoftbg/nacid-components";

const UniversityFormSection = () => {
  return (
    <FormSection label={"t.university.details"}>
      <UniversityArrayFormFields />
    </FormSection>
  );
};

export default UniversityFormSection;
