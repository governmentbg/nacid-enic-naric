import { ReferenceData } from "@duosoftbg/nacid-components";

export interface ReportApplicationType {
  applicationTypes: {
    id: string;
    name: string;
  }[];
  sarServices: ReferenceData[];
  sarServicesJoin: string;
}

export interface LegalApplicant {
  id?: number;
  eik: string;
  name: string;
}
