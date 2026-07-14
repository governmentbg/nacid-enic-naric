import { GridContainer, GridItem, TextareaFormField } from "@duosoftbg/nacid-components";

const SuggestionDetailsFormFields = () => {
  return (
    <>
      <GridContainer>
        <GridItem sm={12} md={12}>
          <TextareaFormField required={true} rows={5} fieldName={"suggestion"} labelCode={"l.suggestion.suggestion"} />
        </GridItem>
      </GridContainer>
    </>
  );
};
export default SuggestionDetailsFormFields;
