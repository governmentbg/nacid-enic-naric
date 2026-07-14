import { useFormContext, useWatch } from "react-hook-form";
import { CheckboxFormField, GridContainer, GridItem, FormSection } from "@duosoftbg/nacid-components";
import React from "react";
import useAppSelector from "../../../../../../../hooks/redux/base/useAppSelector";
import { countriesDataThunk } from "../../../../../../../store/redux/slice/AppData/countriesData";
import { foreignIdTypeThunk } from "../../../../../../../store/redux/slice/AppData/foreignIdType";
import {
  NaturalPersonIdentifierFormFields,
  NaturalPersonNamesFormFields,
} from "@duosoftbg/nacid-frontoffice-components";

const QualificationNamesFormSection = () => {
  const { getValues } = useFormContext();

  const thunkStateCountries = useAppSelector((state) => {
    return state.AppData.CountriesData;
  });

  const thunkStateForeignIdTypes = useAppSelector((state) => {
    return state.AppData.ForeignIdType;
  });

  useWatch({ name: "qualificationNamesDifferent" });

  return (
    <FormSection label={"t.qualificationNames.details"}>
      <GridContainer spacing={4} mt={0}>
        <GridItem sm={12} md={12}>
          <CheckboxFormField
            labelCode={"l.person.qualificationNames.different"}
            fieldName={"qualificationNamesDifferent"}
          />
        </GridItem>
      </GridContainer>

      {getValues().qualificationNamesDifferent ? (
        <GridContainer spacing={4} mt={0}>
          <NaturalPersonNamesFormFields
            baseField={"qualificationNames"}
            firstNameRequired={true}
            middleNameRequired={false}
            lastNameRequired={true}
          />
          <NaturalPersonIdentifierFormFields
            baseField={"qualificationNames"}
            countriesThunk={countriesDataThunk}
            countriesThunkState={thunkStateCountries}
            foreignIdThunk={foreignIdTypeThunk}
            foreignIdThunkState={thunkStateForeignIdTypes}
          />
        </GridContainer>
      ) : null}
    </FormSection>
  );
};
export default QualificationNamesFormSection;
