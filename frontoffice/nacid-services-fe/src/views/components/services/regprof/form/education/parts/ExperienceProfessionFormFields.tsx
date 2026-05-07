import { useFormContext, useWatch } from "react-hook-form";
import { GridContainer, GridItem } from "@duosoftbg/nacid-components";
import React from "react";
import SelectOrWriteAutocompleteFormField from "../../../../../common/form/SelectOrWriteAutocompleteFormField";
import { getProfessionNamesAutocomplete } from "../../../../../../../services/autocompleteCalls";

const ExperienceProfessionFormFields = () => {
  const { getValues } = useFormContext();

  useWatch({ name: "experienceSelected" });
  const profession = useWatch({ name: "experience.profession" });

  if (getValues().experienceSelected) {
    return (
      <GridContainer>
        <GridItem sm={12} md={12}>
          <SelectOrWriteAutocompleteFormField
            required={true}
            inputMinSearchLength={3}
            textFieldName={"experience.profession"}
            label={"l.regprof.experience.profession"}
            autocompleteFn={getProfessionNamesAutocomplete}
            setOptionText={(option) => option.name}
            getOptionLabel={(option) => option.name}
            setInputOnSelect={(option) => option.name}
            selectedOption={{ id: profession, name: profession }}
          />
        </GridItem>
      </GridContainer>
    );
  } else {
    return null;
  }
};
export default ExperienceProfessionFormFields;
