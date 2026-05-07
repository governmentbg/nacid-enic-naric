import ApplicantHasRepresentativeFormFields from "./parts/ApplicantHasRepresentativeFormFields";
import RepresentativeFormFields from "./parts/RepresentativeFormFields";
import { FormSection, GridContainer } from "@duosoftbg/nacid-components";
import RepresentativeCompanyFormFields from "./parts/RepresentativeCompanyFormFields";
import RepresentativeCapacityFormFields from "./parts/RepresentativeCapacityFormFields";

const RepresentativeFormSection = ({
  hasRepresentativeCompany = false,
  capacityRequired = false,
  naturalPersonBirthPlaceCitizenshipRequired,
}) => {
  return (
    <FormSection label={"t.representative.personal.details"}>
      <ApplicantHasRepresentativeFormFields />
      <RepresentativeFormFields
        naturalPersonBirthPlaceCitizenshipRequired={naturalPersonBirthPlaceCitizenshipRequired}
      />
      <GridContainer spacing={4} mt={0}>
        {hasRepresentativeCompany && <RepresentativeCompanyFormFields />}
        <RepresentativeCapacityFormFields required={capacityRequired} />
      </GridContainer>
    </FormSection>
  );
};
export default RepresentativeFormSection;
