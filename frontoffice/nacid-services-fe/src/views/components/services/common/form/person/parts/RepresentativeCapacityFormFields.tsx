import { useWatch } from "react-hook-form";
import { GridItem, InputFormField } from "@duosoftbg/nacid-components";

const RepresentativeCapacityFormFields = ({ required }) => {
  const applicantHasRepresentative = useWatch({ name: "applicantHasRepresentative" });

  if (!applicantHasRepresentative) {
    return null;
  }
  return (
    <GridItem>
      <InputFormField required={required} fieldName={"representativeCapacity"} labelCode={"l.representativeCapacity"} />
    </GridItem>
  );
};
export default RepresentativeCapacityFormFields;
