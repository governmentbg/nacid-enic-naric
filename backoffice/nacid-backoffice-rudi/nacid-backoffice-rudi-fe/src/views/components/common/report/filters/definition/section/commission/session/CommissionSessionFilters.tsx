import { DateFromToFilter, GridContainer, NumberFromToFilter } from "@duosoftbg/nacid-components";
import React from "react";

const CommissionSessionFilters = ({ baseField }) => {
  return (
    <React.Fragment>
      <GridContainer mt={0}>
        <NumberFromToFilter
          pr={4}
          label={"l.reportFilter.sessionNumber"}
          from={`${baseField}.sessionNumberFrom`}
          to={`${baseField}.sessionNumberTo`}
        />
        <DateFromToFilter
          pr={4}
          label={"l.reportFilter.sessionDate"}
          from={`${baseField}.sessionDateFrom`}
          to={`${baseField}.sessionDateTo`}
        />
      </GridContainer>
    </React.Fragment>
  );
};
export default CommissionSessionFilters;
