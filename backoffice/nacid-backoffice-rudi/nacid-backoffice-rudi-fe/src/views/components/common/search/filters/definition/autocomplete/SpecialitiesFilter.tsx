import { FilterFormStringArrayField } from "@duosoftbg/nacid-components";
import * as React from "react";
import { getSpecialitiesAutocomplete } from "../../../../../../../axios/api/services";

const SpecialitiesFilter = () => {
  return (
    <FilterFormStringArrayField
      freeSolo={true}
      fieldName={`specialities`}
      listLabel={"l.selected.speciality"}
      autocompleteFn={getSpecialitiesAutocomplete}
      autocompleteLabel={"l.reportFilter.speciality"}
    />
  );
};

export default SpecialitiesFilter;
