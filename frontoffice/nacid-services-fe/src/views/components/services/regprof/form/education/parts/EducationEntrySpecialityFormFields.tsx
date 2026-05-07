import { useFieldArray, useFormContext, useWatch } from "react-hook-form";
import { initialSpeciality } from "../../../../../../../init/common/educationInitialValues";
import { useEffect } from "react";
import RegprofSpecialityFormFields from "./RegprofSpecialityFormFields";

const EducationEntrySpecialityFormFields = ({ field, specialityAutocompleteFn, hasSpecialityId }) => {
  const { control, getValues, setValue } = useFormContext();
  const qualificationId = useWatch({ name: `education.${field}.professionalQualificationId` });

  useEffect(() => {
    setValue(`education.${field}.specialities`, []);
  }, [qualificationId, setValue, field]);

  const { append, remove } = useFieldArray({
    control,
    name: `education.${field}.specialities`,
  });

  const addSpeciality = () => {
    if (
      getValues().education[field].specialitySingle.name.trim() !== "" &&
      getValues().education[field].specialities.filter(
        (spec) => spec.name === getValues().education[field].specialitySingle.name.trim()
      ).length === 0
    ) {
      append(getValues().education[field].specialitySingle);
      setValue(`education.${field}.specialitySingle`, { ...initialSpeciality }, { shouldValidate: true });
    } else {
      setValue(`education.${field}.specialitySingle`, { ...initialSpeciality }, { shouldValidate: true });
    }
  };

  const removeSpeciality = (ind) => {
    remove(ind);
  };

  return (
    <RegprofSpecialityFormFields
      autocompleteFn={specialityAutocompleteFn}
      addSpeciality={addSpeciality}
      removeSpeciality={removeSpeciality}
      singleSpecialityFieldName={`education.${field}.specialitySingle.name`}
      singleSpecialityIdFieldName={hasSpecialityId ? `education.${field}.specialitySingle.id` : null}
      specialitiesList={getValues().education[field].specialities}
      additionalParams={
        qualificationId && qualificationId !== null && qualificationId !== ""
          ? { qualificationId: qualificationId }
          : null
      }
    />
  );
};
export default EducationEntrySpecialityFormFields;
