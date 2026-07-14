import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import React from "react";
import {
  completeDocumentStep,
  setStepDocumentsEdited,
} from "../../../../../../store/redux/slice/Forms/officialNotesForm";
import DocumentsForm from "../../../common/form/document/DocumentsForm";
import { ApplicationSubtype } from "@duosoftbg/nacid-components";
import useFormReset from "../../../../../../hooks/useFormReset";
import { baseEndpointPaths } from "../../../../../../services/serviceCalls";
import useStepDocuments from "../../../../../../hooks/useStepDocuments";

const OfficialNotesStepDocuments = () => {
  const officialNotesForm = useAppSelector((state) => {
    return state.Forms.OfficialNotesForm;
  });

  const { methods, onSubmit } = useStepDocuments(
    officialNotesForm,
    baseEndpointPaths.officialNotes,
    completeDocumentStep,
    setStepDocumentsEdited
  );

  useFormReset(ApplicationSubtype.OFFICIAL_NOTE, officialNotesForm.documentDetails, methods.reset);

  return (
    <DocumentsForm
      methods={methods}
      onSubmit={onSubmit}
      setDocumentsFormEdited={setStepDocumentsEdited}
      hasAttachmentForm={false}
      hasAttachmentType={true}
      applicationId={officialNotesForm.id}
      informingMessageCode={"m.general.files.info"}
    />
  );
};
export default OfficialNotesStepDocuments;
