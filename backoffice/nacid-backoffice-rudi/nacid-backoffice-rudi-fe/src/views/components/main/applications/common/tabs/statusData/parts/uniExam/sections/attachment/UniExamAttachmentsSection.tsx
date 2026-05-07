import React from "react";
import { useFormContext } from "react-hook-form";
import { AttachmentType, DocumentCategories, TempAttachmentsListTable } from "@duosoftbg/nacid-backoffice-components";
import { FormSection } from "@duosoftbg/nacid-components";

const UniExamAttachmentsSection = ({ applicationId, tempDataKey }) => {
  const { getValues } = useFormContext();
  const universityId = getValues("university.id");
  const tempDataKeyRevised = `${tempDataKey}-${universityId}`;

  return (
    <FormSection label={"l.attachments"}>
      <TempAttachmentsListTable
        applicationId={applicationId}
        tempDataKey={tempDataKeyRevised}
        docCategory={DocumentCategories.UNI_EXAM}
        attachmentType={AttachmentType.RUDI_UNI_EXAM_ATTACHMENT}
      />
    </FormSection>
  );
};
export default UniExamAttachmentsSection;
