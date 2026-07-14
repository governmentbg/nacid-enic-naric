import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import {
  completeApplicantStep,
  setRequestIdentifier,
  setStepApplicantEdited,
} from "../../../../../../store/redux/slice/Forms/docDegreesForm";
import RudiApplicantDetailsForm from "../../../common/form/applicant/RudiApplicantDetailsForm";
import { createRudiApplicantDetailsValidationSchema } from "../../../../../../yup/common/applicant/applicantDetailsValidationSchema";
import { baseEndpointPaths } from "../../../../../../services/serviceCalls";
import { initialRudiApplicantDetails } from "../../../../../../init/common/applicantDetailsInitialValues";
import useStepApplicant from "../../../../../../hooks/useStepApplicant";
import { ApplicationSubtype } from "@duosoftbg/nacid-components";
import useFormReset from "../../../../../../hooks/useFormReset";

const DocDegreesStepApplicant = () => {
  const docDegreesForm = useAppSelector((state) => {
    return state.Forms.DocDegreesForm;
  });

  const { methods, onSubmit } = useStepApplicant(
    docDegreesForm,
    baseEndpointPaths.docDegrees,
    createRudiApplicantDetailsValidationSchema,
    setStepApplicantEdited,
    setRequestIdentifier,
    completeApplicantStep,
    initialRudiApplicantDetails,
    false,
    false,
    true
  );

  useFormReset(ApplicationSubtype.DOC_DEGREES, docDegreesForm.applicantDetails, methods.reset);

  return <RudiApplicantDetailsForm methods={methods} onSubmit={onSubmit} hasRepresentativeCompany={true} />;
};

export default DocDegreesStepApplicant;
