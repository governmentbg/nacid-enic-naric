import { NaturalPersonIdentifier } from "@duosoftbg/nacid-components";
import { IdentifierType } from "@duosoftbg/nacid-frontoffice-components";
import { TFunction } from "i18next";

export const createIdentifierDetailsStr = (person: NaturalPersonIdentifier, t: TFunction) => {
  let idStr = person.personalId + " (" + t("l.person.personalIdType." + person.personalIdType);
  if (person.personalIdType === IdentifierType.DOCUMENT_ID) {
    idStr = idStr + ", " + person.foreignerIdentifierKind.name + ", " + person.foreignerIdentifierCountry?.name;
  }
  idStr = idStr + ")";
  return idStr;
};
