import React from "react";
import { FormDirtyStateControlActions, SimpleConfirmDialog } from "@duosoftbg/nacid-components";
import useAppDispatch from "../../../../../../../../hooks/redux/base/useAppDispatch";
import useAppSelector from "../../../../../../../../hooks/redux/base/useAppSelector";
import { closeDeleteMemberModal } from "../../../../../../../../store/redux/slice/ComponentsControl/commissionCalendarControl";

const DeleteMemberDialog = ({ setMembers, members }) => {
  const dispatch = useAppDispatch();

  const { open, id } = useAppSelector((state) => {
    return state.ComponentsControl.commissionCalendarControl.modals.deleteMember;
  });

  const handleCloseDialog = () => {
    dispatch(closeDeleteMemberModal());
  };

  const confirm = () => {
    const membersAfterRemove = members.filter((obj) => id !== obj.member.id);
    setMembers([...membersAfterRemove]);
    handleCloseDialog();
    dispatch(FormDirtyStateControlActions.setDirty({ dirty: true }));
  };

  return (
    <SimpleConfirmDialog
      open={open}
      setOpen={handleCloseDialog}
      dialogTitleText={"t.delete.dialog"}
      alertText={"m.delete.warning.message"}
      alertType={"warning"}
      onConfirm={confirm}
    ></SimpleConfirmDialog>
  );
};

export default DeleteMemberDialog;
