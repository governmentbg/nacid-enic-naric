import { ApplicationSubtype } from "@duosoftbg/nacid-components";
import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import React from "react";
import { completeDocumentStep, setStepDocumentsEdited } from "../../../../../../store/redux/slice/Forms/docDegreesForm";
import DocumentsForm from "../../../common/form/document/DocumentsForm";
import { baseEndpointPaths } from "../../../../../../services/serviceCalls";
import useFormReset from "../../../../../../hooks/useFormReset";
import useStepDocuments from "../../../../../../hooks/useStepDocuments";
import { fileGroups } from "../../../../../../config/fileGroupConfig";

const DocDegreesStepDocuments = () => {
  const docDegreesForm = useAppSelector((state) => {
    return state.Forms.DocDegreesForm;
  });

  const { methods, onSubmit } = useStepDocuments(
    docDegreesForm,
    baseEndpointPaths.docDegrees,
    completeDocumentStep,
    setStepDocumentsEdited
  );

  useFormReset(ApplicationSubtype.DOC_DEGREES, docDegreesForm.documentDetails, methods.reset);

  return (
    <DocumentsForm
      methods={methods}
      onSubmit={onSubmit}
      setDocumentsFormEdited={setStepDocumentsEdited}
      hasAttachmentForm={true}
      hasAttachmentType={true}
      applicationId={docDegreesForm.id}
      fileGroup={fileGroups.pdf}
      informingMessageCode={"m.rudi.files.info"}
    />
  );
};
export default DocDegreesStepDocuments;
