import SelectOrWriteAutocompleteFormField from "../../../../../common/form/SelectOrWriteAutocompleteFormField";
import React from "react";
import { useWatch } from "react-hook-form";

const SpecialityAutocompleteFormField = ({
  specialityField,
  specialityIdField,
  labelCode,
  required = false,
  autocompleteFn,
  additionalParams = null,
}) => {
  const speciality = useWatch({ name: specialityField });
  const specialityId = useWatch({ name: specialityIdField != null ? specialityIdField : specialityField });

  return (
    <SelectOrWriteAutocompleteFormField
      required={required}
      inputMinSearchLength={2}
      textFieldName={specialityField}
      idFieldName={specialityIdField}
      label={labelCode}
      additionalParams={additionalParams}
      autocompleteFn={autocompleteFn}
      setOptionText={(option) => option.name}
      getOptionLabel={(option) => option.name}
      setInputOnSelect={(option) => option.name}
      selectedOption={{ id: specialityId, name: speciality }}
    />
  );
};
export default SpecialityAutocompleteFormField;
