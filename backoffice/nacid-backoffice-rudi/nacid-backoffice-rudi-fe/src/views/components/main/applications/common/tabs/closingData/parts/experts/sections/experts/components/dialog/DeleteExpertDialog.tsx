import { useTranslation } from "react-i18next";
import React from "react";
import { ConfirmDialog, ReloadWatcherObject, useReloadWatcherWriter } from "@duosoftbg/nacid-components";
import { toast } from "react-toastify";
import useAppDispatch from "../../../../../../../../../../../../../hooks/redux/base/useAppDispatch";
import useAppSelector from "../../../../../../../../../../../../../hooks/redux/base/useAppSelector";
import { closeDeleteExpertModal } from "../../../../../../../../../../../../../store/redux/slice/ComponentsControl/applicationsControl";
import { deleteApplicationExpert } from "../../../../../../../../../../../../../axios/api/services";

const DeleteExpertDialog = () => {
  const dispatch = useAppDispatch();
  const { t } = useTranslation();
  const { updateReloadWatcher } = useReloadWatcherWriter();

  const { open, id } = useAppSelector((state) => {
    return state.ComponentsControl.applicationsControl.modals.deleteExpert;
  });

  const handleCloseDialog = () => {
    dispatch(closeDeleteExpertModal());
  };

  const onSuccess = (response) => {
    dispatch(closeDeleteExpertModal());
    toast.success(t("m.save.data.success"));
    updateReloadWatcher(ReloadWatcherObject.build("expertsData", "delete"));
  };

  return (
    <ConfirmDialog
      open={open}
      onClose={handleCloseDialog}
      promiseFunction={() => deleteApplicationExpert(id)}
      onSuccess={onSuccess}
      title={"t.delete.dialog"}
      message={"m.delete.warning.message"}
    />
  );
};

export default DeleteExpertDialog;
