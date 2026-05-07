import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import { completeDocumentStep, setStepDocumentsEdited } from "../../../../../../store/redux/slice/Forms/suggestionForm";
import DocumentsForm from "../../../common/form/document/DocumentsForm";
import { ApplicationSubtype } from "@duosoftbg/nacid-components";
import useStepDocuments from "../../../../../../hooks/useStepDocuments";
import { baseEndpointPaths } from "../../../../../../services/serviceCalls";
import useFormReset from "../../../../../../hooks/useFormReset";

const SuggestionStepDocuments = () => {
  const suggestionForm = useAppSelector((state) => {
    return state.Forms.SuggestionForm;
  });

  const { methods, onSubmit } = useStepDocuments(
    suggestionForm,
    baseEndpointPaths.suggestion,
    completeDocumentStep,
    setStepDocumentsEdited
  );

  useFormReset(ApplicationSubtype.SUGGESTION, suggestionForm.documentDetails, methods.reset);

  return (
    <DocumentsForm
      methods={methods}
      onSubmit={onSubmit}
      setDocumentsFormEdited={setStepDocumentsEdited}
      hasAttachmentForm={false}
      hasAttachmentType={true}
      applicationId={suggestionForm.id}
      informingMessageCode={"m.general.files.info"}
    />
  );
};
export default SuggestionStepDocuments;
