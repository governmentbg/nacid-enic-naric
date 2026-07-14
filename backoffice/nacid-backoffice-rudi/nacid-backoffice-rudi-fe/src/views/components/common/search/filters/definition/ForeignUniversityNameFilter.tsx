import React from "react";
import { FilterLabelProps, BaseInputFieldFilter } from "@duosoftbg/nacid-components";

const ForeignUniversityNameFilter = ({
  label = "l.searchFilter.foreignUniversityName",
  ...others
}: FilterLabelProps) => {
  return <BaseInputFieldFilter fieldName={"universityName"} label={label} {...others} />;
};

export default ForeignUniversityNameFilter;
