import { getProfInstitutionFormerNamesAutocomplete } from "../../../../../../../services/autocompleteCalls";
import SelectOrWriteAutocompleteFormField from "../../../../../common/form/SelectOrWriteAutocompleteFormField";
import React, { useEffect } from "react";
import { useFormContext, useWatch } from "react-hook-form";

const OldEducationInstitutionAutocompleteField = ({ field }) => {
  const { setValue } = useFormContext();

  const id = useWatch({ name: `education.${field}.oldEducationInstitutionId` });
  const name = useWatch({ name: `education.${field}.oldEducationInstitutionName` });
  const newInstitutionId = useWatch({ name: `education.${field}.newEducationInstitutionId` });
  const newInstitutionName = useWatch({ name: `education.${field}.newEducationInstitutionName` });

  useEffect(() => {
    setValue(`education.${field}.oldEducationInstitutionId`, "");
    setValue(`education.${field}.oldEducationInstitutionName`, "");
  }, [setValue, newInstitutionId, field]);

  return (
    <SelectOrWriteAutocompleteFormField
      autocompleteFn={getProfInstitutionFormerNamesAutocomplete}
      additionalParams={{ profInstitutionId: newInstitutionId }}
      idFieldName={`education.${field}.oldEducationInstitutionId`}
      textFieldName={`education.${field}.oldEducationInstitutionName`}
      required={!newInstitutionName || newInstitutionName.length === 0}
      label={"l.regprof.education.oldEducationInstitutionName"}
      inputMinSearchLength={1}
      setOptionText={(option) => option.name}
      getOptionLabel={(option) => option.id + ""}
      setInputOnSelect={(option) => option.name}
      selectedOption={{ id: id, name: name }}
    />
  );
};

export default OldEducationInstitutionAutocompleteField;
