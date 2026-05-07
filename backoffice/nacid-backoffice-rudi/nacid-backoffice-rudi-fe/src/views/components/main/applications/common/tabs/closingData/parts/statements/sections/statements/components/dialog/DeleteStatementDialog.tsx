import { useTranslation } from "react-i18next";
import React from "react";
import { ConfirmDialog, ReloadWatcherObject, useReloadWatcherWriter } from "@duosoftbg/nacid-components";
import { toast } from "react-toastify";
import useAppDispatch from "../../../../../../../../../../../../../hooks/redux/base/useAppDispatch";
import useAppSelector from "../../../../../../../../../../../../../hooks/redux/base/useAppSelector";
import { closeDeleteStatementModal } from "../../../../../../../../../../../../../store/redux/slice/ComponentsControl/applicationsControl";
import { deleteApplicationExpertStatement } from "../../../../../../../../../../../../../axios/api/services";

const DeleteStatementDialog = () => {
  const dispatch = useAppDispatch();
  const { t } = useTranslation();
  const { updateReloadWatcher } = useReloadWatcherWriter();

  const { open, id } = useAppSelector((state) => {
    return state.ComponentsControl.applicationsControl.modals.deleteStatement;
  });

  const handleCloseDialog = () => {
    dispatch(closeDeleteStatementModal());
  };

  const onSuccess = (response) => {
    dispatch(closeDeleteStatementModal());
    toast.success(t("m.save.data.success"));
    updateReloadWatcher(ReloadWatcherObject.build("statementsData", "delete"));
  };

  return (
    <ConfirmDialog
      open={open}
      onClose={handleCloseDialog}
      promiseFunction={() => deleteApplicationExpertStatement(id)}
      onSuccess={onSuccess}
      title={"t.delete.dialog"}
      message={"m.delete.warning.message"}
    />
  );
};

export default DeleteStatementDialog;
