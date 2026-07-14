import { GridContainer, GridItem, isNotEmpty } from "@duosoftbg/nacid-components";
import {
  getUniversityByBgNameWithAdditionalParamsAutocomplete,
  getUniversityByOrgNameWithAdditionalParamsAutocomplete,
} from "../../../../../../../../../../axios/api/services";
import {
  CountrySelectField,
  FilterPairFormFieldWithMaskArrays,
  ReloadWatcherObject,
} from "@duosoftbg/nacid-backoffice-components";
import { useFormContext } from "react-hook-form";

const UniversityFiltersFormFields = ({ index, baseField }) => {
  const { setValue } = useFormContext();
  const baseFieldRevised = `${baseField}.${index}`;

  return (
    <>
      <GridContainer mt={index === 0 ? 4 : 0}>
        <GridItem sm={12} md={12} pt={0}>
          <CountrySelectField field={`${baseFieldRevised}.country`} />
        </GridItem>
      </GridContainer>
      <div style={{ marginTop: "-16px" }}>
        <FilterPairFormFieldWithMaskArrays
          fieldName={`${baseFieldRevised}.universities`}
          maskFieldName={`${baseFieldRevised}.universityNames`}
          maskExtraFieldName={`${baseFieldRevised}.orgUniversityNames`}
          watchField={`${baseFieldRevised}.country.id`}
          watchFieldParamName={"countryCode"}
          maskExtraListLabel={"l.selected.mask.orgUniversityNames"}
          maskListLabel={"l.selected.mask.universities"}
          listLabel={"l.selected.universities"}
          autocompleteFn={getUniversityByBgNameWithAdditionalParamsAutocomplete}
          autocompleteExtraFn={getUniversityByOrgNameWithAdditionalParamsAutocomplete}
          clearOnWatchChange={false}
          onAdd={() => {
            setValue(`${baseFieldRevised}.country.id`, "");
          }}
          autocompleteLabel={"l.university"}
          autocompleteExtraLabel={"l.university.orgName"}
          setExtraOptionText={(option) =>
            typeof option === "string" ? option : isNotEmpty(option.orgName) ? option.orgName : ""
          }
          setExtraInputOnSelect={(option) =>
            typeof option === "string" ? option : isNotEmpty(option.orgName) ? option.orgName : ""
          }
          reloadObject={ReloadWatcherObject.Report.clear()}
        />
      </div>
    </>
  );
};
export default UniversityFiltersFormFields;
