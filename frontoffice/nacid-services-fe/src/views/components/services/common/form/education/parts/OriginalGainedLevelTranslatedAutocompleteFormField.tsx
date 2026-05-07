import { getOriginalEduLevelsTranslatedAutocomplete } from "../../../../../../../services/autocompleteCalls";
import SelectOrWriteAutocompleteFormField from "../../../../../common/form/SelectOrWriteAutocompleteFormField";
import React from "react";
import { useWatch } from "react-hook-form";

const OriginalGainedLevelTranslatedAutocompleteFormField = () => {
  const name = useWatch({ name: "originalGainedLevelTranslated" });

  return (
    <SelectOrWriteAutocompleteFormField
      autocompleteFn={getOriginalEduLevelsTranslatedAutocomplete}
      textFieldName={"originalGainedLevelTranslated"}
      required={true}
      label={"l.originalGainedLevelTranslated"}
      inputMinSearchLength={1}
      setOptionText={(option) => option.name}
      getOptionLabel={(option) => option.id + ""}
      setInputOnSelect={(option) => option.name}
      selectedOption={{ id: name, name: name }}
    />
  );
};

export default OriginalGainedLevelTranslatedAutocompleteFormField;
