import { getUniversitiesAutocomplete } from "../../../../../../../services/autocompleteCalls";
import { useWatch } from "react-hook-form";
import SelectOrWriteAutocompleteFormField from "../../../../../common/form/SelectOrWriteAutocompleteFormField";

const UniversityAutocompleteFormField = ({ nameField, nameIdField, labelCode, required = false }) => {
  const name = useWatch({ name: nameField });
  const id = useWatch({ name: nameIdField });

  return (
    <SelectOrWriteAutocompleteFormField
      required={required}
      inputMinSearchLength={3}
      textFieldName={nameField}
      idFieldName={nameIdField}
      setOptionText={(option) => option.name}
      autocompleteFn={getUniversitiesAutocomplete}
      label={labelCode}
      getOptionLabel={(option) => option.id + ""}
      setInputOnSelect={(option) => option.name}
      selectedOption={{ id: id, name: name }}
    />
  );
};
export default UniversityAutocompleteFormField;
