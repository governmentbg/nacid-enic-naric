import React from "react";
import { DividerSpg, GridContainer, GridItem, TextSection } from "@duosoftbg/nacid-components";
import { AccordionDetails, Typography } from "@mui/material";
import useAppSelector from "../../../../../../../../hooks/redux/base/useAppSelector";
import { AppType } from "@duosoftbg/nacid-backoffice-components";

type ViewNotesSectionProps = {
  appType: AppType;
};

const ViewNotesSection = ({ appType }: ViewNotesSectionProps) => {
  const viewData = useAppSelector((state) => {
    return state["ViewData"];
  });

  let applicationNotes = viewData.data.application?.applicationNotes;

  if (applicationNotes) {
    return (
      <AccordionDetails>
        <TextSection label={"t.base.notes.details"} withDivider>
          <GridContainer>
            {applicationNotes.map((appNote, index) => (
              <Note key={index} note={appNote?.note} index={index} />
            ))}
          </GridContainer>
        </TextSection>
      </AccordionDetails>
    );
  } else {
    return null;
  }
};

const Note = ({ index, note }) => {
  if (!note) {
    return null;
  }

  return (
    <>
      {index !== 0 && <DividerSpg my={4} />}
      <GridItem sm={12} md={12}>
        <Typography>{note}</Typography>
      </GridItem>
    </>
  );
};
export default ViewNotesSection;
