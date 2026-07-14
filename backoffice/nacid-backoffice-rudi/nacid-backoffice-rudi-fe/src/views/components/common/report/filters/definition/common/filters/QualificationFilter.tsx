import { getQualificationsAutocomplete } from "../../../../../../../../axios/api/services";
import { FilterFormFieldWithMaskArrays, ReloadWatcherObject } from "@duosoftbg/nacid-backoffice-components";

const QualificationFilter = ({ baseField }) => {
  return (
    <FilterFormFieldWithMaskArrays
      fieldName={`${baseField}.qualifications`}
      maskFieldName={`${baseField}.qualificationNames`}
      maskListLabel={"l.selected.mask.qualifications"}
      listLabel={"l.selected.qualifications"}
      autocompleteFn={getQualificationsAutocomplete}
      autocompleteLabel={"l.reportFilter.qualification"}
      reloadObject={ReloadWatcherObject.Report.clear()}
    />
  );
};
export default QualificationFilter;
