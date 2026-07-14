import React from "react";
import { GridContainer, GridItem, CheckboxFormField } from "@duosoftbg/nacid-components";
import { useFormContext } from "react-hook-form";
import { initialContactAddress } from "../../../../../../../init/common/addressInitialValues";

const HasContactAddressFormFields = ({ spacing = 4, marginTop = 0 }) => {
  const { setValue, getValues } = useFormContext();

  const handleChangeHasCA = () => {
    if (getValues("hasContactAddress")) {
      setValue("contactAddress", initialContactAddress);
    } else {
      setValue("contactAddress", null);
    }
  };

  return (
    <GridContainer spacing={spacing} mt={marginTop}>
      <GridItem sm={12} md={12}>
        <CheckboxFormField
          fieldName={"hasContactAddress"}
          onChange={handleChangeHasCA}
          labelCode={"l.contactAddress.hasContactAddress"}
        />
      </GridItem>
    </GridContainer>
  );
};
export default HasContactAddressFormFields;
