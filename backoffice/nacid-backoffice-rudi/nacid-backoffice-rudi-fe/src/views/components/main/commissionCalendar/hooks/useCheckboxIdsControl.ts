import { isArrayEmpty } from "@duosoftbg/nacid-components";
import useAppDispatch from "../../../../../hooks/redux/base/useAppDispatch";
import useAppSelector from "../../../../../hooks/redux/base/useAppSelector";
import {
  addNewId,
  removeId,
  removeIds,
  addNewIds,
} from "../../../../../store/redux/slice/ComponentsControl/selectedIdsControl";

const useCheckboxIdsControl = ({ records }) => {
  const { newIds } = useAppSelector((state) => {
    return state.ComponentsControl.selectedIdsControl;
  });
  const dispatch = useAppDispatch();

  const handleSingleIdToggle = (event, id) => {
    const isChecked = event.target.checked;
    const payload = { newId: id };
    if (isChecked) {
      dispatch(addNewId(payload));
    } else {
      dispatch(removeId(payload));
    }
  };
  const handleMultipleIdsToggle = (event) => {
    const isChecked = event.target.checked;
    const payload = { newIds: records.map((r) => r.id) };
    if (isChecked) {
      dispatch(addNewIds(payload));
    } else {
      dispatch(removeIds(payload));
    }
  };

  const isSingleAddChecked = (id) => {
    return newIds.indexOf(id) !== -1;
  };
  const isMultipleAddChecked = () => {
    if (isArrayEmpty(records) || isArrayEmpty(newIds)) {
      return false;
    }
    let isChecked = true;

    const ids = records.map((r) => r.id);
    ids.forEach((id) => {
      if (newIds.indexOf(id) === -1) {
        isChecked = false;
      }
    });
    return isChecked;
  };

  return {
    handleSingleIdToggle,
    handleMultipleIdsToggle,
    isSingleAddChecked,
    isMultipleAddChecked,
  };
};

export default useCheckboxIdsControl;
