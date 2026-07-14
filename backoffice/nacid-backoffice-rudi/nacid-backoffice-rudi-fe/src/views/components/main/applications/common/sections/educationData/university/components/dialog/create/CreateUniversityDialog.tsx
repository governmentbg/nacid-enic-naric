import { ViewDialog } from "@duosoftbg/nacid-components";
import useAppDispatch from "../../../../../../../../../../../hooks/redux/base/useAppDispatch";
import useAppSelector from "../../../../../../../../../../../hooks/redux/base/useAppSelector";
import React from "react";
import { UniversityControlActions } from "../../../../../../../../../../../store/redux/slice/ComponentsControl/baseUniversityControl";
import UniversityForm from "./UniversityForm";

const CreateUniversityDialog = () => {
  const dispatch = useAppDispatch();

  const { open, universityIdPointer, tempDataKey, initialData } = useAppSelector((state) => {
    return state.ComponentsControl.universityControl.modals.create;
  });

  const handleClose = () => {
    dispatch(UniversityControlActions.closeCreateUniversityModal({}));
  };

  if (!open) {
    return null;
  }

  return (
    <ViewDialog
      open={open}
      onClose={handleClose}
      title={"l.createUniversityDialog.title"}
      disableEnforceFocus
      dialogActionsSpacing={{ pr: 3 }}
    >
      <UniversityForm
        universityId={""}
        universityIdPointer={universityIdPointer}
        universityTDK={tempDataKey}
        initialData={initialData}
      />
    </ViewDialog>
  );
};

export default CreateUniversityDialog;
