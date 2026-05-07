import { FormSection, GridContainer } from "@duosoftbg/nacid-components";
import React from "react";
import { REPORT_FILTERS_DEFINITION } from "../../../../../../../../config/report/filters/definition";
import { REPORT_CONFIG } from "../../../../../../../../config/report/reportConfig";
import { NamesFilter, useReportSectionClearOnUnmount } from "@duosoftbg/nacid-backoffice-components";

const ReportDiplomaNameSection = ({ reportGroup }) => {
  const baseField = "diplomaName";

  const { label } = REPORT_FILTERS_DEFINITION[baseField];
  useReportSectionClearOnUnmount(reportGroup, baseField, REPORT_CONFIG);

  return (
    <FormSection label={label}>
      <GridContainer mt={0}>
        <NamesFilter baseField={baseField} />
      </GridContainer>
    </FormSection>
  );
};
export default ReportDiplomaNameSection;
