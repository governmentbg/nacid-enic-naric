import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import { FormSection } from "@duosoftbg/nacid-components";
import { ContactAddressFormFields } from "@duosoftbg/nacid-frontoffice-components";
import { countriesDataThunk } from "../../../../../../store/redux/slice/AppData/countriesData";
import { getSettlement, getSettlementsAutocomplete } from "../../../../../../services/coreServicesCalls";
import React from "react";
import HasContactAddressFormFields from "./parts/HasContactAddressFormFields";
import { useWatch } from "react-hook-form";

const LibContactAddressFormSection = () => {
  const hasCA = useWatch({ name: "hasContactAddress" });
  const thunkStateCountries = useAppSelector((state) => {
    return state.AppData.CountriesData;
  });

  return (
    <FormSection label={"t.contactAddress.details"}>
      <HasContactAddressFormFields />
      {hasCA && (
        <ContactAddressFormFields
          countriesThunk={countriesDataThunk}
          countriesThunkState={thunkStateCountries}
          getSettlementsAutocomplete={getSettlementsAutocomplete}
          getSettlement={getSettlement}
        />
      )}
    </FormSection>
  );
};
export default LibContactAddressFormSection;
