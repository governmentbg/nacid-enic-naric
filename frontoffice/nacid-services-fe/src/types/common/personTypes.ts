import { Country, NaturalPerson, Settlement } from "@duosoftbg/nacid-components";

export interface ServicesNaturalPerson extends NaturalPerson {
  userName?: string;
}

export interface Company {
  companyCountry: Country;
  companyIdentifier: string;
  companyName: string;
  companyCity: string;
  companySettlement: Settlement;
}

export interface University {
  universityIdentifier: string;
  universityName: string;
  universityCountry: Country;
  universitySettlement: Settlement;
}

export enum ApplicantType {
  NATURAL_PERSON = "NATURAL_PERSON",
  COMPANY = "COMPANY",
  UNIVERSITY = "UNIVERSITY",
}
