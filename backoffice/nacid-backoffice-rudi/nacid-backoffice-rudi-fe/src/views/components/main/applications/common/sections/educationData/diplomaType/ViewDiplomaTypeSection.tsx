import React from "react";
import { GridContainer, LabeledDataItem, TextSection } from "@duosoftbg/nacid-components";
import { AccordionDetails } from "@mui/material";
import useAppSelector from "../../../../../../../../hooks/redux/base/useAppSelector";
import { AppType } from "@duosoftbg/nacid-backoffice-components";

type ViewDiplomaTypeSectionProps = {
  appType: AppType;
};

const ViewDiplomaTypeSection = ({ appType }: ViewDiplomaTypeSectionProps) => {
  const viewData = useAppSelector((state) => {
    return state["ViewData"];
  });

  const trainingCourse = viewData.data?.trainingCourse;

  if (
    !trainingCourse?.diplomaNumber &&
    !trainingCourse?.diplomaDate &&
    !trainingCourse?.diplomaSeries &&
    !trainingCourse?.diplomaRegistrationNumber
  ) {
    return null;
  }

  return (
    <AccordionDetails>
      <TextSection label={"t.diploma.details"} withDivider>
        <GridContainer>
          <LabeledDataItem labelCode={"l.diplomaNumber"} data={trainingCourse?.diplomaNumber} />
          <LabeledDataItem labelCode={"l.diplomaDate"} data={trainingCourse?.diplomaDate} />
          <LabeledDataItem labelCode={"l.diplomaSeries"} data={trainingCourse?.diplomaSeries} />
          <LabeledDataItem labelCode={"l.diplomaRegistrationNumber"} data={trainingCourse?.diplomaRegistrationNumber} />
        </GridContainer>
      </TextSection>
    </AccordionDetails>
  );
};
export default ViewDiplomaTypeSection;
