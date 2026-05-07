import { BoxSpg, GridContainer, GridItem, InputFormField } from "@duosoftbg/nacid-components";
import SearchUniversityDialogFormProvider from "./SearchUniversityDialogFormProvider";
import SearchUniversityDialogResult from "./SearchUniversityDialogResult";
import { CountrySelectField } from "@duosoftbg/nacid-backoffice-components";

const SearchUniversityDialogContent = ({ universityIdPointer, tempDataKey }) => {
  return (
    <BoxSpg>
      <SearchUniversityDialogFormProvider>
        <SearchFields />
      </SearchUniversityDialogFormProvider>
      <SearchUniversityDialogResult universityIdPointer={universityIdPointer} tempDataKey={tempDataKey} />
    </BoxSpg>
  );
};

const SearchFields = () => {
  return (
    <>
      <GridContainer spacing={4} mt={0}>
        <GridItem md={4}>
          <CountrySelectField field={"country"} label={"l.searchFilter.country"} />
        </GridItem>
        <GridItem md={4}>
          <InputFormField fieldName={"bgName"} labelCode={"l.searchFilter.name"} />
        </GridItem>
        <GridItem md={4}>
          <InputFormField fieldName={"orgName"} labelCode={"l.searchFilter.orgName"} />
        </GridItem>
      </GridContainer>
    </>
  );
};

export default SearchUniversityDialogContent;
