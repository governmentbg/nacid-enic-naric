import { ViewDialog } from "@duosoftbg/nacid-components";
import React from "react";
import { useDispatch } from "react-redux";
import {
  LibraryComponentsControlActions,
  TrainingInstitutionEditContent,
} from "@duosoftbg/nacid-backoffice-components";
import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";

const SaveTrainingInstitutionDialog = ({ tempFormDataKey = "STID", resetExternalField = false }) => {
  const dispatch = useDispatch();

  const { open, id, fieldId, universities } = useAppSelector((state) => {
    return state.LibraryComponentsControl.trainingInstitutionControl.modals.edit;
  });

  const handleCloseDialog = () => {
    dispatch(LibraryComponentsControlActions.trainingInstitutionControlActions.closeModal({ modalType: "edit" }));
  };

  return (
    <ViewDialog
      open={open}
      onClose={handleCloseDialog}
      title={id === "" ? "t.nomenclature.create.dialog" : id && "t.nomenclature.edit.dialog"}
      disableEnforceFocus
      dialogActionsSpacing={{ pr: 3 }}
    >
      <TrainingInstitutionEditContent
        id={id}
        universities={universities}
        handleCloseDialog={handleCloseDialog}
        tempFormDataKey={tempFormDataKey}
        resetExternalField={resetExternalField}
        fieldId={fieldId}
      />
    </ViewDialog>
  );
};

export default SaveTrainingInstitutionDialog;
