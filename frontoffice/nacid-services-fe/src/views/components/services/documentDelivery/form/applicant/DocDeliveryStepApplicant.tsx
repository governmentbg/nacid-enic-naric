import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import { createLibApplicantDetailsValidationSchema } from "../../../../../../yup/common/applicant/applicantDetailsValidationSchema";
import {
  completeApplicantStep,
  setStepApplicantEdited,
  setRequestIdentifier,
} from "../../../../../../store/redux/slice/Forms/docDeliveryForm";
import LibApplicantDetailsForm from "../../../common/form/applicant/LibApplicantDetailsForm";
import { ApplicationSubtype } from "@duosoftbg/nacid-components";
import useStepApplicant from "../../../../../../hooks/useStepApplicant";
import { baseEndpointPaths } from "../../../../../../services/serviceCalls";
import { initialLibApplicantDetails } from "../../../../../../init/common/applicantDetailsInitialValues";
import useFormReset from "../../../../../../hooks/useFormReset";

const DocDeliveryStepApplicant = () => {
  const docDeliveryForm = useAppSelector((state) => {
    return state.Forms.DocDeliveryForm;
  });

  const { methods, onSubmit } = useStepApplicant(
    docDeliveryForm,
    baseEndpointPaths.documentDelivery,
    createLibApplicantDetailsValidationSchema,
    setStepApplicantEdited,
    setRequestIdentifier,
    completeApplicantStep,
    initialLibApplicantDetails,
    true,
    false,
    false
  );

  useFormReset(ApplicationSubtype.DOCUMENT_SERVICE, docDeliveryForm.applicantDetails, methods.reset);

  return <LibApplicantDetailsForm methods={methods} onSubmit={onSubmit} />;
};
export default DocDeliveryStepApplicant;
