import InquiryDetailsFormFields from "../parts/InquiryDetailsFormFields";
import { FormSection } from "@duosoftbg/nacid-components";

const InquiryDetailsFormSection = () => {
  return (
    <FormSection label={"t.inquiry.inquiryDetails"}>
      <InquiryDetailsFormFields />
    </FormSection>
  );
};
export default InquiryDetailsFormSection;
