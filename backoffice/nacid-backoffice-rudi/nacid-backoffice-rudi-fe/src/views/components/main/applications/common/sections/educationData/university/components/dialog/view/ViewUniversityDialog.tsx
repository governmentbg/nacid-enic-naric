import { ViewDialog } from "@duosoftbg/nacid-components";
import useAppDispatch from "../../../../../../../../../../../hooks/redux/base/useAppDispatch";
import useAppSelector from "../../../../../../../../../../../hooks/redux/base/useAppSelector";
import React from "react";
import { UniversityControlActions } from "../../../../../../../../../../../store/redux/slice/ComponentsControl/baseUniversityControl";
import { UniversityViewContent } from "@duosoftbg/nacid-backoffice-components";

const ViewUniversityDialog = () => {
  const dispatch = useAppDispatch();

  const { open, universityId } = useAppSelector((state) => {
    return state.ComponentsControl.universityControl.modals.view;
  });

  const handleClose = () => {
    dispatch(UniversityControlActions.closeViewUniversityModal({}));
  };

  if (!open) {
    return null;
  }

  return (
    <ViewDialog
      open={open}
      onClose={handleClose}
      title={"l.viewUniversityDialog.title"}
      disableEnforceFocus
      dialogActionsSpacing={{ pr: 3 }}
    >
      <UniversityViewContent
        universityId={universityId}
        showAllFields={true}
        withViewSections={true}
        loaderType={"skeleton"}
      />
    </ViewDialog>
  );
};

export default ViewUniversityDialog;
