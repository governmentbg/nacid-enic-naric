import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import {
  completeApplicantStep,
  setStepApplicantEdited,
  setRequestIdentifier,
} from "../../../../../../store/redux/slice/Forms/regprofForm";
import { createRegprofApplicantDetailsValidationSchema } from "../../../../../../yup/regprof/regprofApplicantDetailsValidationSchema";
import RegprofApplicantDetailsForm from "./RegprofApplicantDetailsForm";
import { initialRegprofApplicantDetails } from "../../../../../../init/regprofInitialValues";
import { ApplicationSubtype } from "@duosoftbg/nacid-components";
import { baseEndpointPaths } from "../../../../../../services/serviceCalls";
import useFormReset from "../../../../../../hooks/useFormReset";
import useStepApplicant from "../../../../../../hooks/useStepApplicant";

const RegprofStepApplicant = () => {
  const regprofForm = useAppSelector((state) => {
    return state.Forms.RegprofForm;
  });

  const { methods, onSubmit } = useStepApplicant(
    regprofForm,
    baseEndpointPaths.regprof,
    createRegprofApplicantDetailsValidationSchema,
    setStepApplicantEdited,
    setRequestIdentifier,
    completeApplicantStep,
    initialRegprofApplicantDetails,
    false,
    false,
    true
  );

  useFormReset(ApplicationSubtype.REGULATED_PROFESSIONS, regprofForm.applicantDetails, methods.reset);

  return <RegprofApplicantDetailsForm methods={methods} onSubmit={onSubmit} />;
};
export default RegprofStepApplicant;
