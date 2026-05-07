import UniExamPart from "./parts/uniExam/UniExamPart";
import ProgramExamPart from "./parts/programExam/ProgramExamPart";
import TrainingLocationExamPart from "./parts/locationExam/TrainingLocationExamPart";
import DiplomaExamPart from "./parts/diplomaExam/DiplomaExamPart";
import RemindersPart from "../../../sar/edit/tabs/statusData/parts/reminders/RemindersPart";
import { useTranslation } from "react-i18next";
import React from "react";
import { BoxSpg } from "@duosoftbg/nacid-components";
import { Box, Tab, Tabs, tabsClasses } from "@mui/material";
import StatusPart from "./parts/status/StatusPart";
import useRudiFormDirtyStateProcessor from "../../../../../../../store/redux/slice/ComponentsControl/useRudiFormDirtyStateProcessor";
import { FormDirtyStateProcessorFn } from "../../../../../../../config/functions/formDirtyStateProcessorFn";
import useAppSelector from "../../../../../../../hooks/redux/base/useAppSelector";

const tabProps = (key) => {
  return {
    id: `rudi-statusData-${key}`,
    "aria-controls": `rudi-statusData-tabpanel-${key}`,
  };
};

const partsInitializer = (appType) => {
  const parts = [
    {
      key: 0,
      title: "t.appSubSections.status",
      component: <StatusPart appType={appType} />,
    },
    {
      key: 1,
      title: "t.appSubSections.uniExam",
      component: <UniExamPart appType={appType} />,
    },
    {
      key: 2,
      title: "t.appSubSections.programExam",
      component: <ProgramExamPart />,
    },
    {
      key: 3,
      title: "t.appSubSections.trainingLocationExam",
      component: <TrainingLocationExamPart appType={appType} />,
    },
    {
      key: 4,
      title: "t.appSubSections.diplomaExam",
      component: <DiplomaExamPart appType={appType} />,
    },
    {
      key: 5,
      title: "t.appSubSections.reminders",
      component: <RemindersPart />,
    },
  ];
  return parts;
};

// todo - add check if current status is not initial
const StatusTabsData = ({ appType }) => {
  const { t } = useTranslation();
  const { activeTab } = useAppSelector((state) => {
    return state.ComponentsControl.applicationsControl.statusTab;
  });
  const parts = partsInitializer(appType);

  const executeChangeTabFn = useRudiFormDirtyStateProcessor();
  const handleChange = (event, newValue) => {
    executeChangeTabFn({
      proceedFnKey: FormDirtyStateProcessorFn.handleStatusTabChange.name,
      proceedFnParams: { activeTab: newValue },
    });
  };

  return (
    <>
      <BoxSpg>
        <Tabs
          variant="scrollable"
          scrollButtons="auto"
          allowScrollButtonsMobile
          value={activeTab}
          onChange={handleChange}
          sx={{
            [`& .${tabsClasses.scrollButtons}`]: {
              "&.Mui-disabled": { opacity: 0.3 },
            },
            "& .MuiButtonBase-root": { fontSize: 12 },
          }}
        >
          {parts.map((tab) => (
            <Tab wrapped key={tab.key} label={t(tab.title)} {...tabProps(tab.key)} />
          ))}
        </Tabs>
        <Box>
          {parts.map((tab) => (
            <div
              key={tab.key}
              role="tabpanel"
              hidden={activeTab !== tab.key}
              id={`rudi-statusData-tabpanel-${tab.key}`}
              aria-labelledby={`rudi-statusData-tab-${tab.key}`}
            >
              {activeTab === tab.key && tab.component}
            </div>
          ))}
        </Box>
      </BoxSpg>
    </>
  );
};
export default StatusTabsData;
