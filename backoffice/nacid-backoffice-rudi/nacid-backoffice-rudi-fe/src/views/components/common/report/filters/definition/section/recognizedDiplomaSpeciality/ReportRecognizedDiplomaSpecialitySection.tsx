import { DividerSpg, FormSection } from "@duosoftbg/nacid-components";
import React from "react";
import SpecialityFilter from "../../common/filters/SpecialityFilter";
import QualificationFilter from "../../common/filters/QualificationFilter";
import EduLevelFilter from "../../common/filters/EduLevelFilter";
import { REPORT_FILTERS_DEFINITION } from "../../../../../../../../config/report/filters/definition";
import { REPORT_CONFIG } from "../../../../../../../../config/report/reportConfig";
import { useReportSectionClearOnUnmount } from "@duosoftbg/nacid-backoffice-components";

const ReportRecognizedDiplomaSpecialitySection = ({ reportGroup }) => {
  const baseField = "recognizedDiplomaSpeciality";

  const { label } = REPORT_FILTERS_DEFINITION[baseField];
  useReportSectionClearOnUnmount(reportGroup, baseField, REPORT_CONFIG);

  return (
    <FormSection label={label}>
      <SpecialityFilter baseField={baseField} />
      <DividerSpg my={4} />
      <QualificationFilter baseField={baseField} />
      <DividerSpg my={4} />
      <EduLevelFilter baseField={baseField} />
    </FormSection>
  );
};
export default ReportRecognizedDiplomaSpecialitySection;
