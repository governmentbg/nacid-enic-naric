import ServicesNaturalPersonFormFields from "../../../../common/form/person/parts/ServicesNaturalPersonFormFields";
import { FormSection } from "@duosoftbg/nacid-components";
import DiplomaHolderEanFormFields from "../parts/DiplomaHolderEanFormFields";

const DiplomaHolderFormSection = () => {
  return (
    <FormSection label={"t.uniChecks.diplomaHolder"}>
      <ServicesNaturalPersonFormFields
        field={"diplomaHolder"}
        showEmail={false}
        naturalPersonBirthPlaceCitizenshipRequired={true}
      />
      <DiplomaHolderEanFormFields />
    </FormSection>
  );
};
export default DiplomaHolderFormSection;
