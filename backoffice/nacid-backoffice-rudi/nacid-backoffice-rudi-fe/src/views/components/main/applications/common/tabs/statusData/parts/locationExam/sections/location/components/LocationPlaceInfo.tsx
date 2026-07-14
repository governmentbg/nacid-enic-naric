import { BlockText, concatNotEmptyBy, GridItem } from "@duosoftbg/nacid-components";
import React from "react";

const LocationPlaceInfo = ({ trainingLocation }) => {
  return (
    <GridItem sm={12} md={12}>
      <BlockText
        label={"l.trainingLocationExam.locationPlace"}
        text={concatNotEmptyBy(", ")(trainingLocation?.country?.name, trainingLocation?.city)}
      />
    </GridItem>
  );
};

export default LocationPlaceInfo;
