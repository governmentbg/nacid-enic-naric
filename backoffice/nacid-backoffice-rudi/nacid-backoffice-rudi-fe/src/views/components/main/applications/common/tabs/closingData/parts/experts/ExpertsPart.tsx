import * as React from "react";
import { BoxSpg, FormSection, GridContainer, GridItem } from "@duosoftbg/nacid-components";
import ExpertsSection from "./sections/experts/ExpertsSection";
import { useParams } from "react-router-dom";

const ExpertsPart = ({ appType }) => {
  const { id } = useParams();
  return (
    <BoxSpg>
      <FormSection label={"t.appSubSections.experts"}>
        <GridContainer>
          <GridItem sm={12} md={12}>
            <ExpertsSection applicationId={id} appType={appType} />
          </GridItem>
        </GridContainer>
      </FormSection>
    </BoxSpg>
  );
};

export default ExpertsPart;
