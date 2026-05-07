import { FormSection } from "@duosoftbg/nacid-components";
import GraduationWayFormFields from "./parts/GraduationWayFormFields";

const GraduationWayFormSection = ({
  applicationType,
  applicationSubtype,
  labelCode = "l.graduationWay",
  required = false,
}) => {
  return (
    <FormSection label={labelCode}>
      <GraduationWayFormFields
        required={required}
        applicationType={applicationType}
        applicationSubtype={applicationSubtype}
        labelCode={labelCode}
      />
    </FormSection>
  );
};
export default GraduationWayFormSection;
