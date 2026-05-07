import { AddressView, PersonView } from "@duosoftbg/nacid-backoffice-components";
import * as React from "react";
import ViewDataWrapper from "./ViewDataWrapper";
import useFormViewData from "./useFormViewData";
import {
  BoxSpg,
  DividerSpg,
  GridContainer,
  LabeledDataItem,
  objectNestedPropByString,
} from "@duosoftbg/nacid-components";

export const ApplicantView = () => {
  const { viewData } = useFormViewData();

  const applicant = viewData?.applicant;
  if (!applicant) {
    return null;
  }

  return (
    <ViewDataWrapper>
      <PersonView person={applicant} renderType={"inlineText"} />
    </ViewDataWrapper>
  );
};

export const RepresentativeView = () => {
  const { viewData } = useFormViewData();

  const representative = viewData?.representative;
  if (!representative) {
    return null;
  }

  const representativeCompany = viewData?.representativeCompany;

  return (
    <ViewDataWrapper>
      <PersonView person={representative} renderType={"inlineText"} />
      {representativeCompany && (
        <>
          <BoxSpg mt={2} mb={1} style={{ fontSize: 14 }}>
            {"Фирма"}
          </BoxSpg>
          <DividerSpg mb={1} />
          <PersonView person={representativeCompany} renderType={"inlineText"} />
        </>
      )}
    </ViewDataWrapper>
  );
};

export const DiplomaOwnerView = () => {
  const { viewData } = useFormViewData();

  const diplomaOwner = viewData?.diplomaOwner;
  if (!diplomaOwner) {
    return null;
  }

  return (
    <ViewDataWrapper>
      <PersonView person={diplomaOwner} renderType={"inlineText"} />
    </ViewDataWrapper>
  );
};

export const ContactAddressView = () => {
  const { viewData } = useFormViewData();

  const contactAddress = viewData?.contactAddress;
  if (!contactAddress) {
    return null;
  }

  return (
    <ViewDataWrapper>
      <AddressView address={contactAddress} renderType={"inlineText"} withGridContainer />
    </ViewDataWrapper>
  );
};

export const DocumentRecipientAddressView = ({
  documentRecipientAddressPointer = "receiveMethod.documentRecipientAddress",
}: {
  documentRecipientAddressPointer?: string;
}) => {
  const { viewData } = useFormViewData();

  const documentRecipientAddress = objectNestedPropByString(documentRecipientAddressPointer, viewData);
  if (!documentRecipientAddress) {
    return null;
  }

  return (
    <ViewDataWrapper>
      <AddressView address={documentRecipientAddress} renderType={"inlineText"} withGridContainer />
    </ViewDataWrapper>
  );
};

export const DocumentReceiveMethodView = () => {
  const { viewData } = useFormViewData();

  const documentReceiveMethod = viewData?.documentReceiveMethod?.name;
  if (!documentReceiveMethod) {
    return null;
  }

  return (
    <ViewDataWrapper>
      <LabeledDataItem labelCode={"t.documentReceiveMethod.details"} data={documentReceiveMethod} md={12} sm={12} />
    </ViewDataWrapper>
  );
};

export const UniversityView = () => {
  const { viewData } = useFormViewData();

  const university = viewData?.baseUniversity;
  if (!university) {
    return null;
  }

  const countryName = university?.country?.name;
  const universityName = university?.bgName;

  return (
    <ViewDataWrapper>
      <GridContainer spacing={0} mt={0}>
        <LabeledDataItem labelCode={"l.name.v2"} data={universityName} />
        <LabeledDataItem labelCode={"l.country"} data={countryName} />
      </GridContainer>
    </ViewDataWrapper>
  );
};
