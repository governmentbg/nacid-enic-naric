import SuggestionDetailsFormFields from "../parts/SuggestionDetailsFormFields";
import { FormSection } from "@duosoftbg/nacid-components";

const SuggestionDetailsSection = () => {
  return (
    <FormSection label={"t.suggestion.suggestionDetails"}>
      <SuggestionDetailsFormFields />
    </FormSection>
  );
};
export default SuggestionDetailsSection;
