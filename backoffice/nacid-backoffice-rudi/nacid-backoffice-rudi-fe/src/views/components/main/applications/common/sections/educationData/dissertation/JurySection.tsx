import { FormSection, GridContainer, GridItem, GridSpg, InputFormField } from "@duosoftbg/nacid-components";
import React from "react";

const JurySection = () => {
  return (
    <>
      <FormSection label={"t.jury.title"}>
        <GridSpg container spacing={1}>
          <GridSpg item xs={12}>
            <GridContainer>
              <GridItem sm={6} md={6} lg={6}>
                <InputFormField fieldName={"scientificSupervisor"} labelCode={"l.scientificSupervisor"} />
              </GridItem>
              <GridItem sm={6} md={6} lg={6}>
                <InputFormField fieldName={"scientificSupervisorEn"} labelCode={"l.scientificSupervisorEn"} />
              </GridItem>
            </GridContainer>

            <GridContainer>
              <GridItem sm={6} md={6} lg={6}>
                <InputFormField fieldName={"reviewers"} labelCode={"l.reviewers"} />
              </GridItem>
              <GridItem sm={6} md={6} lg={6}>
                <InputFormField fieldName={"reviewersEn"} labelCode={"l.reviewersEn"} />
              </GridItem>
            </GridContainer>

            <GridContainer>
              <GridItem sm={6} md={6} lg={6}>
                <InputFormField fieldName={"juryChair"} labelCode={"l.juryChair"} />
              </GridItem>
              <GridItem sm={6} md={6} lg={6}>
                <InputFormField fieldName={"juryChairEn"} labelCode={"l.juryChairEn"} />
              </GridItem>
            </GridContainer>

            <GridContainer>
              <GridItem sm={6} md={6} lg={6}>
                <InputFormField fieldName={"juryMembers"} labelCode={"l.juryMembers"} />
              </GridItem>
              <GridItem sm={6} md={6} lg={6}>
                <InputFormField fieldName={"juryMembersEn"} labelCode={"l.juryMembersEn"} />
              </GridItem>
            </GridContainer>
          </GridSpg>
        </GridSpg>
      </FormSection>
    </>
  );
};

export default JurySection;
