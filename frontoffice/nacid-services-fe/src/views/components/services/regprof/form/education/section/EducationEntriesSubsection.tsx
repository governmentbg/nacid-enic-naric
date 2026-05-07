import { useFormContext, useWatch } from "react-hook-form";
import EducationEntryFormFields from "../parts/EducationEntryFormFields";
import { EducationType } from "@duosoftbg/nacid-components";
import {
  getHigherQualificationsAutocomplete,
  getHigherSpecialitiesAutocomplete,
  getSdkQualificationsAutocomplete,
  getSdkSpecialitiesAutocomplete,
  getSecondaryQualificationsAutocomplete,
  getSecondarySpecialitiesAutocomplete,
} from "../../../../../../../services/autocompleteCalls";

const EducationEntriesSubsection = () => {
  const { getValues } = useFormContext();

  useWatch({ name: "education.kind" });
  useWatch({ name: "educationSelected" });

  if (!getValues().educationSelected || getValues().education.kind === null) {
    return null;
  } else if (getValues().education.kind === EducationType.AFTER_DIPLOMA_QUALIFICATION) {
    return (
      <>
        <EducationEntryFormFields
          key={EducationType.HIGHER_EDUCATION}
          field={"educationEntryHigher"}
          title={`l.education.kind.HIGHER_EDUCATION`}
          hasEduLevel={true}
          hasRank={false}
          specialityAutocompleteFn={getHigherSpecialitiesAutocomplete}
          qualificationAutocompleteFn={getHigherQualificationsAutocomplete}
        />
        <EducationEntryFormFields
          key={EducationType.AFTER_DIPLOMA_QUALIFICATION}
          field={"educationEntryADQ"}
          title={`l.education.kind.AFTER_DIPLOMA_QUALIFICATION`}
          hasEduLevel={false}
          hasRank={false}
          specialityAutocompleteFn={getSdkSpecialitiesAutocomplete}
          qualificationAutocompleteFn={getSdkQualificationsAutocomplete}
        />
      </>
    );
  } else if (getValues().education.kind === EducationType.HIGHER_EDUCATION) {
    return (
      <EducationEntryFormFields
        key={getValues().education.kind}
        field={"educationEntryHigher"}
        title={`l.education.kind.${getValues().education.kind}`}
        specialityAutocompleteFn={getHigherSpecialitiesAutocomplete}
        qualificationAutocompleteFn={getHigherQualificationsAutocomplete}
        hasEduLevel={true}
        hasRank={false}
      />
    );
  } else {
    return (
      <EducationEntryFormFields
        key={getValues().education.kind}
        field={"educationEntrySecondary"}
        title={`l.education.kind.${getValues().education.kind}`}
        specialityAutocompleteFn={getSecondarySpecialitiesAutocomplete}
        qualificationAutocompleteFn={getSecondaryQualificationsAutocomplete}
        hasEduLevel={false}
        hasRank={true}
        hasSpecialityId={true}
      />
    );
  }
};
export default EducationEntriesSubsection;
