import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import { createLibApplicantDetailsValidationSchema } from "../../../../../../yup/common/applicant/applicantDetailsValidationSchema";
import {
  completeApplicantStep,
  setStepApplicantEdited,
  setRequestIdentifier,
} from "../../../../../../store/redux/slice/Forms/signalForm";
import LibApplicantDetailsForm from "../../../common/form/applicant/LibApplicantDetailsForm";
import { ApplicationSubtype } from "@duosoftbg/nacid-components";
import useStepApplicant from "../../../../../../hooks/useStepApplicant";
import { baseEndpointPaths } from "../../../../../../services/serviceCalls";
import { initialLibApplicantDetails } from "../../../../../../init/common/applicantDetailsInitialValues";
import useFormReset from "../../../../../../hooks/useFormReset";

const SignalStepApplicant = () => {
  const signalForm = useAppSelector((state) => {
    return state.Forms.SignalForm;
  });

  const { methods, onSubmit } = useStepApplicant(
    signalForm,
    baseEndpointPaths.signal,
    createLibApplicantDetailsValidationSchema,
    setStepApplicantEdited,
    setRequestIdentifier,
    completeApplicantStep,
    initialLibApplicantDetails,
    true,
    false,
    false
  );

  useFormReset(ApplicationSubtype.SIGNAL, signalForm.applicantDetails, methods.reset);

  return <LibApplicantDetailsForm methods={methods} onSubmit={onSubmit} />;
};
export default SignalStepApplicant;
