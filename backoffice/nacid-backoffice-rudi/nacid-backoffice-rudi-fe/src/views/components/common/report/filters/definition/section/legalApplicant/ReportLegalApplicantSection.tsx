import { FormSection, isNotEmpty } from "@duosoftbg/nacid-components";
import React from "react";
import { getLegalApplicantsWithAdditionalParamsAutocomplete } from "../../../../../../../../axios/api/services";
import LegalNatureCheckboxFilter from "./legalNature/LegalNatureCheckboxFilter";
import {
  FilterFormFieldWithMaskArrays,
  ReloadWatcherObject,
  useReportSectionClearOnUnmount,
} from "@duosoftbg/nacid-backoffice-components";
import { REPORT_FILTERS_DEFINITION } from "../../../../../../../../config/report/filters/definition";
import { REPORT_CONFIG } from "../../../../../../../../config/report/reportConfig";

const ReportLegalApplicantSection = ({ reportGroup }) => {
  const baseField = "legalApplicant";

  const { label } = REPORT_FILTERS_DEFINITION[baseField];
  useReportSectionClearOnUnmount(reportGroup, baseField, REPORT_CONFIG);

  return (
    <FormSection label={label}>
      <LegalNatureCheckboxFilter baseField={baseField} />
      <FilterFormFieldWithMaskArrays
        fieldName={`${baseField}.legalApplicants`}
        maskFieldName={`${baseField}.legalApplicantNames`}
        watchField={`${baseField}.legalNatureTypes`}
        watchFieldParamName={"legalNatureTypes"}
        clearOnWatchChange={false}
        maskListLabel={"l.selected.mask.legalApplicants"}
        listLabel={"l.selected.legalApplicants"}
        autocompleteFn={getLegalApplicantsWithAdditionalParamsAutocomplete}
        autocompleteLabel={"l.reportFilter.legalApplicants"}
        reduceOptionObject={false}
        setOptionText={(option) => (isNotEmpty(option.eik) ? `${option.eik} - ${option.name}` : option.name || option)}
        reloadObject={ReloadWatcherObject.Report.clear()}
      />
    </FormSection>
  );
};
export default ReportLegalApplicantSection;
