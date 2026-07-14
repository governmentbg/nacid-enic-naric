import { ViewDialog } from "@duosoftbg/nacid-components";
import useAppDispatch from "../../../../../../../../../../../hooks/redux/base/useAppDispatch";
import useAppSelector from "../../../../../../../../../../../hooks/redux/base/useAppSelector";
import React from "react";
import { UniversityControlActions } from "../../../../../../../../../../../store/redux/slice/ComponentsControl/baseUniversityControl";
import UniversityForm from "./UniversityForm";

const EditUniversityDialog = () => {
  const dispatch = useAppDispatch();

  const { open, universityId, universityIdPointer, tempDataKey } = useAppSelector((state) => {
    return state.ComponentsControl.universityControl.modals.edit;
  });

  const handleClose = () => {
    dispatch(UniversityControlActions.closeEditUniversityModal({}));
  };

  if (!open) {
    return null;
  }

  return (
    <ViewDialog
      open={open}
      onClose={handleClose}
      title={"l.editUniversityDialog.title"}
      disableEnforceFocus
      dialogActionsSpacing={{ pr: 3 }}
    >
      <UniversityForm
        universityId={universityId}
        universityIdPointer={universityIdPointer}
        universityTDK={tempDataKey}
      />
    </ViewDialog>
  );
};

export default EditUniversityDialog;
