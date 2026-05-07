import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import { completeDocumentStep, setStepDocumentsEdited } from "../../../../../../store/redux/slice/Forms/signalForm";
import DocumentsForm from "../../../common/form/document/DocumentsForm";
import { ApplicationSubtype } from "@duosoftbg/nacid-components";
import useStepDocuments from "../../../../../../hooks/useStepDocuments";
import { baseEndpointPaths } from "../../../../../../services/serviceCalls";
import useFormReset from "../../../../../../hooks/useFormReset";

const SignalStepDocuments = () => {
  const signalForm = useAppSelector((state) => {
    return state.Forms.SignalForm;
  });

  const { methods, onSubmit } = useStepDocuments(
    signalForm,
    baseEndpointPaths.signal,
    completeDocumentStep,
    setStepDocumentsEdited
  );

  useFormReset(ApplicationSubtype.SIGNAL, signalForm.documentDetails, methods.reset);

  return (
    <DocumentsForm
      methods={methods}
      onSubmit={onSubmit}
      setDocumentsFormEdited={setStepDocumentsEdited}
      hasAttachmentForm={false}
      hasAttachmentType={true}
      applicationId={signalForm.id}
      informingMessageCode={"m.general.files.info"}
    />
  );
};
export default SignalStepDocuments;
