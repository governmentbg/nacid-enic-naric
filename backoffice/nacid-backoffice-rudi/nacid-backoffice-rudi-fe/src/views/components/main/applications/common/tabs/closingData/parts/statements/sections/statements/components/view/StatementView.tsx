import * as React from "react";
import { useTranslation } from "react-i18next";
import { BlockText, CardSpg, GridContainer, GridItem } from "@duosoftbg/nacid-components";
import CardContent from "@mui/material/CardContent";
import PageWrapper from "../../../../../../../../../../../common/layout/PageWrapper";
import { AttachmentListView } from "@duosoftbg/nacid-backoffice-components";

const StatementView = ({ statement }) => {
  const { t } = useTranslation();

  return (
    <PageWrapper title={t("t.application.expert.statement.view")}>
      {statement && (
        <CardSpg my={4} style={{ overflow: "visible" }}>
          <CardContent style={{ padding: 24, position: "relative" }}>
            {" "}
            <GridContainer spacing={3} mt={0}>
              {statement?.commissionMember?.id && (
                <GridItem sm={12} md={6}>
                  <BlockText
                    label={"l.application.commission.member"}
                    text={
                      statement.commissionMember.middleName !== null
                        ? `${statement.commissionMember.firstName} ${statement.commissionMember.middleName} ${statement.commissionMember.lastName}`
                        : `${statement.commissionMember.firstName} ${statement.commissionMember.lastName}`
                    }
                  />
                </GridItem>
              )}

              {statement?.attachedDoc?.documentType?.id && (
                <GridItem sm={12} md={6}>
                  <BlockText label={"l.commissionMembersDocTypes"} text={statement.attachedDoc.documentType.name} />
                </GridItem>
              )}

              {statement?.attachedDoc?.description && (
                <GridItem sm={12} md={12}>
                  <BlockText label={"l.description"} text={statement.attachedDoc.description} />
                </GridItem>
              )}
              <AttachmentListView attachments={statement?.attachedDoc?.attachedDocAttachments} />
            </GridContainer>
          </CardContent>
        </CardSpg>
      )}
    </PageWrapper>
  );
};

export default StatementView;
