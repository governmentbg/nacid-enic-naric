import { GridContainer, GridItem, TextareaFormField } from "@duosoftbg/nacid-components";
import { Typography } from "@mui/material";
import React from "react";
import { useTranslation } from "react-i18next";

const NotesData = () => {
  const { t } = useTranslation();

  return (
    <GridContainer spacing={4} mt={0}>
      <GridItem sm={12} md={12}>
        <Typography variant={"h6"} color={"primary"}>
          {t("t.notes.data")}
        </Typography>
      </GridItem>
      <GridItem sm={12} md={12}>
        <TextareaFormField fieldName={"notes"} labelCode={"l.notes"} />
      </GridItem>
    </GridContainer>
  );
};
export default NotesData;
