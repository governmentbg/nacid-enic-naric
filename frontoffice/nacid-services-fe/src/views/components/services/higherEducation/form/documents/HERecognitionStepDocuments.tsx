import { ApplicationSubtype } from "@duosoftbg/nacid-components";
import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import React from "react";
import {
  completeDocumentStep,
  setStepDocumentsEdited,
} from "../../../../../../store/redux/slice/Forms/heRecognitionForm";
import DocumentsForm from "../../../common/form/document/DocumentsForm";
import { baseEndpointPaths } from "../../../../../../services/serviceCalls";
import useFormReset from "../../../../../../hooks/useFormReset";
import useStepDocuments from "../../../../../../hooks/useStepDocuments";
import { fileGroups } from "../../../../../../config/fileGroupConfig";

const HERecognitionStepDocuments = () => {
  const heRecognitionForm = useAppSelector((state) => {
    return state.Forms.HERecognitionForm;
  });

  const { methods, onSubmit } = useStepDocuments(
    heRecognitionForm,
    baseEndpointPaths.heRecognition,
    completeDocumentStep,
    setStepDocumentsEdited
  );

  useFormReset(ApplicationSubtype.HE_RECOGNITION, heRecognitionForm.documentDetails, methods.reset);

  return (
    <DocumentsForm
      methods={methods}
      onSubmit={onSubmit}
      setDocumentsFormEdited={setStepDocumentsEdited}
      hasAttachmentForm={true}
      hasAttachmentType={true}
      applicationId={heRecognitionForm.id}
      fileGroup={fileGroups.pdf}
      informingMessageCode={"m.rudi.files.info"}
    />
  );
};
export default HERecognitionStepDocuments;
