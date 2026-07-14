// TODO: NACIDSE-16
import { useEffect, useState } from "react";
import { ScrollableAsyncAutocomplete, ScrollableAsyncFormAutocomplete } from "@duosoftbg/nacid-components";
import { useFormContext, useWatch } from "react-hook-form";
import { getSubjectAutocomplete } from "../../../axios/api/services";

const SelectOrWriteAutocompleteFormField = ({ label, textFieldName, selectedOption = null }) => {
  const [selected, setSelected] = useState(null);
  const name = useWatch({ name: textFieldName });

  useEffect(() => {
    setSelected(selectedOption);
  }, [selectedOption]);

  const { setValue } = useFormContext();

  const handleInputChange = (option) => {
    setValue(textFieldName, option, { shouldDirty: true });
    setSelected({ id: "", name: option });
  };

  return (
    <ScrollableAsyncAutocomplete
      isSmall={true}
      inputMinSearchLength={3}
      onChangeSelected={(option) => {
        setValue(textFieldName, option !== null ? option.name : "", { shouldDirty: true });
        setSelected(option);
      }}
      onRemoveInputValue={() => {
        setValue(textFieldName, "", { shouldDirty: true });
        setSelected(null);
      }}
      onInputChange={handleInputChange}
      serviceFn={getSubjectAutocomplete}
      setOptionText={(option) => option.name}
      label={label}
      getOptionLabel={(option) => option.name + ""}
      setInputOnSelect={(option) => option.name}
      selectedOption={{ name: name }}
    />
  );
};
export default SelectOrWriteAutocompleteFormField;
