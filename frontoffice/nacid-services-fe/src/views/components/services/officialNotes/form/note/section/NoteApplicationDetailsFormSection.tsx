import { ApplicationSubtype, ApplicationType, FormSection } from "@duosoftbg/nacid-components";
import ServiceTypeFormFields from "../../../../common/form/education/parts/ServiceTypeFormFields";

const NoteApplicationDetailsFormSection = () => {
  return (
    <FormSection label={"t.officialNote.application.details"}>
      <ServiceTypeFormFields
        applicationType={ApplicationType.LIBRARY}
        applicationSubtype={ApplicationSubtype.OFFICIAL_NOTE}
      />
    </FormSection>
  );
};
export default NoteApplicationDetailsFormSection;
