import { useWatch } from "react-hook-form";
import { ApplicantType } from "../../../../../../../types/common/personTypes";
import ServicesNaturalPersonFormFields from "./ServicesNaturalPersonFormFields";
import CompanyFormFields from "./CompanyFormFields";
import UniversityApplicantFormFields from "./UniversityApplicantFormFields";
import ApplicantTitleFormFields from "./ApplicantTitleFormFields";

const ApplicantFormFields = ({ hasApplicantTitleFields, naturalPersonBirthPlaceCitizenshipRequired }) => {
  const applicantType = useWatch({ name: "applicant.applicantType" });

  if (applicantType === ApplicantType.NATURAL_PERSON) {
    return (
      <>
        <ServicesNaturalPersonFormFields
          field={"applicant.naturalPerson"}
          naturalPersonBirthPlaceCitizenshipRequired={naturalPersonBirthPlaceCitizenshipRequired}
        />
        {hasApplicantTitleFields && <ApplicantTitleFormFields />}
      </>
    );
  } else if (applicantType === ApplicantType.COMPANY) {
    return <CompanyFormFields />;
  } else if (applicantType === ApplicantType.UNIVERSITY) {
    return <UniversityApplicantFormFields />;
  }

  return null;
};
export default ApplicantFormFields;
