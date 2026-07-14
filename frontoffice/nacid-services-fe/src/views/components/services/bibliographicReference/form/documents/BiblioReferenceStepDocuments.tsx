import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import {
  completeDocumentStep,
  setStepDocumentsEdited,
} from "../../../../../../store/redux/slice/Forms/biblioReferenceForm";
import DocumentsForm from "../../../common/form/document/DocumentsForm";
import { ApplicationSubtype } from "@duosoftbg/nacid-components";
import useStepDocuments from "../../../../../../hooks/useStepDocuments";
import { baseEndpointPaths } from "../../../../../../services/serviceCalls";
import useFormReset from "../../../../../../hooks/useFormReset";
import { fileGroups } from "../../../../../../config/fileGroupConfig";

const BiblioReferenceStepDocuments = () => {
  const biblioReferenceForm = useAppSelector((state) => {
    return state.Forms.BiblioReferenceForm;
  });

  const { methods, onSubmit } = useStepDocuments(
    biblioReferenceForm,
    baseEndpointPaths.bibliographicReference,
    completeDocumentStep,
    setStepDocumentsEdited
  );

  useFormReset(ApplicationSubtype.BIBLIO_REFERENCE, biblioReferenceForm.documentDetails, methods.reset);

  return (
    <DocumentsForm
      methods={methods}
      onSubmit={onSubmit}
      setDocumentsFormEdited={setStepDocumentsEdited}
      hasAttachmentForm={false}
      hasAttachmentType={false}
      informingMessageCode={"m.biblioReference.files.info"}
      fileGroup={fileGroups.editableDoc}
      applicationId={biblioReferenceForm.id}
    />
  );
};
export default BiblioReferenceStepDocuments;
