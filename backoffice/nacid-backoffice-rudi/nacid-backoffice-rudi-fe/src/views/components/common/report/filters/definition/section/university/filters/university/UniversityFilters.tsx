import { SectionArrayFormFieldControl, GridContainer, GridItem } from "@duosoftbg/nacid-components";
import { universityInitialValues } from "../../../../../../../../../../init/report/commonReportSearchFiltersInitialValues";
import UniversityFiltersFormFields from "./UniversityFiltersFormFields";

const UniversityFilters = ({ baseField }) => {
  const fieldName = `${baseField}.universities`;

  return (
    <GridContainer mt={0}>
      <GridItem sm={12} md={12}>
        <SectionArrayFormFieldControl
          field={fieldName}
          renderFormFields={(index, key) => {
            return <UniversityFiltersFormFields index={index} baseField={fieldName} key={key} />;
          }}
          initialValues={universityInitialValues}
          addBtnLabelCode={"l.btn.university.add"}
          removeBtnLabelCode={"l.btn.university.remove"}
          formLabelCode={"l.university"}
          titlePosition={"global"}
          removeBtnPosition={"right"}
        />
      </GridItem>
    </GridContainer>
  );
};
export default UniversityFilters;
