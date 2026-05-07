import {
  AbdocsTransferAttachmentsDialog,
  SaveAttachmentDialog,
  SaveCompetentInstitutionDialog,
  ViewCompetentInstitutionDialog,
} from "@duosoftbg/nacid-backoffice-components";
import ViewUniExaminationDialog from "../../../../sections/statusData/uniExamination/components/dialog/view/ViewUniExaminationDialog";
import * as React from "react";

const DialogsProvider = ({ tempDataKey, competentInstitutionPointer }) => {
  return (
    <>
      <SaveCompetentInstitutionDialog
        resetExternalField={true}
        tempFormDataKey={tempDataKey}
        fieldId={competentInstitutionPointer}
      />
      <ViewUniExaminationDialog />
      <ViewCompetentInstitutionDialog />
      <SaveAttachmentDialog />
      <AbdocsTransferAttachmentsDialog />
    </>
  );
};
export default DialogsProvider;
