import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import React from "react";
import { completeDocumentStep, setStepDocumentsEdited } from "../../../../../../store/redux/slice/Forms/inquiryForm";
import DocumentsForm from "../../../common/form/document/DocumentsForm";
import { ApplicationSubtype } from "@duosoftbg/nacid-components";
import useFormReset from "../../../../../../hooks/useFormReset";
import { baseEndpointPaths } from "../../../../../../services/serviceCalls";
import useStepDocuments from "../../../../../../hooks/useStepDocuments";
import { fileGroups } from "../../../../../../config/fileGroupConfig";

const InquiryStepDocuments = () => {
  const inquiryForm = useAppSelector((state) => {
    return state.Forms.InquiryForm;
  });

  const { methods, onSubmit } = useStepDocuments(
    inquiryForm,
    baseEndpointPaths.inquiry,
    completeDocumentStep,
    setStepDocumentsEdited
  );

  useFormReset(ApplicationSubtype.INQUIRY, inquiryForm.documentDetails, methods.reset);

  return (
    <DocumentsForm
      methods={methods}
      onSubmit={onSubmit}
      setDocumentsFormEdited={setStepDocumentsEdited}
      hasAttachmentForm={false}
      hasAttachmentType={true}
      informingMessageCode={"m.inquiry.files.info"}
      fileGroup={fileGroups.editableDoc}
      applicationId={inquiryForm.id}
    />
  );
};
export default InquiryStepDocuments;
