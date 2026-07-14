// TODO: NACIDSE-16
import { TextButton } from "@duosoftbg/nacid-components";
import { Add } from "@mui/icons-material";
import { Typography } from "@mui/material";
import React from "react";
import { useTranslation } from "react-i18next";

const AddRecordButton = ({ labelCode, onClick, mt = 2 }) => {
  const { t } = useTranslation();

  return (
    <Typography mr={4} mt={mt}>
      <TextButton size={"small"} disableRipple startIcon={<Add />} color="primary" onClick={onClick}>
        {t(labelCode)}
      </TextButton>
    </Typography>
  );
};

export default AddRecordButton;
