import { isDate } from "date-fns";
import { parseDate } from "@duosoftbg/nacid-components";
import { overrideFiltersData } from "../store/redux/slice/SearchData/backofficeSearchTable";
import { store } from "../store/redux/store";
import { toast } from "react-toastify";

export const parseDateString = (value, originalValue) => {
  return isDate(originalValue) ? originalValue : parseDate(originalValue);
};

export const onDeleteTableEntrySuccess = (
  group,
  recordsLength,
  total,
  filterFn,
  dispatch,
  closeModal,
  getValues,
  setValue,
  t,
) => {
  const filtersData = store.getState().SearchData.backofficeSearchTable[group].filtersData;
  if (recordsLength === 1 && total > filtersData.pageSize) {
    setValue("page", filtersData.page - 1);
    dispatch(overrideFiltersData({ group, data: getValues() }));
  }
  filterFn();
  dispatch(closeModal({ modalType: "delete" }));
  toast.success(t("m.delete.data.success"));
};
