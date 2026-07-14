import { CommonApplication } from "./common/applicationTypes";
import { StepperApplication } from "./common/stepsTypes";
import { ReferenceData } from "@duosoftbg/nacid-components";
import { FileStoreEntry } from "./common/documentTypes";

export interface DocDeliveryApplication extends StepperApplication, CommonApplication {
  bibliographicDetails: DocBibliographicDetails;
}

export interface DocBibliographicDetails {
  entries: DocBibliographicEntryDetails[];
}

export interface DocBibliographicEntryDetails {
  key: number;
  bibliographicDataText: string;
  electronicCatalogues: boolean;
  bgLibraries: boolean;
  foreignLibraries: boolean;
  deliveryResultKind: ReferenceData;
  file: FileStoreEntry;
  forRemoval: boolean;
}
