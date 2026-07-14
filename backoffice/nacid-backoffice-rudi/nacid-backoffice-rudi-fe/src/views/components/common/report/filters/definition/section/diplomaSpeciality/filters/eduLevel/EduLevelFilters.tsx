import React from "react";
import { getOriginalEduLevelsAutocomplete } from "../../../../../../../../../../axios/api/services";
import { FilterFormFieldWithMaskArrays, ReloadWatcherObject } from "@duosoftbg/nacid-backoffice-components";

const EduLevelFilters = ({ baseField }) => {
  const baseFieldRevised = `${baseField}.eduLevel`;
  return (
    <>
      <FilterFormFieldWithMaskArrays
        fieldName={`${baseFieldRevised}.originalEduLevels`}
        maskFieldName={`${baseFieldRevised}.originalEduLevelNames`}
        maskListLabel={"l.selected.mask.originalEduLevels"}
        listLabel={"l.selected.originalEduLevels"}
        autocompleteFn={getOriginalEduLevelsAutocomplete}
        autocompleteLabel={"l.reportFilter.originalEduLevel"}
        reloadObject={ReloadWatcherObject.Report.clear()}
      />
    </>
  );
};
export default EduLevelFilters;
