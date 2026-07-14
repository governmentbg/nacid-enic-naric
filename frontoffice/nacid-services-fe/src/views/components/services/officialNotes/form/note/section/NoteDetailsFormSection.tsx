import NoteDetailsFormFields from "../parts/NoteDetailsFormFields";
import { FormSection } from "@duosoftbg/nacid-components";

const NoteDetailsFormSection = () => {
  return (
    <FormSection label={"t.officialNotes.officialNoteDetails"}>
      <NoteDetailsFormFields />
    </FormSection>
  );
};
export default NoteDetailsFormSection;
