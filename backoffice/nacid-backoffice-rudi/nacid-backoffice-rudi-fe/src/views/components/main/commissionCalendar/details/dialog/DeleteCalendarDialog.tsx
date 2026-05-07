import { useTranslation } from "react-i18next";
import React from "react";
import { ConfirmDialog } from "@duosoftbg/nacid-components";
import { toast } from "react-toastify";
import useAppDispatch from "../../../../../../hooks/redux/base/useAppDispatch";
import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import { closeDeleteCalendarModal } from "../../../../../../store/redux/slice/ComponentsControl/commissionCalendarControl";
import { deleteCalendar } from "../../../../../../axios/api/services";

const DeleteCalendarDialog = ({ filterFn }) => {
  const dispatch = useAppDispatch();
  const { t } = useTranslation();

  const { open, id } = useAppSelector((state) => {
    return state.ComponentsControl.commissionCalendarControl.modals.deleteCalendar;
  });

  const handleCloseDialog = () => {
    dispatch(closeDeleteCalendarModal());
  };

  const onSuccess = (response) => {
    filterFn();
    dispatch(closeDeleteCalendarModal());
    toast.success(t("m.save.data.success"));
  };

  return (
    <ConfirmDialog
      open={open}
      onClose={handleCloseDialog}
      promiseFunction={() => deleteCalendar(id)}
      onSuccess={onSuccess}
      title={"t.delete.dialog"}
      message={"m.delete.warning.message"}
    />
  );
};

export default DeleteCalendarDialog;
