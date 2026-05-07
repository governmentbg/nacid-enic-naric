import React from "react";
import { GridContainer, LabeledDataItem, TextSection } from "@duosoftbg/nacid-components";
import { AccordionDetails } from "@mui/material";
import useAppSelector from "../../../../../../../../hooks/redux/base/useAppSelector";
import { AppType } from "@duosoftbg/nacid-backoffice-components";

type ViewSchoolSectionProps = {
  appType: AppType;
};

const ViewSchoolSection = ({ appType }: ViewSchoolSectionProps) => {
  const viewData = useAppSelector((state) => {
    return state["ViewData"];
  });

  const trainingCourse = viewData.data.trainingCourse;

  if (
    !trainingCourse?.schoolCountry?.name &&
    !trainingCourse?.schoolCity &&
    !trainingCourse?.schoolName &&
    !trainingCourse?.schoolGraduationDate &&
    !trainingCourse?.schoolNotes
  ) {
    return null;
  }

  return (
    <AccordionDetails>
      <TextSection label={"t.base.school.details"} withDivider>
        <GridContainer>
          <LabeledDataItem labelCode={"l.country"} data={trainingCourse?.schoolCountry?.name} />
          {/*<LabeledDataItem labelCode={"l.originalName"} data={trainingCourse?.schoolSettlement} />*/}
          <LabeledDataItem labelCode={"l.schoolCity"} data={trainingCourse?.schoolCity} />
          <LabeledDataItem labelCode={"l.schoolName"} data={trainingCourse?.schoolName} />
          <LabeledDataItem
            labelCode={"l.schoolGraduationDate"}
            data={
              trainingCourse?.schoolGraduationDate ? new Date(trainingCourse?.schoolGraduationDate).getFullYear() : ""
            }
          />
          <LabeledDataItem labelCode={"l.schoolNotes"} data={trainingCourse?.schoolNotes} />
        </GridContainer>
      </TextSection>
    </AccordionDetails>
  );
};
export default ViewSchoolSection;
