import SearchDetailsFormFields from "../parts/SearchDetailsFormFields";
import SearchChoiceFormFields from "../parts/SearchChoiceFormFields";
import SearchKindsFormFields from "../parts/SearchKindsFormFields";
import { FormSection } from "@duosoftbg/nacid-components";

const BibliographicReferenceDetailsFormSection = () => {
  return (
    <FormSection label={"t.biblioReference.bibliographicReferenceDetails"}>
      <SearchChoiceFormFields />
      <SearchKindsFormFields />
      <SearchDetailsFormFields />
    </FormSection>
  );
};
export default BibliographicReferenceDetailsFormSection;
