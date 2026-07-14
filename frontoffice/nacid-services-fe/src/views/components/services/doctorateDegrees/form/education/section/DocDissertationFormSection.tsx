import { FormSection } from "@duosoftbg/nacid-components";
import DocDissertationFormFields from "../parts/DocDissertationFormFields";
import { useWatch } from "react-hook-form";

const DocDissertationFormSection = () => {
  const graduationWay = useWatch({ name: "graduationWay" });

  //TODO create constant for DIS
  const showDissertationFields = () => {
    if (graduationWay && graduationWay.filter((gr) => gr.id === "DIS").length > 0) {
      return true;
    }
    return false;
  };

  if (showDissertationFields()) {
    return (
      <FormSection label={"t.dissertation.details"}>
        <DocDissertationFormFields />
      </FormSection>
    );
  } else {
    return null;
  }
};
export default DocDissertationFormSection;
