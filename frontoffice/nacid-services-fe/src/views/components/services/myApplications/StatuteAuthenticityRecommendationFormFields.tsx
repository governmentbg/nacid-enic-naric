import { ApplicationSubtype, CheckboxFormField } from "@duosoftbg/nacid-components";
import { useWatch } from "react-hook-form";

const StatuteAuthenticityRecommendationFormFields = () => {
  const applicationSubtype = useWatch({ name: "applicationSubtype" });

  if (applicationSubtype !== ApplicationSubtype.UNI_CHECKS.valueOf()) {
    return null;
  }
  return (
    <>
      <CheckboxFormField fieldName={"statute"} labelCode={"l.filter.statute"} />
      <CheckboxFormField fieldName={"authenticity"} labelCode={"l.filter.authenticity"} />
      <CheckboxFormField fieldName={"recommendation"} labelCode={"l.filter.recommendation"} />
    </>
  );
};
export default StatuteAuthenticityRecommendationFormFields;
