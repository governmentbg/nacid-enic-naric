import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import { createLibApplicantDetailsValidationSchema } from "../../../../../../yup/common/applicant/applicantDetailsValidationSchema";
import {
  completeApplicantStep,
  setStepApplicantEdited,
  setRequestIdentifier,
} from "../../../../../../store/redux/slice/Forms/biblioReferenceForm";
import LibApplicantDetailsForm from "../../../common/form/applicant/LibApplicantDetailsForm";
import { ApplicationSubtype } from "@duosoftbg/nacid-components";
import useStepApplicant from "../../../../../../hooks/useStepApplicant";
import { baseEndpointPaths } from "../../../../../../services/serviceCalls";
import { initialLibApplicantDetails } from "../../../../../../init/common/applicantDetailsInitialValues";
import useFormReset from "../../../../../../hooks/useFormReset";

const BiblioReferenceStepApplicant = () => {
  const biblioReferenceForm = useAppSelector((state) => {
    return state.Forms.BiblioReferenceForm;
  });

  const { methods, onSubmit } = useStepApplicant(
    biblioReferenceForm,
    baseEndpointPaths.bibliographicReference,
    createLibApplicantDetailsValidationSchema,
    setStepApplicantEdited,
    setRequestIdentifier,
    completeApplicantStep,
    initialLibApplicantDetails,
    true,
    false,
    false
  );

  useFormReset(ApplicationSubtype.BIBLIO_REFERENCE, biblioReferenceForm.applicantDetails, methods.reset);

  return <LibApplicantDetailsForm methods={methods} onSubmit={onSubmit} />;
};
export default BiblioReferenceStepApplicant;
