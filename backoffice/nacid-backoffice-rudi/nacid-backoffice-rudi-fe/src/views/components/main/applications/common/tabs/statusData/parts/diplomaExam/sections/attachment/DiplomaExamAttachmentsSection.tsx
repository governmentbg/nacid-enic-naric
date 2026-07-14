import React from "react";
import { AttachmentType, DocumentCategories, TempAttachmentsListTable } from "@duosoftbg/nacid-backoffice-components";
import { FormSection } from "@duosoftbg/nacid-components";

const DiplomaExamAttachmentsSection = ({ applicationId, tempDataKey }) => {
  return (
    <FormSection label={"l.attachments"}>
      <TempAttachmentsListTable
        applicationId={applicationId}
        tempDataKey={tempDataKey}
        docCategory={DocumentCategories.DIPLOMA_EXAM}
        attachmentType={AttachmentType.RUDI_DIPLOMA_EXAM_ATTACHMENT}
      />
    </FormSection>
  );
};
export default DiplomaExamAttachmentsSection;
