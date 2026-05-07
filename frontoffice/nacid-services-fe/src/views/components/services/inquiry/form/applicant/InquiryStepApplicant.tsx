import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import { createLibApplicantDetailsValidationSchema } from "../../../../../../yup/common/applicant/applicantDetailsValidationSchema";
import {
  completeApplicantStep,
  setStepApplicantEdited,
  setRequestIdentifier,
} from "../../../../../../store/redux/slice/Forms/inquiryForm";
import LibApplicantDetailsForm from "../../../common/form/applicant/LibApplicantDetailsForm";
import { ApplicationSubtype } from "@duosoftbg/nacid-components";
import useFormReset from "../../../../../../hooks/useFormReset";
import { baseEndpointPaths } from "../../../../../../services/serviceCalls";
import { initialLibApplicantDetails } from "../../../../../../init/common/applicantDetailsInitialValues";
import useStepApplicant from "../../../../../../hooks/useStepApplicant";

const InquiryStepApplicant = () => {
  const inquiryForm = useAppSelector((state) => {
    return state.Forms.InquiryForm;
  });

  const { methods, onSubmit } = useStepApplicant(
    inquiryForm,
    baseEndpointPaths.inquiry,
    createLibApplicantDetailsValidationSchema,
    setStepApplicantEdited,
    setRequestIdentifier,
    completeApplicantStep,
    initialLibApplicantDetails,
    true,
    false,
    false
  );

  useFormReset(ApplicationSubtype.INQUIRY, inquiryForm.applicantDetails, methods.reset);

  return <LibApplicantDetailsForm methods={methods} onSubmit={onSubmit} hasApplicantTitleFields={true} />;
};
export default InquiryStepApplicant;
