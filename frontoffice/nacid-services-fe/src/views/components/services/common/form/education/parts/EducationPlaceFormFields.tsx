import React from "react";
import { InputFormField, GridItem, GridContainer, CountrySelectField } from "@duosoftbg/nacid-components";
import useAppSelector from "../../../../../../../hooks/redux/base/useAppSelector";
import { countriesDataThunk } from "../../../../../../../store/redux/slice/AppData/countriesData";

const EducationPlaceFormFields = ({ index }) => {
  const thunkStateCountries = useAppSelector((state) => {
    return state.AppData.CountriesData;
  });

  return (
    <GridContainer spacing={4} mt={0}>
      <GridItem sm={12} md={4}>
        <CountrySelectField
          countryRequired={true}
          countryField={`educationPlaces.${index}.country`}
          countryLabel={"l.educationPlace.country"}
          countriesThunkState={thunkStateCountries}
          countriesThunk={countriesDataThunk}
        />
      </GridItem>
      <GridItem sm={12} md={8} pr={0}>
        <InputFormField
          required={true}
          fieldName={`educationPlaces.${index}.city`}
          labelCode={"l.educationPlace.city"}
        />
      </GridItem>
    </GridContainer>
  );
};
export default EducationPlaceFormFields;
