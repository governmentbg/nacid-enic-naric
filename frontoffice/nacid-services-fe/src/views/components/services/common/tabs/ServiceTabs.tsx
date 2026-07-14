import * as React from "react";
import Box from "@mui/material/Box";
import Tabs from "@mui/material/Tabs";
import Tab from "@mui/material/Tab";
import ServiceTabPanel from "./ServiceTabPanel";
import { useTranslation } from "react-i18next";

function a11yProps(index) {
  return {
    id: `service-tab-${index}`,
    "aria-controls": `service-tabpanel-${index}`,
  };
}

const ServiceTabs = ({ descriptionComponent, applicationsComponent, activeTab, onActiveTabChange }) => {
  const { t } = useTranslation();

  const tabList = [
    {
      index: 0,
      title: "t.service.tab.description",
      component: descriptionComponent,
    },
    {
      index: 1,
      title: "t.service.tab.applications",
      component: applicationsComponent,
    },
  ];

  const handleChange = (event, newValue) => {
    onActiveTabChange(newValue);
  };

  return (
    <Box sx={{ width: "100%" }}>
      <Box>
        <Tabs value={activeTab} onChange={handleChange}>
          {tabList.map((tab) => (
            <Tab key={tab.index} label={t(tab.title)} {...a11yProps(tab.index)} />
          ))}
        </Tabs>
      </Box>
      {tabList.map((tab) => (
        <ServiceTabPanel key={tab.index} value={activeTab} index={tab.index}>
          {tab.component}
        </ServiceTabPanel>
      ))}
    </Box>
  );
};

export default ServiceTabs;
