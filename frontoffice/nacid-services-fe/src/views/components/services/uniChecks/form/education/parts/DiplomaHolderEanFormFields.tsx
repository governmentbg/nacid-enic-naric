import { GridContainer, GridItem, InputFormField } from "@duosoftbg/nacid-components";

const DiplomaHolderEanFormFields = () => {
  return (
    <GridContainer spacing={4} mt={0}>
      <GridItem>
        <InputFormField fieldName={"diplomaHolderEan"} labelCode={"l.diplomaHolderEan"} />
      </GridItem>
    </GridContainer>
  );
};
export default DiplomaHolderEanFormFields;
