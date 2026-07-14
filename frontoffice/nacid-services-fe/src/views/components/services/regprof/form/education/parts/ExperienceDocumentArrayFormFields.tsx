import { useTranslation } from "react-i18next";
import { useFieldArray, useFormContext, useWatch } from "react-hook-form";
import { GridContainer, GridItem, RelativeBox, BoxedContent } from "@duosoftbg/nacid-components";
import { IconButton, Typography } from "@mui/material";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faXmark } from "@fortawesome/free-solid-svg-icons";
import AddRecordButton from "../../../../../common/button/AddRecordButton";
import React from "react";
import { initialRegprofExperienceDocument } from "../../../../../../../init/regprofInitialValues";
import ExperienceDocumentFormFields from ".//ExperienceDocumentFormFields";

const ExperienceDocumentArrayFormFields = () => {
  const { t } = useTranslation();

  const { control, getValues } = useFormContext();
  useWatch({ name: "experienceSelected" });

  const { append, remove } = useFieldArray({
    control,
    name: "experience.experienceDocuments",
  });

  const addNewExpDocumentData = () => {
    const newVal = { ...initialRegprofExperienceDocument };
    newVal.key = Math.random();
    append(newVal);
  };

  const removeExpDocumentData = (index) => {
    remove(index);
  };

  const renderAdditionalExperienceDocument = (doc, ind) => {
    if (ind === 0) {
      return null;
    } else {
      return (
        <BoxedContent key={ind}>
          <GridContainer>
            <GridItem xs={11} sm={11} md={11.5}>
              <Typography variant={"h4"} color={"primary"}>
                {t("t.regprof.experience.experienceDocument")}
              </Typography>
            </GridItem>
            <GridItem xs={1} sm={1} md={0.5}>
              <IconButton
                title={t("l.btn.remove")}
                color={"error"}
                style={{ position: "relative" }}
                onClick={(e) => removeExpDocumentData(ind)}
              >
                <FontAwesomeIcon style={{ fontSize: 20 }} icon={faXmark} />
              </IconButton>
            </GridItem>
          </GridContainer>

          <ExperienceDocumentFormFields index={ind} key={doc.key} />
        </BoxedContent>
      );
    }
  };

  if (!getValues().experienceSelected) {
    return null;
  } else {
    return (
      <>
        <ExperienceDocumentFormFields index={0} key={initialRegprofExperienceDocument.key} />
        <RelativeBox>
          {getValues().experience.experienceDocuments.map((doc, ind) => renderAdditionalExperienceDocument(doc, ind))}

          <AddRecordButton labelCode={"l.btn.experienceDocument.add"} onClick={addNewExpDocumentData} />
        </RelativeBox>
      </>
    );
  }
};
export default ExperienceDocumentArrayFormFields;
