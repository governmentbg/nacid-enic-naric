import * as React from "react";
import { BoxSpg, FormSection, GridContainer, GridItem } from "@duosoftbg/nacid-components";
import StatementsSection from "./sections/statements/StatementsSection";
import { useParams } from "react-router-dom";

const StatementsPart = ({ appType }) => {
  const { id } = useParams();
  return (
    <BoxSpg>
      <FormSection label={"t.appSubSections.statements"}>
        <GridContainer>
          <GridItem sm={12} md={12}>
            <StatementsSection applicationId={id} appType={appType} />
          </GridItem>
        </GridContainer>
      </FormSection>
    </BoxSpg>
  );
};

export default StatementsPart;
