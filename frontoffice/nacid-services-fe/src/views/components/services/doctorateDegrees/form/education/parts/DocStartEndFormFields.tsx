import { useWatch } from "react-hook-form";
import StartEndFormFields from "../../../../common/form/education/parts/StartEndFormFields";

const DocStartEndFormFields = () => {
  const recognitionCategory = useWatch({ name: "recognitionCategory.id" });

  const required = "DOC" === recognitionCategory;

  return <StartEndFormFields required={required} />;
};
export default DocStartEndFormFields;
