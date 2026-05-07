import React from "react";
import { FormSection } from "@duosoftbg/nacid-components";
import { ContactAddressFormFields } from "@duosoftbg/nacid-frontoffice-components";
import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import { countriesDataThunk } from "../../../../../../store/redux/slice/AppData/countriesData";
import { getSettlement, getSettlementsAutocomplete } from "../../../../../../services/coreServicesCalls";

const ContactAddressFormSection = () => {
  const thunkStateCountries = useAppSelector((state) => {
    return state.AppData.CountriesData;
  });

  return (
    <FormSection label={"t.contactAddress.details"}>
      <ContactAddressFormFields
        countriesThunk={countriesDataThunk}
        countriesThunkState={thunkStateCountries}
        getSettlementsAutocomplete={getSettlementsAutocomplete}
        getSettlement={getSettlement}
      />
    </FormSection>
  );
};
export default ContactAddressFormSection;
