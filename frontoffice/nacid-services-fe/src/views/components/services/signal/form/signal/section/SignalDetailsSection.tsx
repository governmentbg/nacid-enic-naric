import SignalDetailsFormFields from "../parts/SignalDetailsFormFields";
import { FormSection } from "@duosoftbg/nacid-components";

const SignalDetailsSection = () => {
  return (
    <FormSection label={"t.signal.signalDetails"}>
      <SignalDetailsFormFields />
    </FormSection>
  );
};
export default SignalDetailsSection;
