import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import { createLibApplicantDetailsValidationSchema } from "../../../../../../yup/common/applicant/applicantDetailsValidationSchema";
import {
  completeApplicantStep,
  setStepApplicantEdited,
  setRequestIdentifier,
} from "../../../../../../store/redux/slice/Forms/suggestionForm";
import LibApplicantDetailsForm from "../../../common/form/applicant/LibApplicantDetailsForm";
import { ApplicationSubtype } from "@duosoftbg/nacid-components";
import useStepApplicant from "../../../../../../hooks/useStepApplicant";
import { baseEndpointPaths } from "../../../../../../services/serviceCalls";
import { initialLibApplicantDetails } from "../../../../../../init/common/applicantDetailsInitialValues";
import useFormReset from "../../../../../../hooks/useFormReset";

const SuggestionStepApplicant = () => {
  const suggestionForm = useAppSelector((state) => {
    return state.Forms.SuggestionForm;
  });

  const { methods, onSubmit } = useStepApplicant(
    suggestionForm,
    baseEndpointPaths.suggestion,
    createLibApplicantDetailsValidationSchema,
    setStepApplicantEdited,
    setRequestIdentifier,
    completeApplicantStep,
    initialLibApplicantDetails,
    true,
    false,
    false
  );

  useFormReset(ApplicationSubtype.SUGGESTION, suggestionForm.applicantDetails, methods.reset);

  return <LibApplicantDetailsForm methods={methods} onSubmit={onSubmit} />;
};
export default SuggestionStepApplicant;
