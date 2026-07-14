import { FilterFormFieldWithMaskArrays, ReloadWatcherObject } from "@duosoftbg/nacid-backoffice-components";
import { DividerSpg } from "@duosoftbg/nacid-components";
import { getOriginalQualificationsAutocomplete } from "../../../../../../../../../../axios/api/services";
import QualificationFilter from "../../../../common/filters/QualificationFilter";

const QualificationFilters = ({ baseField }) => {
  const baseFieldRevised = `${baseField}.qualification`;

  return (
    <>
      <QualificationFilter baseField={baseFieldRevised} />
      <DividerSpg my={4} />
      <FilterFormFieldWithMaskArrays
        fieldName={`${baseFieldRevised}.originalQualifications`}
        maskFieldName={`${baseFieldRevised}.originalQualificationNames`}
        maskListLabel={"l.selected.mask.originalQualifications"}
        listLabel={"l.selected.originalQualifications"}
        autocompleteFn={getOriginalQualificationsAutocomplete}
        autocompleteLabel={"l.reportFilter.originalQualification"}
        reloadObject={ReloadWatcherObject.Report.clear()}
      />
    </>
  );
};
export default QualificationFilters;
