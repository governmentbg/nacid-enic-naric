import { useTranslation } from "react-i18next";
import { useFieldArray, useFormContext } from "react-hook-form";
import { BoxSpg, GridContainer, GridItem, RelativeBox } from "@duosoftbg/nacid-components";
import { IconButton, Typography } from "@mui/material";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faXmark } from "@fortawesome/free-solid-svg-icons";
import AddRecordButton from "../../../common/button/AddRecordButton";
import React from "react";

const ArrayFormControl = ({ field, initialValues, renderFormFields, addBtnLabelCode, subsequentTitleCode = null }) => {
  const { t } = useTranslation();

  const { control, getValues } = useFormContext();

  const { append, remove } = useFieldArray({
    control,
    name: field,
  });

  const addNewForm = () => {
    const newVal = { ...initialValues };
    newVal.key = Math.random();
    append(newVal);
  };

  const removeForm = (index) => {
    remove(index);
  };

  const renderAdditionalForm = (form, ind) => {
    if (ind === 0) {
      return null;
    } else {
      return (
        <BoxSpg key={ind}>
          <GridContainer spacing={1} mt={0}>
            <GridItem xs={11} sm={11} md={11.5}>
              {renderFormFields(ind, form.key)}
            </GridItem>
            <GridItem xs={1} sm={1} md={0.5}>
              <IconButton
                title={t("l.btn.remove")}
                color={"error"}
                style={{ position: "relative", top: 18 }}
                onClick={(e) => removeForm(ind)}
              >
                <FontAwesomeIcon style={{ fontSize: 20 }} icon={faXmark} />
              </IconButton>
            </GridItem>
          </GridContainer>
        </BoxSpg>
      );
    }
  };

  return (
    <>
      <BoxSpg>
        <GridContainer spacing={1} mt={0}>
          <GridItem xs={11} sm={11} md={11.5}>
            {renderFormFields(0, initialValues.key)}
          </GridItem>
        </GridContainer>
      </BoxSpg>
      <RelativeBox mt={2}>
        {subsequentTitleCode && getValues(field).length > 1 && (
          <Typography component={"span"} fontWeight={"bolder"}>
            {t(subsequentTitleCode)}
          </Typography>
        )}

        {getValues(field).map((form, ind) => renderAdditionalForm(form, ind))}

        <AddRecordButton labelCode={addBtnLabelCode} onClick={addNewForm} />
      </RelativeBox>
    </>
  );
};
export default ArrayFormControl;
