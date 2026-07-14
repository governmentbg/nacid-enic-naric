import { ArrayFormField, FormSection } from "@duosoftbg/nacid-components";
import React from "react";
import { ReloadWatcherObject, useReportSectionClearOnUnmount } from "@duosoftbg/nacid-backoffice-components";
import { getDocumentReceiveMethods } from "../../../../../../../../axios/api/services";
import { REPORT_FILTERS_DEFINITION } from "../../../../../../../../config/report/filters/definition";
import { REPORT_CONFIG } from "../../../../../../../../config/report/reportConfig";

const ReportDocumentReceiveMethod = ({ reportGroup }) => {
  const baseField = "documentReceiveMethod";

  const { label } = REPORT_FILTERS_DEFINITION[baseField];
  useReportSectionClearOnUnmount(reportGroup, baseField, REPORT_CONFIG);

  return (
    <FormSection label={label}>
      <ArrayFormField
        fieldName={`${baseField}.documentReceiveMethods`}
        listLabel={"l.selected.documentReceiveMethods"}
        autocompleteLabel={"l.reportFilter.documentReceiveMethods"}
        autocompleteFn={getDocumentReceiveMethods}
        reloadObject={ReloadWatcherObject.Report.clear()}
      />
    </FormSection>
  );
};
export default ReportDocumentReceiveMethod;
