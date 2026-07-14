import { ViewDialog } from "@duosoftbg/nacid-components";
import useAppDispatch from "../../../../../../../../../../../hooks/redux/base/useAppDispatch";
import useAppSelector from "../../../../../../../../../../../hooks/redux/base/useAppSelector";
import { UniversityControlActions } from "../../../../../../../../../../../store/redux/slice/ComponentsControl/baseUniversityControl";
import SearchUniversityDialogContent from "./SearchUniversityDialogContent";

const SearchUniversityDialog = () => {
  const dispatch = useAppDispatch();

  const { open, universityIdPointer, tempDataKey } = useAppSelector((state) => {
    return state.ComponentsControl.universityControl.modals.search;
  });

  const handleClose = () => {
    dispatch(UniversityControlActions.closeSearchUniversityModal({}));
  };

  if (!open) {
    return null;
  }

  return (
    <ViewDialog
      open={open}
      onClose={handleClose}
      title={"t.universitySearch"}
      disableEnforceFocus
      dialogActionsSpacing={{ pr: 3 }}
    >
      <SearchUniversityDialogContent universityIdPointer={universityIdPointer} tempDataKey={tempDataKey} />
    </ViewDialog>
  );
};

export default SearchUniversityDialog;
