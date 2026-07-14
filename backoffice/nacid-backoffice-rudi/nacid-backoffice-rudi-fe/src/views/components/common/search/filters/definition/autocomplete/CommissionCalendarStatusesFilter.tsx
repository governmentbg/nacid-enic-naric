import * as React from "react";
import useAppSelector from "../../../../../../../hooks/redux/base/useAppSelector";
import { store } from "../../../../../../../store/redux/store";
import { NomenclatureAutocompleteFilter } from "@duosoftbg/nacid-components";
import { commissionCalendarStatusesThunk } from "../../../../../../../store/redux/slice/AppData/commissionCalendarStatuses";

const CommissionCalendarStatusesFilter = ({ xs = 12, sm = 3, md = 3, group }) => {
  const thunkState = useAppSelector((state) => {
    return state.AppData.commissionCalendarStatuses;
  });

  const getPreviousStateOptionFn = () => {
    const sessionStatusCode = store.getState().SearchData.backofficeSearchTable[group].filtersData.sessionStatusCode;
    const options = store.getState().AppData.commissionCalendarStatuses.data;
    return options.find((element) => element.id === sessionStatusCode);
  };

  return (
    <NomenclatureAutocompleteFilter
      filter={"sessionStatusCode"}
      thunkFn={commissionCalendarStatusesThunk}
      thunkState={thunkState}
      getPreviousStateOptionFn={getPreviousStateOptionFn}
      xs={xs}
      sm={sm}
      md={md}
    />
  );
};

export default CommissionCalendarStatusesFilter;
