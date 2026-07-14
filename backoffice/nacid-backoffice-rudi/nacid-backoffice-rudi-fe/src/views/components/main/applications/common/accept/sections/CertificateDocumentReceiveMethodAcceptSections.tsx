import {
  AcceptFormPrefilledModalUtils,
  AddressType,
  ApiUrlBuilder,
  AppType,
  CertificateReceiveFrom,
  CertificateReceiveMethodSection,
  CrfCodeDocumentReceiveMethodWrapper,
} from "@duosoftbg/nacid-backoffice-components";
import { useParams } from "react-router-dom";
import useFormViewData from "../viewData/useFormViewData";
import { DocumentRecipientAddressView } from "../viewData/viewData";

type CertificateDocumentReceiveMethodAcceptSectionsProps = {
  appType: AppType;
};

const CertificateDocumentReceiveMethodAcceptSections = ({
  appType,
}: CertificateDocumentReceiveMethodAcceptSectionsProps) => {
  const { id } = useParams();
  const { viewData } = useFormViewData();

  return (
    <>
      <CertificateReceiveMethodSection appType={appType} />
      <CrfCodeDocumentReceiveMethodWrapper
        sectionTitle={"t.paperDocumentReceiveMethod.details"}
        appType={appType}
        documentReceiveMethodFieldName={"documentReceiveMethod.paperReceivedMethod.documentReceiveMethod.id"}
        docRecipientAddressFieldName={"documentReceiveMethod.paperReceivedMethod.documentRecipientAddress.id"}
        filterByCrfCode={CertificateReceiveFrom.PAPER}
        errorPointer={`documentReceiveMethod${CertificateReceiveFrom.PAPER}`}
        addressViewDataComponent={
          <DocumentRecipientAddressView
            documentRecipientAddressPointer={"documentReceiveMethod.paperReceivedMethod.documentRecipientAddress"}
          />
        }
        searchFormDefaultValuesAddress={AcceptFormPrefilledModalUtils.fillAddressData(
          viewData?.documentReceiveMethod?.paperReceivedMethod?.documentRecipientAddress,
        )}
        loadAddressData={{
          title: "l.loadFromFoApplication",
          url: ApiUrlBuilder.importFoAddressUrl(id, AddressType.DOCUMENT, appType),
        }}
      />
      <CrfCodeDocumentReceiveMethodWrapper
        sectionTitle={"t.electronicDocumentReceiveMethod.details"}
        appType={appType}
        documentReceiveMethodFieldName={"documentReceiveMethod.electronicReceivedMethod.documentReceiveMethod.id"}
        docRecipientAddressFieldName={"documentReceiveMethod.electronicReceivedMethod.documentRecipientAddress.id"}
        filterByCrfCode={CertificateReceiveFrom.ELECTRONIC}
        errorPointer={`documentReceiveMethod${CertificateReceiveFrom.ELECTRONIC}`}
        addressViewDataComponent={
          <DocumentRecipientAddressView
            documentRecipientAddressPointer={"documentReceiveMethod.electronicReceivedMethod.documentRecipientAddress"}
          />
        }
        searchFormDefaultValuesAddress={AcceptFormPrefilledModalUtils.fillAddressData(
          viewData?.documentReceiveMethod?.electronicReceivedMethod?.documentRecipientAddress,
        )}
        loadAddressData={{
          title: "l.loadFromFoApplication",
          url: ApiUrlBuilder.importFoAddressUrl(id, AddressType.DOCUMENT, appType),
        }}
      />
    </>
  );
};
export default CertificateDocumentReceiveMethodAcceptSections;
