import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import {
  fulfilledThunkState,
  initialThunkState,
  pendingThunkState,
  rejectedThunkState,
  commonThunkCondition,
} from "@duosoftbg/nacid-components";
import { getAllCountries } from "../../../../services/coreServicesCalls";

const sliceName = "appData/countriesData";
const countriesDataSlice = createSlice({
  name: sliceName,
  initialState: initialThunkState([]),
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(countriesDataThunk.pending, (state, action) => {
      return pendingThunkState(state);
    });
    builder.addCase(countriesDataThunk.fulfilled, (state, action) => {
      const { data } = action.payload;
      return fulfilledThunkState(data);
    });
    builder.addCase(countriesDataThunk.rejected, (state, action) => {
      return rejectedThunkState([]);
    });
  },
});

export const countriesDataThunk = createAsyncThunk(
  `${sliceName}/countriesDataThunk`,
  async () => {
    const response = await getAllCountries()();
    return { data: response };
  },
  {
    condition: (_, { getState, extra }) => {
      return commonThunkCondition(getState()["AppData"]?.CountriesData);
    },
  }
);

export default countriesDataSlice.reducer;
