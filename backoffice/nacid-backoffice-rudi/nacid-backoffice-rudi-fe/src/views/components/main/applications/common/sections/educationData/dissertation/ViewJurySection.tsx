import React from "react";
import { GridContainer, LabeledDataItem, TextSection } from "@duosoftbg/nacid-components";
import { AccordionDetails } from "@mui/material";
import useAppSelector from "../../../../../../../../hooks/redux/base/useAppSelector";

const ViewJurySection = () => {
  const viewData = useAppSelector((state) => state["ViewData"]);

  const trainingCourse = viewData.data?.trainingCourse;

  const scientificSupervisor = trainingCourse?.scientificSupervisor;
  const scientificSupervisorEn = trainingCourse?.scientificSupervisorEn;
  const reviewers = trainingCourse?.reviewers;
  const reviewersEn = trainingCourse?.reviewersEn;
  const juryChair = trainingCourse?.juryChair;
  const juryChairEn = trainingCourse?.juryChairEn;
  const juryMembers = trainingCourse?.juryMembers;
  const juryMembersEn = trainingCourse?.juryMembersEn;

  if (
    !scientificSupervisor &&
    !scientificSupervisorEn &&
    !reviewers &&
    !reviewersEn &&
    !juryChair &&
    !juryChairEn &&
    !juryMembers &&
    !juryMembersEn
  ) {
    return null;
  }

  return (
    <AccordionDetails>
      <TextSection label={"t.jury.title"} withDivider>
        <GridContainer>
          <LabeledDataItem labelCode={"l.scientificSupervisor"} data={scientificSupervisor} />
          <LabeledDataItem labelCode={"l.scientificSupervisorEn"} data={scientificSupervisorEn} />

          <LabeledDataItem labelCode={"l.reviewers"} data={reviewers} />
          <LabeledDataItem labelCode={"l.reviewersEn"} data={reviewersEn} />

          <LabeledDataItem labelCode={"l.juryChair"} data={juryChair} />
          <LabeledDataItem labelCode={"l.juryChairEn"} data={juryChairEn} />

          <LabeledDataItem labelCode={"l.juryMembers"} data={juryMembers} />
          <LabeledDataItem labelCode={"l.juryMembersEn"} data={juryMembersEn} />
        </GridContainer>
      </TextSection>
    </AccordionDetails>
  );
};

export default ViewJurySection;
