// TODO: NACIDSE-16
import { GridContainer, GridItem, InputFormField } from "@duosoftbg/nacid-components";
import SelectOrWriteAutocompleteFormField from "./SelectOrWriteAutocompleteFormField";
import { useWatch } from "react-hook-form";

const SubjectFormFields = ({ index }) => {
  const name = useWatch({ name: `educationSubject.${index}.subject.name` });

  return (
    <GridContainer>
      <GridItem sm={12} md={8}>
        <SelectOrWriteAutocompleteFormField
          textFieldName={`educationSubject.${index}.subject.name`}
          label={"l.educationSubject.name"}
          selectedOption={{ name: name }}
        />
      </GridItem>
      <GridItem sm={12} md={4} pr={0}>
        <InputFormField
          required={true}
          fieldName={`educationSubject.${index}.grade`}
          labelCode={"l.educationSubject.grade"}
        />
      </GridItem>
    </GridContainer>
  );
};
export default SubjectFormFields;
