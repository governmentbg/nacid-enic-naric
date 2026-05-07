import { ContactAddress, Country, ReceiverAddress, Settlement } from "@duosoftbg/nacid-components";

export const initialSettlement: Settlement = { id: "", name: "", fullSettlementName: "" };

export const initialCountryEmpty: Country = { id: "", name: "" };

export const initialCountryBG: Country = { id: "BG", name: "" };

export const initialContactAddress: ContactAddress = {
  address: "",
  city: "",
  country: initialCountryEmpty,
  settlement: initialSettlement,
  phone: "",
  postCode: "",
  email: "",
  fax: "",
  postBox: "",
};

export const initialReceiverAddress: ReceiverAddress = {
  name: "",
  country: initialCountryEmpty,
  settlement: initialSettlement,
  city: "",
  address: "",
  phone: "",
  postCode: "",
};
