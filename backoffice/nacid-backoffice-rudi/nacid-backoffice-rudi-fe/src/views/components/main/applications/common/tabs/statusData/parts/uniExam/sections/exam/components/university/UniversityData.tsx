import { CheckboxFormField, GridContainer, GridItem } from "@duosoftbg/nacid-components";
import { Typography } from "@mui/material";
import React from "react";
import { useTranslation } from "react-i18next";
import TrainingLocation from "./TrainingLocation";
import TrainingForm from "./TrainingForm";

const UniversityData = () => {
  const { t } = useTranslation();

  return (
    <GridContainer mt={0}>
      <GridItem sm={12} md={12} pt={4}>
        <Typography variant={"h6"} color={"primary"}>
          {t("t.university.data")}
        </Typography>
      </GridItem>
      <GridItem sm={4} md={4}>
        <CheckboxFormField fieldName={`isRecognized`} labelCode={"l.uniExamination.isRecognized"} />
      </GridItem>
      <GridItem sm={4} md={4}>
        <CheckboxFormField fieldName={`isCommunicated`} labelCode={"l.uniExamination.isCommunicated"} />
      </GridItem>
      <GridItem sm={4} md={4}>
        <CheckboxFormField fieldName={`isJointDegree`} labelCode={"l.uniExamination.isJointDegree"} />
      </GridItem>
      <TrainingLocation />
      <TrainingForm />
    </GridContainer>
  );
};
export default UniversityData;
