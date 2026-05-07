import * as yup from "yup";
import i18n from "i18next";
import { initializeYup } from "../../utils/yupUtils";

export const createAttachedDocumentValidationSchema = () => {
  initializeYup(yup);

  const attachedDocumentValidationSchema = yup.object({
    description: yup.mixed().when("attachmentType", {
      is: (attachmentType) =>
        attachmentType && attachmentType.id && attachmentType.id !== null && attachmentType.id !== "",
      then: yup.string().optional(),
      otherwise: yup.string().required(),
    }),
    file: yup.object({
      fileId: yup.string().required(i18n.t("validation.attachment.file.required")),
    }),
  });

  return attachedDocumentValidationSchema;
};

export const createSignedApplicationDocumentValidationSchema = () => {
  initializeYup(yup);

  const signedAppDocumentValidationSchema = yup.object({
    file: createFileValidationSchema(),
  });

  return signedAppDocumentValidationSchema;
};

export const createDocumentsValidationSchema = () => {
  initializeYup(yup);

  const documentsValidationSchema = yup.object({
    attachments: yup.array().optional(),
  });

  return documentsValidationSchema;
};

export const createFileValidationSchema = () => {
  initializeYup(yup);

  const schema = yup.object({
    fileId: yup.string().required(i18n.t("validation.attachment.file.required")),
  });

  return schema;
};
