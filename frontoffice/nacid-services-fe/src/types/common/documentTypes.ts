import { DocType, ReferenceData } from "@duosoftbg/nacid-components";

export interface DocumentDetails {
  attachments: AttachedDocument[];
}

export interface AttachedDocument {
  attachmentType: DocType;
  description: string;
  attachmentForm: ReferenceData;
  file: FileStoreEntry;
  forRemoval: boolean;
}

export interface SignedApplicationDocument {
  file: FileStoreEntry;
}

export interface FileStoreEntry {
  fileId: string;
  fileName: string;
  fileSize: number;
  contentType: string;
  rootDirectory: string;
  relativePath: string;
  additionalMetadata: object;
}
