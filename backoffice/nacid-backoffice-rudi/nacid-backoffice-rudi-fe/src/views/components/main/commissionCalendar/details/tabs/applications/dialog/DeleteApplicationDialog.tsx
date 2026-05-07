import React from "react";
import { FormDirtyStateControlActions, SimpleConfirmDialog } from "@duosoftbg/nacid-components";
import useAppDispatch from "../../../../../../../../hooks/redux/base/useAppDispatch";
import useAppSelector from "../../../../../../../../hooks/redux/base/useAppSelector";
import { closeDeleteApplicationModal } from "../../../../../../../../store/redux/slice/ComponentsControl/commissionCalendarControl";

const DeleteApplicationDialog = ({ setApplicationIds, applicationIds }) => {
  const dispatch = useAppDispatch();

  const { open, id } = useAppSelector((state) => {
    return state.ComponentsControl.commissionCalendarControl.modals.deleteApplication;
  });

  const handleCloseDialog = () => {
    dispatch(closeDeleteApplicationModal());
  };

  const confirm = () => {
    const applicationIdsAfterRemove = applicationIds.filter((existingId) => id !== existingId);
    setApplicationIds([...applicationIdsAfterRemove]);
    dispatch(FormDirtyStateControlActions.setDirty({ dirty: true }));
    handleCloseDialog();
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

export default DeleteApplicationDialog;
