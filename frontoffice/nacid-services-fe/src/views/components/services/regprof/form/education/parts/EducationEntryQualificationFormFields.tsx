import SelectOrWriteAutocompleteFormField from "../../../../../common/form/SelectOrWriteAutocompleteFormField";
import React from "react";
import { useWatch } from "react-hook-form";

const EducationEntryQualificationFormFields = ({ field, qualificationAutocompleteFn }) => {
  const qualification = useWatch({ name: `education.${field}.professionalQualification` });
  const qualificationId = useWatch({ name: `education.${field}.professionalQualificationId` });

  return (
    <SelectOrWriteAutocompleteFormField
      required={true}
      inputMinSearchLength={3}
      textFieldName={`education.${field}.professionalQualification`}
      idFieldName={`education.${field}.professionalQualificationId`}
      setOptionText={(option) => option.name}
      autocompleteFn={qualificationAutocompleteFn}
      label={"l.regprof.education.professionalQualification"}
      getOptionLabel={(option) => option.id + ""}
      setInputOnSelect={(option) => option.name}
      selectedOption={{ id: qualificationId, name: qualification }}
    />
  );
};
export default EducationEntryQualificationFormFields;
