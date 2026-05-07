import { CoreApiServicesBase, ReloadWatcherObject } from "@duosoftbg/nacid-backoffice-components";
import { ArrayFormField, ReferenceDataDomain } from "@duosoftbg/nacid-components";
import React from "react";

const EduLevelFilter = ({ baseField }) => {
  return (
    <ArrayFormField
      fieldName={`${baseField}.eduLevels`}
      listLabel={"l.selected.eduLevels"}
      autocompleteLabel={"l.reportFilter.eduLevel"}
      autocompleteFn={() => CoreApiServicesBase.getReferenceDataOptions(ReferenceDataDomain.EDUCATION_LEVEL)}
      reloadObject={ReloadWatcherObject.Report.clear()}
    />
  );
};
export default EduLevelFilter;
