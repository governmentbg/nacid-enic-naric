import { useFormContext, useWatch } from "react-hook-form";
import { FormSection, CopyRecordButton } from "@duosoftbg/nacid-components";
import { ReceiverAddressFormFields } from "@duosoftbg/nacid-frontoffice-components";
import React from "react";
import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import { countriesDataThunk } from "../../../../../../store/redux/slice/AppData/countriesData";
import { getSettlement, getSettlementsAutocomplete } from "../../../../../../services/coreServicesCalls";

const ReceiverAddressFormSection = ({ baseField }) => {
  const { getValues, setValue } = useFormContext();

  const thunkStateCountries = useAppSelector((state) => {
    return state.AppData.CountriesData;
  });

  const hasRecipient = useWatch({ name: `${baseField}.resultReceive.documentRecipient` });

  const onClick = () => {
    setValue(`${baseField}.receiverAddress.city`, getValues().contactAddress.city);
    setValue(`${baseField}.receiverAddress.settlement`, getValues().contactAddress.settlement);
    setValue(`${baseField}.receiverAddress.country`, getValues().contactAddress.country);
    setValue(`${baseField}.receiverAddress.address`, getValues().contactAddress.address);
    setValue(`${baseField}.receiverAddress.postCode`, getValues().contactAddress.postCode);
    setValue(`${baseField}.receiverAddress.phone`, getValues().contactAddress.phone);
  };

  if (hasRecipient) {
    return (
      <FormSection label={"t.receiverAddress.details"}>
        <ReceiverAddressFormFields
          actionButtonComponent={
            <CopyRecordButton
              labelCode={"l.copy.from.contact.address"}
              onClick={onClick}
              mr={0}
              mt={0}
              align={"right"}
            />
          }
          countriesThunk={countriesDataThunk}
          countriesThunkState={thunkStateCountries}
          getSettlementsAutocomplete={getSettlementsAutocomplete}
          getSettlement={getSettlement}
          baseField={`${baseField}.receiverAddress`}
        />
      </FormSection>
    );
  } else {
    return null;
  }
};

export default ReceiverAddressFormSection;
