import { ApplicationSubtype, ApplicationType, FormSection } from "@duosoftbg/nacid-components";
import ServiceTypeFormFields from "../../../../common/form/education/parts/ServiceTypeFormFields";

const RegprofApplicationDetailsFormSection = () => {
  return (
    <FormSection label={"t.regprof.application.details"}>
      <ServiceTypeFormFields
        applicationType={ApplicationType.REGULATED_PROFESSIONS}
        applicationSubtype={ApplicationSubtype.REGULATED_PROFESSIONS}
      />
    </FormSection>
  );
};
export default RegprofApplicationDetailsFormSection;
