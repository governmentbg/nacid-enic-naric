import { useWatch } from "react-hook-form";
import SpecialityFormSubsection from "../../../../common/form/education/SpecialityFormSubsection";

const UniChecksSpecialityFormSubsection = () => {
  const recognitionCategoryId = useWatch({ name: "recognitionCategory.id" });
  const isDoctor = recognitionCategoryId === "DOC" || recognitionCategoryId === "DSC";

  return (
    <SpecialityFormSubsection
      originalNameLabelCode={isDoctor ? "l.rudiSpecialityOriginalName.uniChecks" : undefined}
      nameLabelCode={isDoctor ? "l.rudiSpecialityName.uniChecks" : undefined}
    />
  );
};
export default UniChecksSpecialityFormSubsection;
