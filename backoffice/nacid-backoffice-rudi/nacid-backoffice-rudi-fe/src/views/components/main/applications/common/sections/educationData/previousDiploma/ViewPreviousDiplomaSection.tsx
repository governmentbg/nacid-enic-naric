import React from "react";
import { GridContainer, LabeledDataItem, TextSection } from "@duosoftbg/nacid-components";
import { AccordionDetails } from "@mui/material";
import useAppSelector from "../../../../../../../../hooks/redux/base/useAppSelector";
import { AppType } from "@duosoftbg/nacid-backoffice-components";

type ViewPreviousDiplomaSectionProps = {
  appType: AppType;
};

const ViewPreviousDiplomaSection = ({ appType }: ViewPreviousDiplomaSectionProps) => {
  const viewData = useAppSelector((state) => {
    return state["ViewData"];
  });

  const trainingCourse = viewData.data.trainingCourse;
  const prevDiplomaUniversity = trainingCourse?.prevDiplomaUniversity;

  if (
    !prevDiplomaUniversity &&
    !trainingCourse?.prevDiplomaEduLevel?.name &&
    !trainingCourse?.prevDiplomaGraduationDate &&
    !trainingCourse?.prevDiplomaSpeciality &&
    !trainingCourse?.prevDiplomaNotes
  ) {
    return null;
  }

  return (
    <AccordionDetails>
      <TextSection label={"t.base.previous.diploma.details"} withDivider>
        <GridContainer>
          <LabeledDataItem labelCode={"l.prevDiplomaUniversity"} data={prevDiplomaUniversity?.bgName} />
          <LabeledDataItem labelCode={"l.prevDiplomaUniversity.country"} data={prevDiplomaUniversity?.country?.name} />
          <LabeledDataItem labelCode={"l.prevDiplomaUniversity.city"} data={prevDiplomaUniversity?.address?.city} />
          <LabeledDataItem labelCode={"l.prevDiplomaEduLevel"} data={trainingCourse?.prevDiplomaEduLevel?.name} />
          <LabeledDataItem
            labelCode={"l.prevDiplomaGraduationDate"}
            data={
              trainingCourse?.prevDiplomaGraduationDate
                ? new Date(trainingCourse?.prevDiplomaGraduationDate).getFullYear()
                : ""
            }
          />
          <LabeledDataItem labelCode={"l.prevDiplomaSpeciality"} data={trainingCourse?.prevDiplomaSpeciality} />
          <LabeledDataItem labelCode={"l.prevDiplomaNotes"} data={trainingCourse?.prevDiplomaNotes} />
        </GridContainer>
      </TextSection>
    </AccordionDetails>
  );
};
export default ViewPreviousDiplomaSection;
