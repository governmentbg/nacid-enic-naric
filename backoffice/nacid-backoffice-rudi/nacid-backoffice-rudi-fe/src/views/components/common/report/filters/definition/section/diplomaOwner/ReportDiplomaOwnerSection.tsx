import { FormSection, GridContainer } from "@duosoftbg/nacid-components";
import React from "react";
import { REPORT_FILTERS_DEFINITION } from "../../../../../../../../config/report/filters/definition";
import { REPORT_CONFIG } from "../../../../../../../../config/report/reportConfig";
import {
  NaturalPersonFilter,
  ReportCountryFilter,
  useReportSectionClearOnUnmount,
} from "@duosoftbg/nacid-backoffice-components";

const ReportDiplomaOwnerSection = ({ reportGroup }) => {
  const baseField = "diplomaOwner";

  const { label } = REPORT_FILTERS_DEFINITION[baseField];
  useReportSectionClearOnUnmount(reportGroup, baseField, REPORT_CONFIG);

  return (
    <FormSection label={label}>
      <GridContainer mt={0}>
        <NaturalPersonFilter baseField={baseField} />
        <ReportCountryFilter
          baseField={baseField}
          listLabel={"l.selected.diplomaOwnerCountries"}
          autocompleteLabel={"l.reportFilter.diplomaOwnerCountry"}
        />
      </GridContainer>
    </FormSection>
  );
};
export default ReportDiplomaOwnerSection;
