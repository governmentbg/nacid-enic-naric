import * as React from "react";
import { Box, CardContent, Tab, Tabs } from "@mui/material";
import { CardSpg, DividerSpg } from "@duosoftbg/nacid-components";
import { useTranslation } from "react-i18next";
import styled from "styled-components";
import CommonInformation from "./details/tabs/commonInformation/CommonInformation";
import Applications from "./details/tabs/applications/Applications";
import Members from "./details/tabs/members/Members";
import Processing from "./details/tabs/processing/Processing";
import { useParams } from "react-router-dom";
import useAppSelector from "../../../../hooks/redux/base/useAppSelector";
import { FormDirtyStateProcessorFn } from "../../../../config/functions/formDirtyStateProcessorFn";
import useRudiFormDirtyStateProcessor from "../../../../store/redux/slice/ComponentsControl/useRudiFormDirtyStateProcessor";
const TabDivider = styled(DividerSpg)`
  border-width: 1px;
  margin-top: -2px;
`;

function a11yProps(key) {
  return {
    id: `commission-calendar-modification-section-${key}`,
    "aria-controls": `commission-calendar-modification-section-tabpanel-${key}`,
  };
}

const tabs = [
  {
    key: 0,
    title: "t.commonInformation",
    component: <CommonInformation />,
    disabled: false,
  },
  {
    key: 1,
    title: "t.applications",
    component: <Applications />,
    disabled: true,
  },
  {
    key: 2,
    title: "t.commission.members",
    component: <Members />,
    disabled: true,
  },
  {
    key: 3,
    title: "t.commission.calendar.processing",
    component: <Processing />,
    disabled: true,
  },
];

const CommissionCalendarEdit = () => {
  const { t } = useTranslation();
  const { activeTab } = useAppSelector((state) => {
    return state.ComponentsControl.commissionCalendarControl.editPageTab;
  });
  const executeChangeTabFn = useRudiFormDirtyStateProcessor();
  const calendarId = useParams().calendarId;

  const handleChange = (event, newValue) => {
    executeChangeTabFn({
      proceedFnKey: FormDirtyStateProcessorFn.handleTabChangeCommissionCalendar.name,
      proceedFnParams: { activeTab: newValue },
    });
  };

  return (
    <>
      <CardSpg my={4} style={{ overflow: "visible" }}>
        <CardContent style={{ position: "relative" }}>
          <Tabs style={{ marginTop: "-15px" }} variant="fullWidth" centered value={activeTab} onChange={handleChange}>
            {tabs.map((tab) => (
              <Tab
                onClick={() => {}}
                disabled={!calendarId ? tab.disabled : false}
                key={tab.key}
                label={t(tab.title)}
                {...a11yProps(tab.key)}
              />
            ))}
          </Tabs>
          <Box>
            <TabDivider />
            {tabs.map((tab) => (
              <div
                key={tab.key}
                role="tabpanel"
                hidden={activeTab !== tab.key}
                id={`commission-calendar-modification-section-tabpanel-${tab.key}`}
                aria-labelledby={`commission-calendar-modification-section-tab-${tab.key}`}
              >
                {activeTab === tab.key && tab.component}
              </div>
            ))}
          </Box>
        </CardContent>
      </CardSpg>
    </>
  );
};

export default CommissionCalendarEdit;
