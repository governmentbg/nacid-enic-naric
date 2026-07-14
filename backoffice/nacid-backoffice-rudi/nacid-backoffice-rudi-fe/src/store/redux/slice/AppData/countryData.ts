// TODO: NACIDSE-16
import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import {
  fulfilledThunkState,
  initialThunkState,
  pendingThunkState,
  rejectedThunkState,
  commonThunkCondition,
} from "@duosoftbg/nacid-components";
import { getCountryData } from "../../../../axios/api/services";

const sliceName = "appData/countryData";
const countryDataSlice = createSlice({
  name: sliceName,
  initialState: initialThunkState([]),
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(countryDataThunk.pending, (state, action) => {
      return pendingThunkState(state);
    });
    builder.addCase(countryDataThunk.fulfilled, (state, action) => {
      const { data } = action.payload;
      return fulfilledThunkState(data);
    });
    builder.addCase(countryDataThunk.rejected, (state, action) => {
      return rejectedThunkState([]);
    });
  },
});

export const countryDataThunk = createAsyncThunk(
  `${sliceName}/countryDataThunk`,
  async () => {
    const response = await getCountryData()();
    return {
      data: response.map((c) => ({
        id: c.code,
        name: c.nameBg,
      })),
    };
  },
  {
    condition: (_, { getState, extra }) => {
      return commonThunkCondition(getState()["AppData"]?.countryData);
    },
  },
);

export default countryDataSlice.reducer;
