import { getUniversityFacultyAutocomplete } from "../../../../../../../services/autocompleteCalls";
import { useFormContext, useWatch } from "react-hook-form";
import { useEffect } from "react";
import SelectOrWriteAutocompleteFormField from "../../../../../common/form/SelectOrWriteAutocompleteFormField";

const FacultyAutocompleteFormField = ({ nameField, nameIdField, uniIdField, labelCode, required = false }) => {
  const { setValue } = useFormContext();

  const uniId = useWatch({ name: uniIdField });
  const name = useWatch({ name: nameField });
  const id = useWatch({ name: nameIdField });

  useEffect(() => {
    setValue(nameIdField, "");
    setValue(nameField, "");
  }, [setValue, nameField, nameIdField, uniId]);

  return (
    <SelectOrWriteAutocompleteFormField
      required={required}
      inputMinSearchLength={0}
      textFieldName={nameField}
      idFieldName={nameIdField}
      setOptionText={(option) => option.name}
      autocompleteFn={getUniversityFacultyAutocomplete}
      label={labelCode}
      getOptionLabel={(option) => option.id + ""}
      setInputOnSelect={(option) => option.name}
      selectedOption={{ id: id, name: name }}
      additionalParams={{ universityId: uniId }}
    />
  );
};
export default FacultyAutocompleteFormField;
