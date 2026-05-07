import { ArrayFormField, FormSection, GridContainer, ReferenceDataDomain } from "@duosoftbg/nacid-components";
import React from "react";
import { REPORT_FILTERS_DEFINITION } from "../../../../../../../../config/report/filters/definition";
import { REPORT_CONFIG } from "../../../../../../../../config/report/reportConfig";
import {
  CoreApiServicesBase,
  NaturalPersonFilter,
  ReloadWatcherObject,
  useReportSectionClearOnUnmount,
} from "@duosoftbg/nacid-backoffice-components";

const ReportNaturalPersonApplicantSection = ({ reportGroup }) => {
  const baseField = "naturalPersonApplicant";

  const { label } = REPORT_FILTERS_DEFINITION[baseField];
  useReportSectionClearOnUnmount(reportGroup, baseField, REPORT_CONFIG);

  return (
    <FormSection label={label}>
      <GridContainer mt={0}>
        <NaturalPersonFilter baseField={baseField} />
      </GridContainer>
      <ArrayFormField
        fieldName={`${baseField}.personalDocumentTypes`}
        listLabel={"l.selected.personalDocumentTypes"}
        autocompleteLabel={"l.reportFilter.personalDocumentTypes"}
        autocompleteFn={() => CoreApiServicesBase.getReferenceDataOptions(ReferenceDataDomain.PERSONAL_DOCUMENT_TYPE)}
        reloadObject={ReloadWatcherObject.Report.clear()}
      />
    </FormSection>
  );
};
export default ReportNaturalPersonApplicantSection;
