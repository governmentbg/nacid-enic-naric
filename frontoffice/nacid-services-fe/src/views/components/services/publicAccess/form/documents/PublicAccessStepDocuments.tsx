import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import { completeDocumentStep, setStepDocumentsEdited } from "../../../../../../store/redux/slice/Forms/suggestionForm";
import DocumentsForm from "../../../common/form/document/DocumentsForm";
import { ApplicationSubtype } from "@duosoftbg/nacid-components";
import useStepDocuments from "../../../../../../hooks/useStepDocuments";
import { baseEndpointPaths } from "../../../../../../services/serviceCalls";
import useFormReset from "../../../../../../hooks/useFormReset";

const PublicAccessStepDocuments = () => {
  const publicAccessForm = useAppSelector((state) => {
    return state.Forms.PublicAccessForm;
  });

  const { methods, onSubmit } = useStepDocuments(
    publicAccessForm,
    baseEndpointPaths.publicAccess,
    completeDocumentStep,
    setStepDocumentsEdited
  );

  useFormReset(ApplicationSubtype.PUBLIC_ACCESS, publicAccessForm.documentDetails, methods.reset);

  return (
    <DocumentsForm
      methods={methods}
      onSubmit={onSubmit}
      setDocumentsFormEdited={setStepDocumentsEdited}
      hasAttachmentForm={false}
      hasAttachmentType={true}
      applicationId={publicAccessForm.id}
      informingMessageCode={"m.general.files.info"}
    />
  );
};
export default PublicAccessStepDocuments;
