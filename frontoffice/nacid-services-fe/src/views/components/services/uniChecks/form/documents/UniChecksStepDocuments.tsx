import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import { completeDocumentStep, setStepDocumentsEdited } from "../../../../../../store/redux/slice/Forms/uniChecksForm";
import DocumentsForm from "../../../common/form/document/DocumentsForm";
import React from "react";
import { ApplicationSubtype } from "@duosoftbg/nacid-components";
import { baseEndpointPaths } from "../../../../../../services/serviceCalls";
import useFormReset from "../../../../../../hooks/useFormReset";
import useStepDocuments from "../../../../../../hooks/useStepDocuments";
import { fileGroups } from "../../../../../../config/fileGroupConfig";

const UniChecksStepDocuments = () => {
  const uniChecksForm = useAppSelector((state) => {
    return state.Forms.UniChecksForm;
  });

  const { methods, onSubmit } = useStepDocuments(
    uniChecksForm,
    baseEndpointPaths.uniChecks,
    completeDocumentStep,
    setStepDocumentsEdited
  );

  useFormReset(ApplicationSubtype.UNI_CHECKS, uniChecksForm.documentDetails, methods.reset);

  return (
    <DocumentsForm
      methods={methods}
      onSubmit={onSubmit}
      setDocumentsFormEdited={setStepDocumentsEdited}
      hasAttachmentForm={true}
      hasAttachmentType={true}
      applicationId={uniChecksForm.id}
      fileGroup={fileGroups.pdf}
      informingMessageCode={"m.rudi.files.info"}
    />
  );
};
export default UniChecksStepDocuments;
