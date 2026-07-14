import * as React from "react";
import useAppSelector from "../../../../../../../hooks/redux/base/useAppSelector";
import { store } from "../../../../../../../store/redux/store";
import { NomenclatureAutocompleteFilter } from "@duosoftbg/nacid-components";
import { countriesThunk } from "@duosoftbg/nacid-backoffice-components";

const CountryFilter = ({ xs = 12, sm = 6, md = 4, group, fieldName = "country", label = undefined }) => {
  const thunkState = useAppSelector((state) => {
    return state.ThunkData.countries;
  });

  const getPreviousStateOptionFn = () => {
    const countryCode = store.getState().SearchData.backofficeSearchTable[group].filtersData[fieldName];
    const options = store.getState().ThunkData.countries.data;
    return options.find((element) => element.id === countryCode);
  };

  return (
    <NomenclatureAutocompleteFilter
      filter={fieldName}
      label={label}
      thunkFn={countriesThunk}
      thunkState={thunkState}
      getPreviousStateOptionFn={getPreviousStateOptionFn}
      xs={xs}
      sm={sm}
      md={md}
    />
  );
};

export default CountryFilter;
