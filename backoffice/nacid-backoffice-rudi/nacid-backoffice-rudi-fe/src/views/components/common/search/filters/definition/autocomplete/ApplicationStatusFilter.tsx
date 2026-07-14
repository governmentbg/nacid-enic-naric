import * as React from "react";
import useAppSelector from "../../../../../../../hooks/redux/base/useAppSelector";
import { store } from "../../../../../../../store/redux/store";
import { NomenclatureAutocompleteFilter } from "@duosoftbg/nacid-components";
import { applicationStatusesThunk } from "../../../../../../../store/redux/slice/AppData/applicationStatuses";

const ApplicationStatusFilter = ({ xs = 12, sm = 4, md = 4, group }) => {
  const thunkState = useAppSelector((state) => {
    return state.AppData.applicationStatuses;
  });

  const getPreviousStateOptionFn = () => {
    const statusCode = store.getState().SearchData.backofficeSearchTable[group].filtersData.apnStatusCode;
    const options = store.getState().AppData.applicationStatuses.data;
    return options.find((element) => element.id === statusCode);
  };

  return (
    <NomenclatureAutocompleteFilter
      filter={"apnStatusCode"}
      thunkFn={applicationStatusesThunk}
      thunkState={thunkState}
      getPreviousStateOptionFn={getPreviousStateOptionFn}
      xs={xs}
      sm={sm}
      md={md}
    />
  );
};

export default ApplicationStatusFilter;
