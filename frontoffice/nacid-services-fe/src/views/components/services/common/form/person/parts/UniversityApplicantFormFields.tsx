import {
  GridContainer,
  GridItem,
  NomenclatureAutocompleteFormField,
  isArrayNotEmpty,
} from "@duosoftbg/nacid-components";
import useAppSelector from "../../../../../../../hooks/redux/base/useAppSelector";
import { useFormContext, useWatch } from "react-hook-form";
import { useEffect } from "react";
import UniversitySelectedInfo from "./UniversitySelectedInfo";
import { nationalUniversitiesDataThunk } from "../../../../../../../store/redux/slice/AppData/nationalUniversities";

const UniversityApplicantFormFields = () => {
  const { setValue } = useFormContext();
  const universityId = useWatch({ name: "applicant.university.universityIdentifier" });
  const universityName = useWatch({ name: "applicant.university.universityName" });

  const nationalUniversitiesThunk = useAppSelector((state) => {
    return state.AppData.NationalUniversities;
  });

  const selectSelectedOption = (fieldValue) => {
    if (fieldValue && isArrayNotEmpty(nationalUniversitiesThunk.data)) {
      return nationalUniversitiesThunk.data.find((element) => element.id === fieldValue);
    }
    return null;
  };

  useEffect(() => {
    if (universityId !== "" && isArrayNotEmpty(nationalUniversitiesThunk.data) && universityName === "") {
      const uni = nationalUniversitiesThunk.data.find((element) => element.id === universityId);
      setValue("applicant.university.universityName", uni.name);
      setValue("applicant.university.universitySettlement", uni.settlement);
    }
  }, [universityId, nationalUniversitiesThunk.data, setValue, universityName]);

  return (
    <>
      <GridContainer>
        <GridItem sm={12} md={12}>
          <NomenclatureAutocompleteFormField
            onlyActive
            required={true}
            fieldName={"applicant.university.universityIdentifier"}
            labelCode={"l.university"}
            thunkFn={nationalUniversitiesDataThunk}
            thunkState={nationalUniversitiesThunk}
            initialValue={universityId}
            previousOptionValueFn={selectSelectedOption}
          />
        </GridItem>
      </GridContainer>
      {selectSelectedOption(universityId) && <UniversitySelectedInfo university={selectSelectedOption(universityId)} />}
    </>
  );
};
export default UniversityApplicantFormFields;
