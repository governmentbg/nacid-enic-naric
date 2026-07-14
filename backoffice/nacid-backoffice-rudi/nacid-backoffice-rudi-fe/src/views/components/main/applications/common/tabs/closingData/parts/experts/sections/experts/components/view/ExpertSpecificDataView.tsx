import React from "react";
import { ViewSection } from "@duosoftbg/nacid-components";
import ExpertSpecificDataViewBody from "./ExpertSpecificDataViewBody";

const ExpertSpecificDataView = ({ expert }) => {
  return (
    <>
      <ViewSection label={"t.expert.data"}>
        <ExpertSpecificDataViewBody expert={expert}></ExpertSpecificDataViewBody>
      </ViewSection>
    </>
  );
};

export default ExpertSpecificDataView;
