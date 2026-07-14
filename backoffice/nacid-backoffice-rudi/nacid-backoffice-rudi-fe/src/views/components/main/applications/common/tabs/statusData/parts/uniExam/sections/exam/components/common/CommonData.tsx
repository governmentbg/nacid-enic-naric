import { DateFormField, GridContainer, GridItem } from "@duosoftbg/nacid-components";
import { Typography } from "@mui/material";
import React from "react";
import { useTranslation } from "react-i18next";

const CommonData = () => {
  const { t } = useTranslation();

  return (
    <GridContainer spacing={4} mt={0}>
      <GridItem sm={12} md={12}>
        <Typography variant={"h6"} color={"primary"}>
          {t("t.common.data")}
        </Typography>
      </GridItem>
      <GridItem sm={4} md={4}>
        <DateFormField fieldName={"examinationDate"} labelCode={"l.examinationDate"} required={true} />
      </GridItem>
    </GridContainer>
  );
};
export default CommonData;
