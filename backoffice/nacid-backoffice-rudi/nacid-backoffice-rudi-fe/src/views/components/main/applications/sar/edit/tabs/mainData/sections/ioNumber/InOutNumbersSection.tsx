import React from "react";
import { FormSection, GridContainer, GridItem, InputFormField } from "@duosoftbg/nacid-components";

type InOutNumbersSectionProps = {
  sectionTitle?: string;
};

const InOutNumbersSection = ({ sectionTitle = "l.sarInOutNumbers" }: InOutNumbersSectionProps) => {
  return (
    <FormSection label={sectionTitle}>
      <GridContainer spacing={4} mt={0}>
        <GridItem xs={12} sm={6} md={6} lg={6}>
          <InputFormField fieldName={"internalNumber"} labelCode={"l.internalNumber"} />
        </GridItem>
        <GridItem xs={12} sm={6} md={6} lg={6}>
          <InputFormField fieldName={"outgoingNumber"} labelCode={"l.outgoingNumber"} />
        </GridItem>
      </GridContainer>
    </FormSection>
  );
};

export default InOutNumbersSection;
