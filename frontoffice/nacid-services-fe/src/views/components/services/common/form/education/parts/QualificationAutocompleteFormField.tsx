import SelectOrWriteAutocompleteFormField from "../../../../../common/form/SelectOrWriteAutocompleteFormField";
import React from "react";
import { useWatch } from "react-hook-form";

const QualificationAutocompleteFormField = ({ labelCode, fieldName, autocompleteFn }) => {
  const qualification = useWatch({ name: fieldName });

  return (
    <SelectOrWriteAutocompleteFormField
      inputMinSearchLength={3}
      textFieldName={fieldName}
      label={labelCode}
      autocompleteFn={autocompleteFn}
      setOptionText={(option) => option.name}
      getOptionLabel={(option) => option.name}
      setInputOnSelect={(option) => option.name}
      selectedOption={{ id: qualification, name: qualification }}
    />
  );
};
export default QualificationAutocompleteFormField;
