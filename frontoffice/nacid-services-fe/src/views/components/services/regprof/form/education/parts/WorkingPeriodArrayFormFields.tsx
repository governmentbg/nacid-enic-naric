import { useTranslation } from "react-i18next";
import { useFieldArray, useFormContext } from "react-hook-form";
import { initialWorkPeriod } from "../../../../../../../init/regprofInitialValues";
import { BoxSpg, GridContainer, GridItem, RelativeBox } from "@duosoftbg/nacid-components";
import { Alert, IconButton, Typography } from "@mui/material";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faXmark } from "@fortawesome/free-solid-svg-icons";
import AddRecordButton from "../../../../../common/button/AddRecordButton";
import React from "react";
import WorkingPeriodFormFields from "./WorkingPeriodFormFields";

const WorkingPeriodArrayFormFields = ({ documentIndex }) => {
  const { t } = useTranslation();

  const { control, getValues } = useFormContext();

  const { append, remove } = useFieldArray({
    control,
    name: `experience.experienceDocuments.${documentIndex}.workPeriods`,
  });

  const addNewWorkPeriodData = () => {
    const newVal = { ...initialWorkPeriod };
    newVal.key = Math.random();
    append(newVal);
  };

  const removeWorkPeriodData = (index) => {
    remove(index);
  };

  const renderAdditionalWorkPeriod = (period, ind) => {
    return (
      <BoxSpg key={ind}>
        <GridContainer>
          <GridItem xs={11} sm={11} md={11.5}>
            <WorkingPeriodFormFields documentIndex={documentIndex} index={ind} key={period.key} />
          </GridItem>
          <GridItem xs={1} sm={1} md={0.5}>
            <IconButton
              title={t("l.btn.remove")}
              color={"error"}
              style={{ position: "relative" }}
              onClick={(e) => removeWorkPeriodData(ind)}
            >
              <FontAwesomeIcon style={{ fontSize: 20 }} icon={faXmark} />
            </IconButton>
          </GridItem>
        </GridContainer>
      </BoxSpg>
    );
  };

  return (
    <RelativeBox mt={2}>
      <GridContainer>
        <Typography variant={"h6"} color={"primary"}>
          {t("t.regprof.experience.experienceDocument.workPeriods")}
        </Typography>
      </GridContainer>
      {getValues().experience.experienceDocuments[documentIndex].workPeriods.length < 1 ? (
        <GridContainer>
          <GridItem sm={12} md={12}>
            <Alert severity={"info"}> {t("m.regprof.experience.experienceDocument.workPeriods.none")}</Alert>
          </GridItem>
        </GridContainer>
      ) : (
        <>
          {getValues().experience.experienceDocuments[documentIndex].workPeriods.map((period, ind) =>
            renderAdditionalWorkPeriod(period, ind)
          )}
        </>
      )}

      <AddRecordButton labelCode={"l.btn.workPeriod.add"} onClick={addNewWorkPeriodData} />
    </RelativeBox>
  );
};

export default WorkingPeriodArrayFormFields;
