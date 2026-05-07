import { CheckboxFormField, GridContainer, GridItem, isNotEmpty } from "@duosoftbg/nacid-components";
import { useFormContext, useWatch } from "react-hook-form";
import { initialServicesNaturalPerson } from "../../../../../../../init/common/personInitialValues";
import { useEffect } from "react";
import { ApplicantType } from "../../../../../../../types/common/personTypes";

const switchPersons = (formValues, setValue) => {
  if (formValues.applicantHasRepresentative) {
    setValue("representative", { ...formValues.applicant.naturalPerson });
    setValue("applicant.naturalPerson", initialServicesNaturalPerson);
  } else {
    setValue("applicant.naturalPerson", { ...formValues.representative });
    setValue("representative", initialServicesNaturalPerson);
  }
};

const ApplicantHasRepresentativeFormFields = () => {
  const { getValues, setValue } = useFormContext();

  useWatch({ name: "applicantHasRepresentative" });
  const applicantType = useWatch({ name: "applicant.applicantType" });
  const reprUserName = useWatch({ name: "representative.userName" });

  const representativeImported = isNotEmpty(reprUserName);

  useEffect(() => {
    if (applicantType !== ApplicantType.NATURAL_PERSON) {
      setValue("applicantHasRepresentative", true);
      if (!representativeImported) {
        switchPersons(getValues(), setValue);
      }
    }
  }, [applicantType, setValue, getValues, representativeImported]);

  return (
    <GridContainer>
      <GridItem>
        <CheckboxFormField
          isDisabled={applicantType !== ApplicantType.NATURAL_PERSON}
          labelCode={"l.applicant.has.representative"}
          fieldName={"applicantHasRepresentative"}
          onChange={() => switchPersons(getValues(), setValue)}
        />
      </GridItem>
    </GridContainer>
  );
};
export default ApplicantHasRepresentativeFormFields;
