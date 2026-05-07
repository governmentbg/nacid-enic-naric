import { ArrayFormField, FormSection } from "@duosoftbg/nacid-components";
import { ReloadWatcherObject, useReportSectionClearOnUnmount } from "@duosoftbg/nacid-backoffice-components";
import { getRudiApplicationUsersCreated } from "../../../../../../../../axios/api/services";
import React from "react";
import { REPORT_FILTERS_DEFINITION } from "../../../../../../../../config/report/filters/definition";
import { REPORT_CONFIG } from "../../../../../../../../config/report/reportConfig";

const ReportApplicationUserCreatedSection = ({ reportGroup }) => {
  const baseField = "applicationUserCreated";

  const { label } = REPORT_FILTERS_DEFINITION[baseField];
  useReportSectionClearOnUnmount(reportGroup, baseField, REPORT_CONFIG);

  return (
    <FormSection label={label}>
      <ArrayFormField
        fieldName={`${baseField}.users`}
        listLabel={"l.selected.users"}
        autocompleteLabel={"l.reportFilter.users"}
        autocompleteFn={getRudiApplicationUsersCreated}
        reloadObject={ReloadWatcherObject.Report.clear()}
      />
    </FormSection>
  );
};
export default ReportApplicationUserCreatedSection;
