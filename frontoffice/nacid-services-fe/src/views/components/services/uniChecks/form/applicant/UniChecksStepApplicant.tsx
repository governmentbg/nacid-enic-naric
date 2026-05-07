import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import {
  completeApplicantStep,
  setRequestIdentifier,
  setStepApplicantEdited,
} from "../../../../../../store/redux/slice/Forms/uniChecksForm";
import { ApplicationSubtype } from "@duosoftbg/nacid-components";
import { baseEndpointPaths } from "../../../../../../services/serviceCalls";
import { initialUniChecksApplicantDetails } from "../../../../../../init/common/applicantDetailsInitialValues";
import { createUniChecksApplicantDetailsValidationSchema } from "../../../../../../yup/uniChecks/uniChecksApplicantDetailsValidationSchema";
import useFormReset from "../../../../../../hooks/useFormReset";
import useStepApplicant from "../../../../../../hooks/useStepApplicant";
import UniChecksApplicantDetailsForm from "./UniChecksApplicantDetailsForm";

const UniChecksStepApplicant = () => {
  const uniChecksForm = useAppSelector((state) => {
    return state.Forms.UniChecksForm;
  });

  const { methods, onSubmit } = useStepApplicant(
    uniChecksForm,
    baseEndpointPaths.uniChecks,
    createUniChecksApplicantDetailsValidationSchema,
    setStepApplicantEdited,
    setRequestIdentifier,
    completeApplicantStep,
    initialUniChecksApplicantDetails,
    true,
    true,
    true
  );

  useFormReset(ApplicationSubtype.UNI_CHECKS, uniChecksForm.applicantDetails, methods.reset);

  return <UniChecksApplicantDetailsForm methods={methods} onSubmit={onSubmit} />;
};
export default UniChecksStepApplicant;
