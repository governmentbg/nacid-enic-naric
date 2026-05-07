import * as React from "react";
import useAppSelector from "../../../../../../../hooks/redux/base/useAppSelector";
import { store } from "../../../../../../../store/redux/store";
import { NomenclatureAutocompleteFilter } from "@duosoftbg/nacid-components";
import { profGroupsThunk } from "../../../../../../../store/redux/slice/AppData/profGroups";
const ProfGroupFilter = ({ xs = 12, sm = 6, md = 3, group }) => {
  const thunkState = useAppSelector((state) => {
    return state.AppData.profGroups;
  });

  const getPreviousStateOptionFn = () => {
    const professionGroupCode = store.getState().SearchData.backofficeSearchTable[group].filtersData.profGroup;
    const options = store.getState().AppData.profGroups.data;
    return options.find((element) => element.id === professionGroupCode);
  };

  return (
    <NomenclatureAutocompleteFilter
      filter={"profGroup"}
      thunkFn={profGroupsThunk}
      thunkState={thunkState}
      getPreviousStateOptionFn={getPreviousStateOptionFn}
      xs={xs}
      sm={sm}
      md={md}
    />
  );
};

export default ProfGroupFilter;
