import { FormDirtyStateControlActions, ViewDialog } from "@duosoftbg/nacid-components";
import useAppSelector from "../../../../../../../../hooks/redux/base/useAppSelector";
import useAppDispatch from "../../../../../../../../hooks/redux/base/useAppDispatch";
import { closeAddApplicationDialog } from "../../../../../../../../store/redux/slice/ComponentsControl/commissionCalendarControl";
import CalendarDiplomaRecognition from "./CalendarDiplomaRecognition";
import { useStore } from "react-redux";
import { removeAll } from "../../../../../../../../store/redux/slice/ComponentsControl/selectedIdsControl";
import type { RootState } from "../../../../../../../../store/redux/store";

const AddApplicationsDialog = ({ setApplicationIds }) => {
  const dispatch = useAppDispatch();
  const store = useStore();

  const { open, excludedApplications } = useAppSelector((state) => {
    return state.ComponentsControl.commissionCalendarControl.modals.addApplication;
  });

  const handleCloseDialog = () => {
    dispatch(closeAddApplicationDialog());
    dispatch(removeAll());
  };

  const onSubmit = () => {
    const state = store.getState() as RootState;
    const newIds = state.ComponentsControl.selectedIdsControl.newIds;
    setApplicationIds((applicationIds) => [...applicationIds, ...newIds]);
    dispatch(FormDirtyStateControlActions.setDirty({ dirty: true }));
    handleCloseDialog();
  };

  return (
    <ViewDialog
      open={open}
      onClose={handleCloseDialog}
      title={"t.commission.calendar.add.application"}
      disableEnforceFocus
      dialogActionsSpacing={{ pr: 3 }}
      onSubmitBtnClick={() => {
        onSubmit();
      }}
    >
      <CalendarDiplomaRecognition excludedApplications={excludedApplications}></CalendarDiplomaRecognition>
    </ViewDialog>
  );
};

export default AddApplicationsDialog;
