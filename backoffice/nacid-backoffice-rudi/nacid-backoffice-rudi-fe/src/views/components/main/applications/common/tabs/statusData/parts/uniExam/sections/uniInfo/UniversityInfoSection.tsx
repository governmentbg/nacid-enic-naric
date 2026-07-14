import { BlockText, FormSection, GridContainer, GridItem } from "@duosoftbg/nacid-components";
import { useFormContext } from "react-hook-form";
import * as React from "react";

const UniversityInfoSection = () => {
  const { getValues } = useFormContext();
  const university = getValues("university");

  return (
    <FormSection label={"l.university"}>
      <GridContainer spacing={4} mt={0}>
        {university?.bgName && (
          <GridItem md={4}>
            <BlockText label={"l.university.bg.name"} text={university?.bgName} />
          </GridItem>
        )}
        {university?.orgName && (
          <GridItem md={4}>
            <BlockText label={"l.university.original.name"} text={university?.orgName} />
          </GridItem>
        )}
        {university?.country?.name && (
          <GridItem md={2}>
            <BlockText label={"l.country"} text={university?.country?.name} />
          </GridItem>
        )}
        {university?.address?.city && (
          <GridItem md={2}>
            <BlockText label={"l.city"} text={university?.address?.city} />
          </GridItem>
        )}
      </GridContainer>
    </FormSection>
  );
};
export default UniversityInfoSection;
