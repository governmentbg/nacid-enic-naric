import { GridItem, InputFormField } from "@duosoftbg/nacid-components";
import { useWatch } from "react-hook-form";

const RepresentativeCompanyFormFields = () => {
  const applicantHasRepresentative = useWatch({ name: "applicantHasRepresentative" });

  if (!applicantHasRepresentative) {
    return null;
  }
  return (
    <GridItem>
      <InputFormField
        isDisabled={true}
        fieldName={"representativeCompanyIdentifier"}
        labelCode={"l.representativeCompanyIdentifier"}
      />
    </GridItem>
  );
};
export default RepresentativeCompanyFormFields;
