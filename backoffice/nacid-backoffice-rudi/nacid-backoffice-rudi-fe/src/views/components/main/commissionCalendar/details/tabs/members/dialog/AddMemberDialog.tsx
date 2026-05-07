import {
  AsyncCallArgs,
  FormDirtyStateControlActions,
  isArrayNotEmpty,
  useAsyncCall,
  ViewDialog,
} from "@duosoftbg/nacid-components";
import useAppSelector from "../../../../../../../../hooks/redux/base/useAppSelector";
import useAppDispatch from "../../../../../../../../hooks/redux/base/useAppDispatch";
import { closeAddMemberDialog } from "../../../../../../../../store/redux/slice/ComponentsControl/commissionCalendarControl";
import { useStore } from "react-redux";
import CalendarMembers from "./CalendarMembers";
import { getCommissionMembersByIds } from "../../../../../../../../axios/api/services";
import { removeAll } from "../../../../../../../../store/redux/slice/ComponentsControl/selectedIdsControl";
import type { RootState } from "../../../../../../../../store/redux/store";

const AddMemberDialog = ({ setMembers }) => {
  const dispatch = useAppDispatch();
  const store = useStore();
  const { asyncCall } = useAsyncCall();

  const { open, excludedMembers } = useAppSelector((state) => {
    return state.ComponentsControl.commissionCalendarControl.modals.addMember;
  });

  const handleCloseDialog = () => {
    dispatch(closeAddMemberDialog());
    dispatch(removeAll());
  };

  const onSubmit = () => {
    const state = store.getState() as RootState;
    const newIds = state.ComponentsControl.selectedIdsControl.newIds;
    if (isArrayNotEmpty(newIds)) {
      const asyncCallArgs: AsyncCallArgs = {
        promise: getCommissionMembersByIds(newIds),
        withGlobalBackdrop: true,
        onSuccess: (response) => {
          setMembers((members) => [...members, ...response]);
          handleCloseDialog();
        },
      };
      asyncCall(asyncCallArgs);
    } else {
      handleCloseDialog();
    }
    dispatch(FormDirtyStateControlActions.setDirty({ dirty: true }));
  };

  return (
    <ViewDialog
      open={open}
      onClose={handleCloseDialog}
      title={"t.commission.calendar.add.member"}
      disableEnforceFocus
      dialogActionsSpacing={{ pr: 3 }}
      onSubmitBtnClick={() => {
        onSubmit();
      }}
    >
      <CalendarMembers excludedMembers={excludedMembers}></CalendarMembers>
    </ViewDialog>
  );
};

export default AddMemberDialog;
