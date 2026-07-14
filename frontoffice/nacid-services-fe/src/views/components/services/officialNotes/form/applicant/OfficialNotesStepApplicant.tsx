import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import { createLibApplicantDetailsValidationSchema } from "../../../../../../yup/common/applicant/applicantDetailsValidationSchema";
import {
  completeApplicantStep,
  setStepApplicantEdited,
  setRequestIdentifier,
} from "../../../../../../store/redux/slice/Forms/officialNotesForm";
import LibApplicantDetailsForm from "../../../common/form/applicant/LibApplicantDetailsForm";
import { ApplicationSubtype } from "@duosoftbg/nacid-components";
import useFormReset from "../../../../../../hooks/useFormReset";
import { baseEndpointPaths } from "../../../../../../services/serviceCalls";
import { initialLibApplicantDetails } from "../../../../../../init/common/applicantDetailsInitialValues";
import useStepApplicant from "../../../../../../hooks/useStepApplicant";

const OfficialNotesStepApplicant = () => {
  const officialNotesForm = useAppSelector((state) => {
    return state.Forms.OfficialNotesForm;
  });

  const { methods, onSubmit } = useStepApplicant(
    officialNotesForm,
    baseEndpointPaths.officialNotes,
    createLibApplicantDetailsValidationSchema,
    setStepApplicantEdited,
    setRequestIdentifier,
    completeApplicantStep,
    initialLibApplicantDetails,
    true,
    false,
    false
  );

  useFormReset(ApplicationSubtype.OFFICIAL_NOTE, officialNotesForm.applicantDetails, methods.reset);

  return <LibApplicantDetailsForm methods={methods} onSubmit={onSubmit} />;
};
export default OfficialNotesStepApplicant;
