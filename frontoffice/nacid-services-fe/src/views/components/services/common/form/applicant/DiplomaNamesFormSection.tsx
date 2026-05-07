import React from "react";
import { useFormContext, useWatch } from "react-hook-form";
import { CheckboxFormField, GridItem, GridContainer, FormSection } from "@duosoftbg/nacid-components";
import { NaturalPersonNamesFormFields } from "@duosoftbg/nacid-frontoffice-components";

const DiplomaNamesFormSection = () => {
  const { getValues } = useFormContext();

  useWatch({ name: "diplomaNamesDifferent" });

  return (
    <FormSection label={"t.diplomaNames.details"}>
      <GridContainer spacing={4} mt={0}>
        <GridItem md={6}>
          <CheckboxFormField labelCode={"l.person.diplomaNames.different"} fieldName={"diplomaNamesDifferent"} />
        </GridItem>
      </GridContainer>

      {getValues().diplomaNamesDifferent ? (
        <GridContainer spacing={4} mt={0}>
          <NaturalPersonNamesFormFields
            baseField={"diplomaNames"}
            firstNameRequired={true}
            middleNameRequired={false}
            lastNameRequired={true}
          />
        </GridContainer>
      ) : null}
    </FormSection>
  );
};

export default DiplomaNamesFormSection;
