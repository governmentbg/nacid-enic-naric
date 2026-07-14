import { CardContent } from "@mui/material";
import React from "react";
import ServicesPanelSection from "./ServicesPanelSection";
import { CardSpg } from "@duosoftbg/nacid-components";

const ServicesPanel = ({ config }) => {
  return (
    <>
      {config.sections.map((section, index) => (
        <CardSpg key={index} mb={4}>
          <CardContent>
            <ServicesPanelSection key={section.titleCode} section={section} />
          </CardContent>
        </CardSpg>
      ))}
    </>
  );
};

export default ServicesPanel;
