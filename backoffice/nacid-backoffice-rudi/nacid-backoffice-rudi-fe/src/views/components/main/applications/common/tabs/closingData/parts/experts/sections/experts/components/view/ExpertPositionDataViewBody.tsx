import React, { useEffect, useState } from "react";
import { BlockText, GridContainer, GridItem, isArrayNotEmpty, StringArrayChipList } from "@duosoftbg/nacid-components";
import { useTranslation } from "react-i18next";

const ExpertPositionDataViewBody = ({ expert }) => {
  const { t } = useTranslation();
  const [specialities, setSpecialities] = useState([]);
  useEffect(() => {
    if (isArrayNotEmpty(expert.applicationCommissionMemberSpecialities)) {
      setSpecialities(
        expert.applicationCommissionMemberSpecialities.map(function (obj) {
          return obj.speciality;
        }),
      );
    }
    // eslint-disable-next-line
  }, []);

  return (
    <>
      <GridContainer spacing={3} mt={0}>
        {expert.courseContent && (
          <GridItem sm={12} md={12}>
            <BlockText label={"l.courseContent"} text={expert.courseContent} />
          </GridItem>
        )}

        {expert?.commissionMemberPosition?.name && (
          <GridItem sm={12} md={6}>
            <BlockText label={"l.commission.member.position"} text={expert.commissionMemberPosition.name} />
          </GridItem>
        )}

        {expert?.legalReason?.name && (
          <GridItem sm={12} md={6}>
            <BlockText label={"l.legal.reason"} text={expert.legalReason.name} />
          </GridItem>
        )}

        {expert?.eduLevel?.name && (
          <GridItem sm={12} md={6}>
            <BlockText label={"l.educationLevel"} text={expert.eduLevel.name} />
          </GridItem>
        )}
        {expert.qualification && (
          <GridItem sm={12} md={6}>
            <BlockText label={"l.prof.qualification"} text={expert.qualification} />
          </GridItem>
        )}
      </GridContainer>

      {isArrayNotEmpty(specialities) && (
        <div style={{ marginTop: "10px" }}>
          <StringArrayChipList
            list={specialities}
            listLabel={"l.selected.speciality"}
            isLabelBold={true}
            hasRemoval={false}
          ></StringArrayChipList>
        </div>
      )}

      <GridContainer spacing={3} mt={0}>
        {expert.previousBoardDecisions && (
          <GridItem sm={12} md={12}>
            <BlockText label={"l.previousBoardDecisions"} text={expert.previousBoardDecisions} />
          </GridItem>
        )}

        {expert.similarBulgarianPrograms && (
          <GridItem sm={12} md={12}>
            <BlockText label={"l.similarBulgarianPrograms"} text={expert.similarBulgarianPrograms} />
          </GridItem>
        )}

        {expert.processStatus && (
          <GridItem sm={4} md={4}>
            <BlockText label={"l.processStatus"} text={expert.processStatus ? t("l.yes") : t("l.no")} />
          </GridItem>
        )}
      </GridContainer>
    </>
  );
};

export default ExpertPositionDataViewBody;
