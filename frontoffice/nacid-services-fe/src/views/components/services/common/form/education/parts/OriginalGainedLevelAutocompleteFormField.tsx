import { getOriginalEduLevelsAutocomplete } from "../../../../../../../services/autocompleteCalls";
import SelectOrWriteAutocompleteFormField from "../../../../../common/form/SelectOrWriteAutocompleteFormField";
import React from "react";
import { useWatch } from "react-hook-form";

const OriginalGainedLevelAutocompleteFormField = () => {
  const name = useWatch({ name: "originalGainedLevel" });

  return (
    <SelectOrWriteAutocompleteFormField
      autocompleteFn={getOriginalEduLevelsAutocomplete}
      textFieldName={"originalGainedLevel"}
      required={true}
      label={"l.originalGainedLevel"}
      inputMinSearchLength={1}
      setOptionText={(option) => option.name}
      getOptionLabel={(option) => option.id + ""}
      setInputOnSelect={(option) => option.name}
      selectedOption={{ id: name, name: name }}
    />
  );
};

export default OriginalGainedLevelAutocompleteFormField;
