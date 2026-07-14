import {
  completeApplicantStep,
  setRequestIdentifier,
  setStepApplicantEdited,
} from "../../../../../../store/redux/slice/Forms/heRecognitionForm";
import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import { ApplicationSubtype } from "@duosoftbg/nacid-components";
import RudiApplicantDetailsForm from "../../../common/form/applicant/RudiApplicantDetailsForm";
import { createRudiApplicantDetailsValidationSchema } from "../../../../../../yup/common/applicant/applicantDetailsValidationSchema";
import { initialRudiApplicantDetails } from "../../../../../../init/common/applicantDetailsInitialValues";
import { baseEndpointPaths } from "../../../../../../services/serviceCalls";
import useFormReset from "../../../../../../hooks/useFormReset";
import useStepApplicant from "../../../../../../hooks/useStepApplicant";

const HERecognitionStepApplicant = () => {
  const heRecognitionForm = useAppSelector((state) => {
    return state.Forms.HERecognitionForm;
  });

  const { methods, onSubmit } = useStepApplicant(
    heRecognitionForm,
    baseEndpointPaths.heRecognition,
    createRudiApplicantDetailsValidationSchema,
    setStepApplicantEdited,
    setRequestIdentifier,
    completeApplicantStep,
    initialRudiApplicantDetails,
    false,
    false,
    true
  );

  useFormReset(ApplicationSubtype.HE_RECOGNITION, heRecognitionForm.applicantDetails, methods.reset);

  return <RudiApplicantDetailsForm methods={methods} onSubmit={onSubmit} hasRepresentativeCompany={true} />;
};
export default HERecognitionStepApplicant;
