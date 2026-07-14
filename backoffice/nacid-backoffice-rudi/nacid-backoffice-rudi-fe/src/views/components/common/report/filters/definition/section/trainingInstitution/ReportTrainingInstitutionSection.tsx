import { FormSection, GridContainer, GridItem, SectionArrayFormFieldControl } from "@duosoftbg/nacid-components";
import { trainingInstitutionInitialValues } from "../../../../../../../../init/report/commonReportSearchFiltersInitialValues";
import TrainingInstitutionFiltersFormFields from "./filters/TrainingInstitutionFiltersFormFields";
import { REPORT_FILTERS_DEFINITION } from "../../../../../../../../config/report/filters/definition";
import { REPORT_CONFIG } from "../../../../../../../../config/report/reportConfig";
import { useReportSectionClearOnUnmount } from "@duosoftbg/nacid-backoffice-components";

const ReportTrainingInstitutionSection = ({ reportGroup }) => {
  const baseField = "trainingInstitution";
  const fieldName = "trainingInstitution.trainingInstitutions";

  const { label } = REPORT_FILTERS_DEFINITION[baseField];
  useReportSectionClearOnUnmount(reportGroup, baseField, REPORT_CONFIG);

  return (
    <FormSection label={label}>
      <GridContainer mt={0}>
        <GridItem sm={12} md={12}>
          <SectionArrayFormFieldControl
            field={`${fieldName}`}
            renderFormFields={(index, key) => {
              return <TrainingInstitutionFiltersFormFields index={index} baseField={fieldName} key={key} />;
            }}
            initialValues={trainingInstitutionInitialValues}
            addBtnLabelCode={"l.btn.trainingInstitution.add"}
            removeBtnLabelCode={"l.btn.trainingInstitution.remove"}
            removeBtnPosition={"right"}
          />
        </GridItem>
      </GridContainer>
    </FormSection>
  );
};
export default ReportTrainingInstitutionSection;
