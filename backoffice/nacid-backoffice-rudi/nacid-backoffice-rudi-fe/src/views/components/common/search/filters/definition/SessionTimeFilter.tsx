import { DateFromToFilter, FilterLabelProps } from "@duosoftbg/nacid-components";
import * as React from "react";

const SessionTimeFilter = ({ label = "l.searchFilter.sessionTime", ...others }: FilterLabelProps) => {
  return <DateFromToFilter label={label} from={"sessionTimeFrom"} to={"sessionTimeTo"} {...others} />;
};

export default SessionTimeFilter;
