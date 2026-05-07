import React from "react";
import { BlockText, GridContainer, GridItem } from "@duosoftbg/nacid-components";

const ExpertSpecificDataViewBody = ({ expert }) => {
  return (
    <>
      <GridContainer spacing={3} mt={0}>
        <GridItem sm={12} md={12}>
          <BlockText
            label={"l.application.commission.member"}
            text={
              expert.commissionMember.middleName !== null
                ? `${expert.commissionMember.firstName} ${expert.commissionMember.middleName} ${expert.commissionMember.lastName}`
                : `${expert.commissionMember.firstName} ${expert.commissionMember.lastName}`
            }
          />
        </GridItem>
        {expert.notes && (
          <GridItem sm={12} md={12}>
            <BlockText label={"l.notes"} text={expert.notes} />
          </GridItem>
        )}
      </GridContainer>
    </>
  );
};

export default ExpertSpecificDataViewBody;
