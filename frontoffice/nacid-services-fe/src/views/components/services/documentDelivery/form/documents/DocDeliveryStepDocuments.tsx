import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import {
  completeDocumentStep,
  setStepDocumentsEdited,
} from "../../../../../../store/redux/slice/Forms/docDeliveryForm";
import DocumentsForm from "../../../common/form/document/DocumentsForm";
import { ApplicationSubtype } from "@duosoftbg/nacid-components";
import useStepDocuments from "../../../../../../hooks/useStepDocuments";
import { baseEndpointPaths } from "../../../../../../services/serviceCalls";
import useFormReset from "../../../../../../hooks/useFormReset";

const DocDeliveryStepDocuments = () => {
  const docDeliveryForm = useAppSelector((state) => {
    return state.Forms.DocDeliveryForm;
  });

  const { methods, onSubmit } = useStepDocuments(
    docDeliveryForm,
    baseEndpointPaths.documentDelivery,
    completeDocumentStep,
    setStepDocumentsEdited
  );

  useFormReset(ApplicationSubtype.DOCUMENT_SERVICE, docDeliveryForm.documentDetails, methods.reset);

  return (
    <DocumentsForm
      methods={methods}
      onSubmit={onSubmit}
      setDocumentsFormEdited={setStepDocumentsEdited}
      hasAttachmentForm={false}
      hasAttachmentType={false}
      applicationId={docDeliveryForm.id}
      informingMessageCode={"m.general.files.info"}
    />
  );
};
export default DocDeliveryStepDocuments;
