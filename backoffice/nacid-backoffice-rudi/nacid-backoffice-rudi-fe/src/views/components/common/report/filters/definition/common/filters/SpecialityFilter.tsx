import { getSpecialitiesAutocomplete } from "../../../../../../../../axios/api/services";
import React from "react";
import { FilterFormFieldWithMaskArrays, ReloadWatcherObject } from "@duosoftbg/nacid-backoffice-components";

const SpecialityFilter = ({ baseField }) => {
  return (
    <FilterFormFieldWithMaskArrays
      fieldName={`${baseField}.specialities`}
      maskFieldName={`${baseField}.specialityNames`}
      maskListLabel={"l.selected.mask.speciality"}
      listLabel={"l.selected.speciality"}
      autocompleteFn={getSpecialitiesAutocomplete}
      autocompleteLabel={"l.reportFilter.speciality"}
      reloadObject={ReloadWatcherObject.Report.clear()}
    />
  );
};
export default SpecialityFilter;
