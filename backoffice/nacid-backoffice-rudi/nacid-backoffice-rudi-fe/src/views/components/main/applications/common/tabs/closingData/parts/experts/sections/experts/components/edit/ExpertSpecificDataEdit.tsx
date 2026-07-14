import React from "react";
import {
  FormSection,
  GridContainer,
  GridItem,
  isNotEmpty,
  ScrollableAsyncFormAutocomplete,
  TextareaFormField,
} from "@duosoftbg/nacid-components";
import { useFormContext } from "react-hook-form";
import { getCommissionMembersAutocomplete } from "../../../../../../../../../../../../../axios/api/services";

const ExpertSpecificDataEdit = () => {
  const { getValues } = useFormContext();
  return (
    <>
      <FormSection label={"t.expert.data"}>
        <GridContainer spacing={3} mt={0}>
          <GridItem sm={12} md={12}>
            <ScrollableAsyncFormAutocomplete
              required={true}
              fieldName={`commissionMember.id`}
              selectedOption={getValues("commissionMember")}
              setOptionText={(option) => {
                return isNotEmpty(option.middleName)
                  ? `${option.firstName} ${option.middleName} ${option.lastName}`
                  : `${option.firstName} ${option.lastName}`;
              }}
              autocompleteFn={getCommissionMembersAutocomplete}
              label={"l.application.commission.member"}
              reduceOptionObject={false}
              getOptionLabel={(option) => option.id + ""}
              setInputOnSelect={(option) =>
                isNotEmpty(option.middleName)
                  ? `${option.firstName} ${option.middleName} ${option.lastName}`
                  : `${option.firstName} ${option.lastName}`
              }
            />
          </GridItem>
          <GridItem sm={12} md={12}>
            <TextareaFormField fieldName={"notes"} labelCode={"l.notes"} />
          </GridItem>
        </GridContainer>
      </FormSection>
    </>
  );
};

export default ExpertSpecificDataEdit;
