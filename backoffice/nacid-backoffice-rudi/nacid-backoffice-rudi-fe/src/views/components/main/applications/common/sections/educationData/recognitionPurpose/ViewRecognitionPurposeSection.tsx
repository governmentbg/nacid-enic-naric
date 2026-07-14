import React from "react";
import { GridContainer, GridItem, TextSection } from "@duosoftbg/nacid-components";
import { AccordionDetails, Typography } from "@mui/material";
import useAppSelector from "../../../../../../../../hooks/redux/base/useAppSelector";
import { AppType } from "@duosoftbg/nacid-backoffice-components";

type ViewRecognitionPurposeSectionProps = {
  appType: AppType;
};

const ViewRecognitionPurposeSection = ({ appType }: ViewRecognitionPurposeSectionProps) => {
  const viewData = useAppSelector((state) => {
    return state["ViewData"];
  });

  const applicationRecognitionPurposes = viewData.data.applicationRecognitionPurposes;

  if (!applicationRecognitionPurposes || applicationRecognitionPurposes.length === 0) {
    return null;
  }

  return (
    <AccordionDetails>
      <TextSection label={"t.base.recognition.purpose.details"} withDivider>
        <GridContainer>
          {applicationRecognitionPurposes.map((row) => (
            <GridItem sm={6} md={6} key={row.id}>
              <Typography>
                {row.notes ? row.recognitionPurpose.name + " (" + row.notes + ")" : row.recognitionPurpose.name}
              </Typography>
            </GridItem>
          ))}
        </GridContainer>
      </TextSection>
    </AccordionDetails>
  );
};
export default ViewRecognitionPurposeSection;
