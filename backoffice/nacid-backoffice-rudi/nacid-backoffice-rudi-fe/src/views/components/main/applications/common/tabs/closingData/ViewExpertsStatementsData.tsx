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
import useAppSelector from "../../../../../../../hooks/redux/base/useAppSelector";
import { useParams } from "react-router-dom";
import {
  AbdocsViewDocButton,
  AttachmentListView,
  ResponsibleUserHistoryTable,
  useAbdocsTransferRenderConfig,
} from "@duosoftbg/nacid-backoffice-components";

const ViewExpertsStatementsData = () => {
  const { t } = useTranslation();
  const { id } = useParams();

  const viewData = useAppSelector((state) => {
    return state["ViewData"];
  });

  const applicationCommissionMembers = viewData.data?.applicationCommissionMembers;
  const applicationCommissionMemberStatements = viewData.data?.applicationCommissionMemberStatements;

  if (
    (!applicationCommissionMembers && !applicationCommissionMemberStatements) ||
    (applicationCommissionMembers.length === 0 && applicationCommissionMemberStatements.length === 0)
  ) {
    return null;
  }

  return (
    <AccordionItemBox mt={1}>
      <Accordion defaultExpanded={false}>
        <AccordionSummaryStld expandIcon={<ExpandMore />}>
          <Typography variant={"h4"}>{t("l.expertsAndStatements")}</Typography>
        </AccordionSummaryStld>
        {applicationCommissionMembers && applicationCommissionMembers.length > 0 && (
          <AccordionDetails>
            {applicationCommissionMembers.map((member, index) => (
              <TextSection
                key={"expert" + index}
                label={
                  member?.commissionMember?.firstName +
                  " " +
                  (member?.commissionMember?.middleName ? member?.commissionMember?.middleName + " " : "") +
                  member?.commissionMember?.lastName +
                  " (" +
                  t("t.expertData") +
                  ")"
                }
                withDivider
              >
                <GridContainer>
                  <LabeledDataItem labelCode={"l.notes"} data={member?.notes} md={12} sm={12} />
                  <LabeledDataItem labelCode={"l.courseContent"} data={member?.courseContent} md={12} sm={12} />
                  <LabeledDataItem
                    labelCode={"l.commission.member.position"}
                    data={member?.commissionMemberPosition?.name}
                  />
                  <LabeledDataItem labelCode={"l.legal.reason"} data={member?.legalReason?.name} />
                  <LabeledDataItem labelCode={"l.educationLevel"} data={member?.eduLevel?.name} />
                  <LabeledDataItem labelCode={"l.prof.qualification"} data={member?.qualification} />
                  <LabeledDataItem
                    labelCode={"l.specialities"}
                    data={member?.applicationCommissionMemberSpecialities.map((spec) => spec.speciality).join(", ")}
                  />
                  <LabeledDataItem
                    labelCode={"l.previousBoardDecisions"}
                    data={member?.previousBoardDecisions}
                    md={12}
                    sm={12}
                  />
                  <LabeledDataItem labelCode={"l.similarBulgarianPrograms"} data={member?.similarBulgarianPrograms} />
                  <LabeledDataItem
                    labelCode={"l.processStatus"}
                    data={member?.processStatus ? t("l.yes") : t("l.no")}
                  />
                </GridContainer>
              </TextSection>
            ))}
          </AccordionDetails>
        )}
        {applicationCommissionMemberStatements && applicationCommissionMemberStatements.length > 0 && (
          <AccordionDetails>
            {applicationCommissionMemberStatements.map((statement, index) => (
              <CommissionMemberStatementRow key={"statement" + index} statement={statement} index={index} />
            ))}
          </AccordionDetails>
        )}
        <AccordionDetails>
          <TextSection key={"applicationResponsibleUsers"} label={"t.applicationResponsibleUsers.history"} withDivider>
            <ResponsibleUserHistoryTable applicationId={id} />
          </TextSection>
        </AccordionDetails>
      </Accordion>
    </AccordionItemBox>
  );
};

const CommissionMemberStatementRow = ({ statement, index }) => {
  const { t } = useTranslation();
  const { hasAbdocsLink, abdocsDoc } = useAbdocsTransferRenderConfig(statement.attachedDoc);

  return (
    <TextSection label={t("l.commission.member.position") + " " + (index + 1)} withDivider>
      <GridContainer>
        <LabeledDataItem
          labelCode={"t.expertData"}
          data={
            statement?.commissionMember?.firstName +
            " " +
            (statement?.commissionMember?.middleName ? statement?.commissionMember?.middleName + " " : "") +
            statement?.commissionMember?.lastName
          }
        />
        <LabeledDataItem labelCode={"l.commissionMembersDocTypes"} data={statement?.attachedDoc?.documentType?.name} />
        <LabeledDataItem labelCode={"l.description"} data={statement?.attachedDoc?.description} md={6} sm={6} />
        {!hasAbdocsLink && <AttachmentListView attachments={statement?.attachedDoc?.attachedDocAttachments} />}
        {hasAbdocsLink && (
          <AbdocsViewDocButton
            attachmentData={statement.attachedDoc}
            abdocsDoc={abdocsDoc}
            label={"l.btn.abdocs.link"}
          />
        )}
      </GridContainer>
    </TextSection>
  );
};

export default ViewExpertsStatementsData;
