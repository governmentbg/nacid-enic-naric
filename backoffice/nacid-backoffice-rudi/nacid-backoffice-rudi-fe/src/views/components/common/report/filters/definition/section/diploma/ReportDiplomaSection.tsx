import { CheckboxFormField, FormSection, GridContainer, GridItem, YearFromToFilter } from "@duosoftbg/nacid-components";
import React from "react";
import { REPORT_FILTERS_DEFINITION } from "../../../../../../../../config/report/filters/definition";

const ReportDiplomaSection = ({ reportGroup }) => {
  const baseField = "diploma";

  const { label } = REPORT_FILTERS_DEFINITION[baseField];

  return (
    <FormSection label={label}>
      <GridContainer mt={0}>
        <YearFromToFilter
          pr={4}
          label={"l.reportFilter.diplomaYear"}
          from={`${baseField}.diplomaYearFrom`}
          to={`${baseField}.diplomaYearTo`}
        />
        <GridItem sm={12} md={6} pt={2}>
          <CheckboxFormField fieldName={`${baseField}.isStateApproved`} labelCode={"l.diplomaExam.isStateApproved"} />
        </GridItem>
      </GridContainer>
    </FormSection>
  );
};
export default ReportDiplomaSection;
