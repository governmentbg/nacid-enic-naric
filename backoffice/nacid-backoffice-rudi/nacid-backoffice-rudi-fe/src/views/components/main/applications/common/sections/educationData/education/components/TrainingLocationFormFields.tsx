import { GridContainer, InputFormField, GridItem } from "@duosoftbg/nacid-components";
import { CountrySelectField } from "@duosoftbg/nacid-backoffice-components";
import React from "react";

const TrainingLocationFormFields = ({ index, baseField }) => {
  const baseFieldRevised = `${baseField}.${index}`;

  return (
    <>
      <GridContainer mt={index === 0 ? 5 : 4}>
        <GridItem sm={6} md={6} pt={0}>
          <CountrySelectField field={`${baseFieldRevised}.country`} required />
        </GridItem>
        <GridItem sm={6} md={6} pt={0}>
          <InputFormField fieldName={`${baseFieldRevised}.city`} labelCode={"l.city"} required />
        </GridItem>
      </GridContainer>
    </>
  );
};
export default TrainingLocationFormFields;
