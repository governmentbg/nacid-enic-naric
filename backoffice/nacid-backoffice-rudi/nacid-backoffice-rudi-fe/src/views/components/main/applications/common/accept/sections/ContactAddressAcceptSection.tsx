import { ContactAddressView } from "../viewData/viewData";
import {
  AddressType,
  ApiUrlBuilder,
  AppType,
  ContactAddressSection,
  AcceptFormPrefilledModalUtils,
} from "@duosoftbg/nacid-backoffice-components";
import * as React from "react";
import useFormViewData from "../viewData/useFormViewData";
import { useParams } from "react-router-dom";

type ContactAddressAcceptSectionProps = {
  appType: AppType;
};

const ContactAddressAcceptSection = ({ appType }: ContactAddressAcceptSectionProps) => {
  const { id } = useParams();
  const { viewData } = useFormViewData();

  return (
    <ContactAddressSection
      appType={appType}
      pointer={"contactAddressId"}
      viewDataComponent={<ContactAddressView />}
      searchFormDefaultValues={AcceptFormPrefilledModalUtils.fillAddressData(viewData?.contactAddress)}
      loadAddressData={{
        title: "l.loadFromFoApplication",
        url: ApiUrlBuilder.importFoAddressUrl(id, AddressType.CONTACT, appType),
      }}
    />
  );
};

export default ContactAddressAcceptSection;
