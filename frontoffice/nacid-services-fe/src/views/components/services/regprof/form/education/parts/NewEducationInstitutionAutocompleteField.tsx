import { getProfInstitutionsAutocomplete } from "../../../../../../../services/autocompleteCalls";
import SelectOrWriteAutocompleteFormField from "../../../../../common/form/SelectOrWriteAutocompleteFormField";
import React from "react";
import { useWatch } from "react-hook-form";

const NewEducationInstitutionAutocompleteField = ({ field }) => {
  const kind = useWatch({ name: "education.kind" });
  const id = useWatch({ name: `education.${field}.newEducationInstitutionId` });
  const name = useWatch({ name: `education.${field}.newEducationInstitutionName` });
  const oldInstitutionName = useWatch({ name: `education.${field}.oldEducationInstitutionName` });

  return (
    <SelectOrWriteAutocompleteFormField
      autocompleteFn={getProfInstitutionsAutocomplete}
      additionalParams={{ educationType: kind }}
      idFieldName={`education.${field}.newEducationInstitutionId`}
      textFieldName={`education.${field}.newEducationInstitutionName`}
      required={!oldInstitutionName || oldInstitutionName.length === 0}
      label={"l.regprof.education.newEducationInstitutionName"}
      inputMinSearchLength={3}
      setOptionText={(option) => option.name}
      getOptionLabel={(option) => option.id + ""}
      setInputOnSelect={(option) => option.name}
      selectedOption={{ id: id, name: name }}
    />
  );
};

export default NewEducationInstitutionAutocompleteField;
