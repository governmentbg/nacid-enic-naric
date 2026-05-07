import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import {
  fulfilledThunkState,
  initialThunkState,
  pendingThunkState,
  rejectedThunkState,
  commonThunkCondition,
} from "@duosoftbg/nacid-components";
import { getCfgServiceTypes } from "../../../../services/coreServicesCalls";

const sliceName = "appData/cfgServiceType";
const cfgServiceTypeSlice = createSlice({
  name: sliceName,
  initialState: initialThunkState([]),
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(cfgServiceTypeThunk.pending, (state, action) => {
      return pendingThunkState(state);
    });
    builder.addCase(cfgServiceTypeThunk.fulfilled, (state, action) => {
      const { data } = action.payload;
      return fulfilledThunkState(data);
    });
    builder.addCase(cfgServiceTypeThunk.rejected, (state, action) => {
      return rejectedThunkState([]);
    });
  },
});

export const cfgServiceTypeThunk = createAsyncThunk(
  `${sliceName}/cfgServiceTypeThunk`,
  async () => {
    const response = await getCfgServiceTypes()();
    return response;
  },
  {
    condition: (_, { getState, extra }) => {
      return commonThunkCondition(getState()["AppData"]?.CfgServiceType);
    },
  }
);

export default cfgServiceTypeSlice.reducer;
