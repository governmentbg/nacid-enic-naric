import SelectOrWriteAutocompleteFormField from "../../../../../common/form/SelectOrWriteAutocompleteFormField";
import React from "react";
import { useWatch } from "react-hook-form";
import { getCertificateProfQualificationsAutocomplete } from "../../../../../../../services/autocompleteCalls";

const ProfQualificationRequestedAutocompleteFormField = () => {
  const profQualificationField = useWatch({ name: "professionalQualificationRequested" });

  return (
    <SelectOrWriteAutocompleteFormField
      required={true}
      inputMinSearchLength={2}
      textFieldName={"professionalQualificationRequested"}
      label={"l.regprof.education.professionalQualificationRequested"}
      autocompleteFn={getCertificateProfQualificationsAutocomplete}
      setOptionText={(option) => option.name}
      getOptionLabel={(option) => option.name}
      setInputOnSelect={(option) => option.name}
      selectedOption={{ id: profQualificationField, name: profQualificationField }}
    />
  );
};

export default ProfQualificationRequestedAutocompleteFormField;
