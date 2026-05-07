import React from "react";
import { FilterLabelProps, BaseInputFieldFilter } from "@duosoftbg/nacid-components";

const SessionNumFilter = ({ label = "l.searchFilter.sessionNum", ...others }: FilterLabelProps) => {
  return <BaseInputFieldFilter fieldName={"sessionNum"} label={label} {...others} />;
};

export default SessionNumFilter;
