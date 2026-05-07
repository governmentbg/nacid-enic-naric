import React from "react";
import { FilterLabelProps, GridItem, CheckboxFormField, BoxSpg } from "@duosoftbg/nacid-components";

const FoApplicationTypeFilter = ({ label = "l.searchFilter.foApplicationType", ...others }: FilterLabelProps) => {
  return (
    <BoxSpg ml={6} mt={3} width={"100%"} display={"flex"}>
      <GridItem sm={1} md={1}>
        <CheckboxFormField fieldName={"statute"} labelCode={"l.statute"} />
      </GridItem>
      <GridItem sm={1.3} md={1.3}>
        <CheckboxFormField fieldName={"authenticity"} labelCode={"l.authenticity"} />
      </GridItem>
      <GridItem sm={1.2} md={1.2}>
        <CheckboxFormField fieldName={"recommendation"} labelCode={"l.recommendation"} />
      </GridItem>
    </BoxSpg>
  );
};

export default FoApplicationTypeFilter;
