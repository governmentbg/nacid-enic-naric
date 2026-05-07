import { BoxSpg, TextButton } from "@duosoftbg/nacid-components";
import { NavLink } from "react-router-dom";
import React from "react";
import { useTranslation } from "react-i18next";
import { Typography } from "@mui/material";

const ServicesSectionLinks = ({ services }) => {
  const { t } = useTranslation();

  const renderLinkButton = (listEntry) => {
    return (
      <TextButton size={"small"} disableRipple color="primary" sx={{ textAlign: "left" }}>
        <Typography fontSize={14} ml={2}>
          {t(listEntry.titleCode)}
        </Typography>
      </TextButton>
    );
  };

  const renderLink = (listEntry) => {
    if (listEntry.external) {
      return (
        <a href={listEntry.baseHref} target={"_blank"} rel="noreferrer">
          {renderLinkButton(listEntry)}
        </a>
      );
    } else {
      return <NavLink to={listEntry.baseHref}>{renderLinkButton(listEntry)}</NavLink>;
    }
  };

  return (
    <>
      {services.map((listEntry) => (
        <BoxSpg key={listEntry.titleCode}>{renderLink(listEntry)}</BoxSpg>
      ))}
    </>
  );
};

export default ServicesSectionLinks;
