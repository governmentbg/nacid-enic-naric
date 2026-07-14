import { FilterFormFieldWithMaskArrays, ReloadWatcherObject } from "@duosoftbg/nacid-backoffice-components";
import { DividerSpg } from "@duosoftbg/nacid-components";
import { getOriginalSpecialitiesAutocomplete } from "../../../../../../../../../../axios/api/services";
import SpecialityFilter from "../../../../common/filters/SpecialityFilter";

const SpecialityFilters = ({ baseField }) => {
  const baseFieldRevised = `${baseField}.speciality`;

  return (
    <>
      <SpecialityFilter baseField={baseFieldRevised} />
      <DividerSpg my={4} />
      <FilterFormFieldWithMaskArrays
        fieldName={`${baseFieldRevised}.originalSpecialities`}
        maskFieldName={`${baseFieldRevised}.originalSpecialityNames`}
        maskListLabel={"l.selected.mask.originalSpeciality"}
        listLabel={"l.selected.originalSpeciality"}
        autocompleteFn={getOriginalSpecialitiesAutocomplete}
        autocompleteLabel={"l.reportFilter.originalSpeciality"}
        reloadObject={ReloadWatcherObject.Report.clear()}
      />
    </>
  );
};
export default SpecialityFilters;
