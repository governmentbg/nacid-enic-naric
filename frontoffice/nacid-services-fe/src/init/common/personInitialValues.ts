import { Company, ServicesNaturalPerson, University } from "../../types/common/personTypes";
import { IdentifierType } from "@duosoftbg/nacid-frontoffice-components";
import { initialCountryBG, initialCountryEmpty, initialSettlement } from "./addressInitialValues";
import { ReferenceDataDomain } from "@duosoftbg/nacid-components";

export const initialServicesNaturalPerson: ServicesNaturalPerson = {
  birthCountry: initialCountryEmpty,
  citizenship: initialCountryEmpty,
  birthPlace: "",
  birthSettlement: initialSettlement,
  dateOfBirth: "",
  email: "",
  firstName: "",
  middleName: "",
  lastName: "",
  personalIdType: IdentifierType.NATIONAL_ID,
  personalId: "",
  personalNacidId: "",
  foreignerIdentifierCountry: { id: "", name: "" },
  foreignerIdentifierKind: { id: "", name: "", domain: ReferenceDataDomain.FOREIGN_IDENTIFIER_TYPE },
  humanitarianStatus: { id: "", name: "", domain: ReferenceDataDomain.HUMANITARIAN_STATUS },
  title: "",
};

export const initialCompany: Company = {
  companyCountry: initialCountryBG,
  companyCity: "",
  companyIdentifier: "",
  companyName: "",
  companySettlement: initialSettlement,
};

export const initialUniversity: University = {
  universityIdentifier: "",
  universityName: "",
  universityCountry: initialCountryBG,
  universitySettlement: initialSettlement,
};
