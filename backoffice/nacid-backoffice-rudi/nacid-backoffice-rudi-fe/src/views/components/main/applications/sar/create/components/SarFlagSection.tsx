import { CheckboxFormField, ErrorMessage, FormSection, GridSpg } from "@duosoftbg/nacid-components";
import * as React from "react";

const SarFlagSection = ({ sectionTitle = "l.sarFlag" }) => {
  return (
    <FormSection label={sectionTitle}>
      <GridSpg mt={2} container spacing={1}>
        <GridSpg item xs={12} sm={4}>
          <CheckboxFormField fieldName={"sarFlag.statuteFlag"} labelCode={"l.statuteFlag"} />
        </GridSpg>
        <GridSpg item xs={12} sm={4}>
          <CheckboxFormField fieldName={"sarFlag.authenticityFlag"} labelCode={"l.authenticityFlag"} />
        </GridSpg>
        <GridSpg item xs={12} sm={4}>
          <CheckboxFormField fieldName={"sarFlag.recommendationFlag"} labelCode={"l.recommendationFlag"} />
        </GridSpg>
        <ErrorMessage pointer="sarFlag" />
      </GridSpg>
    </FormSection>
  );
};

export default SarFlagSection;
