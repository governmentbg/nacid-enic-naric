import { CountrySelectField } from "@duosoftbg/nacid-components";
import React from "react";
import { GridContainer, GridItem, FormSection } from "@duosoftbg/nacid-components";
import useAppSelector from "../../../../../../../hooks/redux/base/useAppSelector";
import { countriesDataThunk } from "../../../../../../../store/redux/slice/AppData/countriesData";

const CountryFormSection = () => {
  const thunkStateCountries = useAppSelector((state) => {
    return state.AppData.CountriesData;
  });

  return (
    <FormSection label={"t.regprof.education.country"}>
      <GridContainer>
        <GridItem>
          <CountrySelectField
            countryRequired={true}
            countryField={"country"}
            countryLabel={"l.regprof.education.country"}
            countriesThunkState={thunkStateCountries}
            countriesThunk={countriesDataThunk}
          />
        </GridItem>
      </GridContainer>
    </FormSection>
  );
};
export default CountryFormSection;
