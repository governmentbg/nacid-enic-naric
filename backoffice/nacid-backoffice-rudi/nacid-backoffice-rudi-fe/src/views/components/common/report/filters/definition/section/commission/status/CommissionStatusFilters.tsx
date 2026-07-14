import { commissionStatusInitialValues } from "../../../../../../../../../init/report/commonReportSearchFiltersInitialValues";
import { GridContainer, GridItem, SectionArrayFormFieldControl } from "@duosoftbg/nacid-components";
import CommissionStatusFilter from "./filters/CommissionStatusFilter";
import { LegalReasonFilter } from "@duosoftbg/nacid-backoffice-components";

const CommissionStatusFilters = () => {
  const baseField = "commission.commissionStatuses";

  return (
    <GridContainer mt={0}>
      <GridItem sm={12} md={12}>
        <SectionArrayFormFieldControl
          field={`${baseField}`}
          renderFormFields={(index, key) => {
            return <CommissionStatusFiltersFormFields index={index} baseField={baseField} key={key} />;
          }}
          initialValues={commissionStatusInitialValues}
          addBtnLabelCode={"l.btn.commission.status.add"}
          removeBtnLabelCode={"l.btn.commission.status.remove"}
          formLabelCode={"l.commissionStatus"}
          titlePosition={"global"}
          removeBtnPosition={"right"}
        />
      </GridItem>
    </GridContainer>
  );
};

const CommissionStatusFiltersFormFields = ({ index, baseField }) => {
  const baseFieldRevised = `${baseField}.${index}`;

  return (
    <>
      <GridContainer mt={index === 0 ? 4 : 0}>
        <CommissionStatusFilter baseField={`${baseFieldRevised}`} />
        <LegalReasonFilter
          baseField={`${baseFieldRevised}`}
          statusField={"commissionStatus"}
          legalReasonsField={"legalReasons"}
        />
      </GridContainer>
    </>
  );
};

export default CommissionStatusFilters;
