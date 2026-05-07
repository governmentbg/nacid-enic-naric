import {
  AttachedDocument,
  DocumentDetails,
  FileStoreEntry,
  SignedApplicationDocument,
} from "../../types/common/documentTypes";
import { ReferenceDataDomain } from "@duosoftbg/nacid-components";

export const initialFile: FileStoreEntry = {
  fileId: "",
  fileName: "",
  fileSize: 0,
  contentType: "",
  rootDirectory: "",
  relativePath: "",
  additionalMetadata: null,
};

export const initialAttachedDocument: AttachedDocument = {
  attachmentForm: { id: "", name: "", domain: ReferenceDataDomain.COPY_TYPE },
  attachmentType: { id: null, name: "" },
  description: "",
  file: initialFile,
  forRemoval: false,
};

export const initialSignedApplicationDocument: SignedApplicationDocument = {
  file: initialFile,
};

export const initialDocumentDetails: DocumentDetails = {
  attachments: [],
};
