import { SecondaryUniversityDetails } from "../../types/secondaryUniversityTypes";
import { InitialValues } from "@duosoftbg/nacid-backoffice-components";

export const secondaryUniversityInitialValues: SecondaryUniversityDetails = {
  ordNum: 2,
  country: { id: "", name: "" },
  university: InitialValues.forms.university.universityInitialValues,
  faculty: InitialValues.forms.university.facultyInitialValues,
};
