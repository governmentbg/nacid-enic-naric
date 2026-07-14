import { GridContainer, GridItem, InputFormField, SettlementsAutocompleteFormField } from "@duosoftbg/nacid-components";
import { getSettlement, getSettlementsAutocomplete } from "../../../../../../../services/coreServicesCalls";

const CompanyFormFields = () => {
  return (
    <GridContainer>
      <GridItem>
        <InputFormField
          required={true}
          fieldName={"applicant.company.companyIdentifier"}
          labelCode={"l.company.companyIdentifier"}
        />
      </GridItem>
      <GridItem>
        <InputFormField
          required={true}
          fieldName={"applicant.company.companyName"}
          labelCode={"l.company.companyName"}
        />
      </GridItem>
      <GridItem>
        <SettlementsAutocompleteFormField
          required={true}
          onlyActive={true}
          getSettlementsAutocomplete={getSettlementsAutocomplete}
          getSettlement={getSettlement}
          settlementField={"applicant.company.companySettlement"}
          label={"l.company.companyCity"}
        />
      </GridItem>
    </GridContainer>
  );
};

export default CompanyFormFields;
