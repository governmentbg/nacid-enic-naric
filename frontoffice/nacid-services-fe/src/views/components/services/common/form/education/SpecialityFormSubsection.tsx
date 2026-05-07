import { useFieldArray, useFormContext } from "react-hook-form";
import { initialSpeciality } from "../../../../../../init/common/educationInitialValues";
import RudiSpecialityFormFields from "./parts/RudiSpecialityFormFields";

const SpecialityFormSubsection = ({ originalNameLabelCode = undefined, nameLabelCode = undefined }) => {
  const { control, getValues, setValue } = useFormContext();

  const { append, remove } = useFieldArray({
    control,
    name: "specialities",
  });

  const addSpeciality = () => {
    if (
      getValues().specialitySingle.name.trim() !== "" &&
      getValues().specialities.filter((spec) => spec.name === getValues().specialitySingle.name.trim()).length === 0
    ) {
      append(getValues().specialitySingle);
      setValue("specialitySingle", { ...initialSpeciality }, { shouldValidate: true });
    } else {
      setValue("specialitySingle", { ...initialSpeciality }, { shouldValidate: true });
    }
  };

  const removeSpeciality = (ind) => {
    remove(ind);
  };

  return (
    <RudiSpecialityFormFields
      addSpeciality={addSpeciality}
      removeSpeciality={removeSpeciality}
      specialitiesList={getValues().specialities}
      originalNameLabelCode={originalNameLabelCode}
      nameLabelCode={nameLabelCode}
    />
  );
};

export default SpecialityFormSubsection;
