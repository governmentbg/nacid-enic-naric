import { DividerSpg, FormSection, GridContainer } from "@duosoftbg/nacid-components";
import JointDegreeFlagFilter from "./filters/JointDegreeFlagFilter";
import DiplomaRegistersFlagFilter from "./filters/DiplomaRegistersFlagFilter";
import UniversityFilters from "./filters/university/UniversityFilters";
import { REPORT_FILTERS_DEFINITION } from "../../../../../../../../config/report/filters/definition";
import { REPORT_CONFIG } from "../../../../../../../../config/report/reportConfig";
import { useReportSectionClearOnUnmount } from "@duosoftbg/nacid-backoffice-components";

const ReportUniversitySection = ({ reportGroup }) => {
  const baseField = "university";

  const { label } = REPORT_FILTERS_DEFINITION[baseField];
  useReportSectionClearOnUnmount(reportGroup, baseField, REPORT_CONFIG);

  return (
    <FormSection label={label}>
      <GridContainer mt={0}>
        <DiplomaRegistersFlagFilter baseField={baseField} />
        <JointDegreeFlagFilter baseField={baseField} />
      </GridContainer>
      <DividerSpg my={4} />
      <UniversityFilters baseField={baseField} />
    </FormSection>
  );
};
export default ReportUniversitySection;
