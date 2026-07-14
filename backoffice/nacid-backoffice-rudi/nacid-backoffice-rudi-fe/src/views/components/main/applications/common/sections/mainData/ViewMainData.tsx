import { useTranslation } from "react-i18next";
import React from "react";
import {
  AccordionItemBox,
  AccordionSummaryStld,
  GridContainer,
  LabeledDataItem,
  TextSection,
} from "@duosoftbg/nacid-components";
import { Accordion, AccordionDetails, Typography } from "@mui/material";
import { ExpandMore } from "@mui/icons-material";
import { AddressView, AppType, DocumentReceiveMethodView, PersonView } from "@duosoftbg/nacid-backoffice-components";
import useAppSelector from "../../../../../../../hooks/redux/base/useAppSelector";

type ViewMainDataProps = {
  appType: AppType;
};

const ViewMainData = ({ appType }: ViewMainDataProps) => {
  const { t } = useTranslation();

  const viewData = useAppSelector((state) => {
    return state["ViewData"];
  });

  const application = viewData.data?.application;
  const sarApplication = viewData.data?.sarApplication;
  const applicant = application?.applicant;
  const diplomaOwner = viewData.data?.trainingCourse?.diplomaOwner;
  const representative = application?.representative;
  const applicantDiplomaNames = application?.applicantDiplomaNames;
  const representativeCompany = application?.representativeCompany;
  const contactAddress = application?.contactAddress;
  const documentReceiveMethods = application?.documentReceiveMethods;
  const serviceType = application?.serviceType;
  const dataAuthenticFlag = application?.dataAuthenticFlag;
  const personalDataUsageFlag = application?.personalDataUsageFlag;
  const notes = application?.notes;
  const personalDocumentType = application?.personalDocumentType?.name;
  const applicationNotes = application?.applicationNotes;

  return (
    <AccordionItemBox mt={1}>
      <Accordion defaultExpanded={true}>
        <AccordionSummaryStld expandIcon={<ExpandMore />}>
          <Typography variant={"h4"}>{t("t.applicationData")}</Typography>
        </AccordionSummaryStld>
        {applicant && (
          <AccordionDetails>
            <TextSection label={"t.applicant.personal.details"} withDivider>
              <PersonView person={applicant} renderType={"inlineText"} />
              <GridContainer>
                <LabeledDataItem labelCode={"l.personalDocumentType"} data={personalDocumentType} />
              </GridContainer>
            </TextSection>
          </AccordionDetails>
        )}
        {representative && (
          <AccordionDetails>
            <TextSection label={"t.representative.personal.details"} withDivider>
              <PersonView person={representative} renderType={"inlineText"} />
              <GridContainer>
                <LabeledDataItem labelCode={"l.representativeCapacity"} data={application.representativeCapacity} />
                <LabeledDataItem
                  labelCode={"l.representativeCompanyFlag"}
                  data={representativeCompany ? t("l.yes") : t("l.no")}
                />
                <LabeledDataItem
                  labelCode={"l.isBgAddressPartOfRepresentative"}
                  data={viewData.data.bgAddressOwner === "R" ? t("l.yes") : t("l.no")}
                />
                <LabeledDataItem
                  labelCode={"l.representativeAuthorizedFlag.short"}
                  data={viewData.data.representativeAuthorizedFlag ? t("l.yes") : t("l.no")}
                />
              </GridContainer>
            </TextSection>
          </AccordionDetails>
        )}
        {representativeCompany && (
          <AccordionDetails>
            <TextSection label={"t.representativeCompany.details"} withDivider>
              <PersonView person={representativeCompany} renderType={"inlineText"} />
            </TextSection>
          </AccordionDetails>
        )}
        {diplomaOwner && appType === AppType.SAR_APPLICATION && (
          <AccordionDetails>
            <TextSection label={"l.diplomaOwner"} withDivider>
              <PersonView person={diplomaOwner} renderType={"inlineText"} />
            </TextSection>
          </AccordionDetails>
        )}
        {applicantDiplomaNames && (
          <AccordionDetails>
            <TextSection label={"t.diploma.details"} withDivider>
              <GridContainer>
                <LabeledDataItem
                  labelCode={"l.diffDiplomaNamesFlag"}
                  data={applicantDiplomaNames ? t("l.yes") : t("l.no")}
                />
              </GridContainer>
              <GridContainer>
                <LabeledDataItem
                  labelCode={"l.name"}
                  data={
                    applicantDiplomaNames.firstName +
                    " " +
                    (applicantDiplomaNames.middleName ? applicantDiplomaNames.middleName + " " : "") +
                    applicantDiplomaNames.lastName
                  }
                />
                <LabeledDataItem labelCode={"l.person.civilIdType"} data={applicantDiplomaNames?.civilIdType?.name} />
                <LabeledDataItem
                  labelCode={"l.person.foreignIdentifierType"}
                  data={applicantDiplomaNames?.foreignIdentifierType?.name}
                />
                <LabeledDataItem
                  labelCode={"l.person.foreignIdentifierCountry"}
                  data={applicantDiplomaNames?.foreignIdentifierCountry?.name}
                />
                <LabeledDataItem labelCode={"l.person.civilId"} data={applicantDiplomaNames?.civilId} />
              </GridContainer>
            </TextSection>
          </AccordionDetails>
        )}
        {contactAddress && (
          <AccordionDetails>
            <TextSection label={"t.contactAddress.details"} withDivider>
              <AddressView address={contactAddress} renderType={"inlineText"} emailFirst={false} withGridContainer />
              <GridContainer>
                <LabeledDataItem
                  labelCode={"l.officialEmailCommunicationFlag.short"}
                  data={application.officialEmailCommunicationFlag ? t("l.yes") : t("l.no")}
                />
              </GridContainer>
            </TextSection>
          </AccordionDetails>
        )}
        <DocumentReceiveMethodView documentReceiveMethods={documentReceiveMethods} />
        {(sarApplication?.internalNumber || sarApplication?.outgoingNumber) && appType === AppType.SAR_APPLICATION && (
          <AccordionDetails>
            <TextSection label={"l.sarInOutNumbers"} withDivider>
              <GridContainer>
                <LabeledDataItem labelCode={"l.internalNumber"} data={sarApplication?.internalNumber} />
                <LabeledDataItem labelCode={"l.outgoingNumber"} data={sarApplication?.outgoingNumber} />
              </GridContainer>
            </TextSection>
          </AccordionDetails>
        )}
        <AccordionDetails>
          <TextSection label={"t.additional.data"} withDivider>
            <GridContainer>
              <LabeledDataItem labelCode={"t.serviceType.details"} data={serviceType?.name} sm={12} md={12} />
              <LabeledDataItem
                labelCode={"l.dataAuthenticFlag.short"}
                data={dataAuthenticFlag ? t("l.yes") : t("l.no")}
              />
              <LabeledDataItem
                labelCode={"l.personalDataUsageFlag.short"}
                data={personalDataUsageFlag ? t("l.yes") : t("l.no")}
              />
            </GridContainer>
            <GridContainer>
              <LabeledDataItem md={12} sm={12} labelCode={"l.notes"} data={notes} />
            </GridContainer>
          </TextSection>
        </AccordionDetails>
        {applicationNotes && applicationNotes.length > 0 && (
          <AccordionDetails>
            <TextSection key={"note"} label={"l.notes"} withDivider>
              <GridContainer>
                {applicationNotes.map((note, index) => (
                  <LabeledDataItem
                    key={"note-" + index}
                    labelCode={note?.createdUserFullName}
                    data={note?.note + " (" + note?.createdDate + ")"}
                    sm={12}
                    md={12}
                  />
                ))}
              </GridContainer>
            </TextSection>
          </AccordionDetails>
        )}
      </Accordion>
    </AccordionItemBox>
  );
};
export default ViewMainData;
