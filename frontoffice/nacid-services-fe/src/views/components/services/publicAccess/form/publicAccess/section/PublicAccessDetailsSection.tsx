import { FormSection } from "@duosoftbg/nacid-components";
import PublicAccessDetailsFormFields from "../parts/PublicAccessDetailsFormFields";

const PublicAccessDetailsSection = () => {
  return (
    <FormSection label={"t.publicAccess.publicAccessDetails"}>
      <PublicAccessDetailsFormFields />
    </FormSection>
  );
};
export default PublicAccessDetailsSection;
