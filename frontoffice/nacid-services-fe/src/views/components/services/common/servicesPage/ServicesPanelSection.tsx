import { Typography } from "@mui/material";
import ServicesSectionLinks from "./ServicesSectionLinks";
import React from "react";
import { useTranslation } from "react-i18next";
import { NacidIconDivider } from "@duosoftbg/nacid-components";

const ServicesPanelSection = ({ section }) => {
  const { t } = useTranslation();

  return (
    <>
      <NacidIconDivider />
      <Typography sx={{ fontSize: 18 }} gutterBottom align={"center"} color={"text.secondary"}>
        {t(section.titleCode)}
      </Typography>
      <ServicesSectionLinks services={section.services} />
    </>
  );
};
export default ServicesPanelSection;
