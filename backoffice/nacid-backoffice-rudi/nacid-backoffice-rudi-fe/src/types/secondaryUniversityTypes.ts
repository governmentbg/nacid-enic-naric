import { Country } from "@duosoftbg/nacid-components";
import { FacultyDetails, UniversityDetails } from "@duosoftbg/nacid-backoffice-components";

export interface SecondaryUniversityDetails {
  ordNum: number;
  country: Country;
  university: UniversityDetails;
  faculty: FacultyDetails;
}
