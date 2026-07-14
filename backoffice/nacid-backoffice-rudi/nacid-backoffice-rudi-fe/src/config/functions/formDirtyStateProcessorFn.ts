import { tabChange } from "../../store/redux/slice/ComponentsControl/commissionCalendarControl";
import { removeAll } from "../../store/redux/slice/ComponentsControl/selectedIdsControl";
import { UdirecAppControlActions } from "../../store/redux/slice/ComponentsControl/udirecApplicationsControl";
import { expertTabChange, statusTabChange } from "../../store/redux/slice/ComponentsControl/applicationsControl";
import { DocrecAppControlActions } from "../../store/redux/slice/ComponentsControl/docrecApplicationsControl";
import { SarAppControlActions } from "../../store/redux/slice/ComponentsControl/sarApplicationsControl";

export const FormDirtyStateProcessorFn = {
  handleTabChangeCommissionCalendar: ({ activeTab }, dispatch) => {
    dispatch(tabChange({ activeTab: activeTab }));
    dispatch(removeAll());
  },
  handleTabChangeUdirec: ({ activeTab }, dispatch) => {
    dispatch(UdirecAppControlActions.tabChange({ activeTab: activeTab }));
  },
  handleTabChangeDocrec: ({ activeTab }, dispatch) => {
    dispatch(DocrecAppControlActions.tabChange({ activeTab: activeTab }));
  },
  handleTabChangeSAR: ({ activeTab }, dispatch) => {
    dispatch(SarAppControlActions.tabChange({ activeTab: activeTab }));
  },
  handleStatusTabChange: ({ activeTab }, dispatch) => {
    dispatch(statusTabChange({ activeTab: activeTab }));
  },

  handleExpertTabChange: ({ activeTab }, dispatch) => {
    dispatch(expertTabChange({ activeTab: activeTab }));
  },
};
