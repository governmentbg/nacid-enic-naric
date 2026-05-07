import { GridContainer, GridItem } from "@duosoftbg/nacid-components";
import { getTrainingInstitutionWithAdditionalParamsAutocomplete } from "../../../../../../../../../axios/api/services";
import {
  CountrySelectField,
  FilterFormFieldWithMaskArrays,
  ReloadWatcherObject,
} from "@duosoftbg/nacid-backoffice-components";

const TrainingInstitutionFiltersFormFields = ({ index, baseField }) => {
  const baseFieldRevised = `${baseField}.${index}`;
  return (
    <>
      <GridContainer mt={0}>
        <GridItem sm={12} md={12} pt={0}>
          <CountrySelectField field={`${baseFieldRevised}.country`} />
        </GridItem>
      </GridContainer>
      <FilterFormFieldWithMaskArrays
        fieldName={`${baseFieldRevised}.trainingInstitutions`}
        maskFieldName={`${baseFieldRevised}.trainingInstitutionNames`}
        watchField={`${baseFieldRevised}.country.id`}
        watchFieldParamName={"countryCode"}
        maskListLabel={"l.selected.mask.trainingInstitutions"}
        listLabel={"l.selected.trainingInstitutions"}
        autocompleteFn={getTrainingInstitutionWithAdditionalParamsAutocomplete}
        autocompleteLabel={"l.reportFilter.trainingInstitution"}
        reloadObject={ReloadWatcherObject.Report.clear()}
      />
    </>
  );
};
export default TrainingInstitutionFiltersFormFields;
