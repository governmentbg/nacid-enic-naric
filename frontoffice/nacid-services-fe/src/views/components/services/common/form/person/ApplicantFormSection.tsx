import React from "react";
import ApplicantTypeFormFields from "./parts/ApplicantTypeFormFields";
import ApplicantFormFields from "./parts/ApplicantFormFields";
import { FormSection } from "@duosoftbg/nacid-components";

const ApplicantFormSection = ({
  hasType,
  hasApplicantTitleFields = false,
  types = [],
  naturalPersonBirthPlaceCitizenshipRequired,
}) => {
  return (
    <FormSection label={"t.applicant.personal.details"}>
      {hasType && <ApplicantTypeFormFields types={types} />}
      <ApplicantFormFields
        hasApplicantTitleFields={hasApplicantTitleFields}
        naturalPersonBirthPlaceCitizenshipRequired={naturalPersonBirthPlaceCitizenshipRequired}
      />
    </FormSection>
  );
};
export default ApplicantFormSection;
