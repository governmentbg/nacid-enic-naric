import { DividerSpg, FormSection } from "@duosoftbg/nacid-components";
import React from "react";
import SpecialityFilters from "./filters/speciality/SpecialityFilters";
import QualificationFilters from "./filters/qualification/QualificationFilters";
import EduLevelFilters from "./filters/eduLevel/EduLevelFilters";
import { REPORT_FILTERS_DEFINITION } from "../../../../../../../../config/report/filters/definition";
import { REPORT_CONFIG } from "../../../../../../../../config/report/reportConfig";
import { useReportSectionClearOnUnmount } from "@duosoftbg/nacid-backoffice-components";

const ReportDiplomaSpecialitySection = ({ reportGroup }) => {
  const baseField = "diplomaSpeciality";

  const { label } = REPORT_FILTERS_DEFINITION[baseField];
  useReportSectionClearOnUnmount(reportGroup, baseField, REPORT_CONFIG);

  return (
    <FormSection label={label}>
      <SpecialityFilters baseField={baseField} />
      <DividerSpg my={4} />
      <QualificationFilters baseField={baseField} />
      <DividerSpg my={4} />
      <EduLevelFilters baseField={baseField} />
    </FormSection>
  );
};
export default ReportDiplomaSpecialitySection;
