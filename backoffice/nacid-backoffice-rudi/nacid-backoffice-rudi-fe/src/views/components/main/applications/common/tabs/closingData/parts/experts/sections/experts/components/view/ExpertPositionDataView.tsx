import React from "react";
import { ViewSection } from "@duosoftbg/nacid-components";
import ExpertPositionDataViewBody from "./ExpertPositionDataViewBody";

const ExpertPositionDataView = ({ expert }) => {
  return (
    <>
      <ViewSection label={"t.expert.position.data"}>
        <ExpertPositionDataViewBody expert={expert}></ExpertPositionDataViewBody>
      </ViewSection>
    </>
  );
};

export default ExpertPositionDataView;
