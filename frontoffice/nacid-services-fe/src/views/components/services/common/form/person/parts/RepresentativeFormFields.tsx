import { useFormContext, useWatch } from "react-hook-form";
import ServicesNaturalPersonFormFields from "./ServicesNaturalPersonFormFields";

const RepresentativeFormFields = ({ naturalPersonBirthPlaceCitizenshipRequired }) => {
  const { getValues } = useFormContext();

  useWatch({ name: "applicantHasRepresentative" });

  if (getValues().applicantHasRepresentative) {
    return (
      <ServicesNaturalPersonFormFields
        field={"representative"}
        naturalPersonBirthPlaceCitizenshipRequired={naturalPersonBirthPlaceCitizenshipRequired}
      />
    );
  }
  return null;
};
export default RepresentativeFormFields;
