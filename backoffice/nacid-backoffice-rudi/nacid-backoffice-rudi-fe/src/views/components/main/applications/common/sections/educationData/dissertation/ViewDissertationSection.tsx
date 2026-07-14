import React from "react";
import { GridContainer, LabeledDataItem, TextSection } from "@duosoftbg/nacid-components";
import { AccordionDetails } from "@mui/material";
import useAppSelector from "../../../../../../../../hooks/redux/base/useAppSelector";
import { AppType } from "@duosoftbg/nacid-backoffice-components";

type ViewDissertationSectionProps = {
  appType: AppType;
};

const ViewDissertationSection = ({ appType }: ViewDissertationSectionProps) => {
  const viewData = useAppSelector((state) => {
    return state["ViewData"];
  });

  const trainingCourse = viewData.data?.trainingCourse;
  // const profGroup = trainingCourse?.profGroup;
  const graduationDocumentType = trainingCourse?.graduationDocumentType;

  const thesisTopic = trainingCourse?.thesisTopic;
  const thesisTopicEn = trainingCourse?.thesisTopicEn;
  const thesisDefenceDate = trainingCourse?.thesisDefenceDate;
  const thesisLanguage = trainingCourse?.thesisLanguage;
  const thesisBibliography = trainingCourse?.thesisBibliography;
  const thesisVolume = trainingCourse?.thesisVolume;
  const thesisAnnotation = trainingCourse?.thesisAnnotation;
  const thesisAnnotationEn = trainingCourse?.thesisAnnotationEn;

  if (
    !thesisTopic &&
    !thesisTopicEn &&
    !thesisDefenceDate &&
    !thesisLanguage &&
    !thesisBibliography &&
    !thesisVolume &&
    !thesisAnnotation &&
    !thesisAnnotationEn &&
    // !profGroup &&
    !graduationDocumentType
  ) {
    return null;
  }

  return (
    <AccordionDetails>
      <TextSection label={"t.dissertation.details"} withDivider>
        <GridContainer>
          <LabeledDataItem labelCode={"l.thesisTopic"} data={thesisTopic} />
          <LabeledDataItem labelCode={"l.thesisTopicEn"} data={thesisTopicEn} />
          <LabeledDataItem labelCode={"l.thesisDefenceDate"} data={thesisDefenceDate} />
          <LabeledDataItem labelCode={"l.thesisLanguage"} data={thesisLanguage?.name} />
          <LabeledDataItem labelCode={"l.thesisBibliography"} data={thesisBibliography} />
          <LabeledDataItem labelCode={"l.thesisVolume"} data={thesisVolume} />
          <LabeledDataItem labelCode={"l.thesisAnnotation"} data={thesisAnnotation} />
          <LabeledDataItem labelCode={"l.thesisAnnotationEn"} data={thesisAnnotationEn} />
          {/*<LabeledDataItem labelCode={"l.profGroup"} data={profGroup?.name} />*/}
          {/*<LabeledDataItem labelCode={"l.profGroup.educationArea"} data={profGroup?.educationArea?.name} />*/}
          <LabeledDataItem labelCode={"l.graduationDocumentTypeId"} data={graduationDocumentType?.name} />
        </GridContainer>
      </TextSection>
    </AccordionDetails>
  );
};
export default ViewDissertationSection;
