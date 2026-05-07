import EducationPlaceArrayFormFields from "./parts/EducationPlaceArrayFormFields";
import { FormSection } from "@duosoftbg/nacid-components";

const EducationPlaceFormSection = () => {
  return (
    <FormSection label={"t.education.educationPlace"}>
      <EducationPlaceArrayFormFields />
    </FormSection>
  );
};
export default EducationPlaceFormSection;
