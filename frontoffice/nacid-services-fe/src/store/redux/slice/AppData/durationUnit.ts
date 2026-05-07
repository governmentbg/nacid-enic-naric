import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import {
  fulfilledThunkState,
  initialThunkState,
  pendingThunkState,
  rejectedThunkState,
  commonThunkCondition,
  ReferenceDataDomain,
} from "@duosoftbg/nacid-components";
import { getReferenceDataOptions } from "../../../../services/coreServicesCalls";

const sliceName = "appData/durationUnit";
const durationUnitSlice = createSlice({
  name: sliceName,
  initialState: initialThunkState([]),
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(durationUnitThunk.pending, (state, action) => {
      return pendingThunkState(state);
    });
    builder.addCase(durationUnitThunk.fulfilled, (state, action) => {
      const { data } = action.payload;
      return fulfilledThunkState(data);
    });
    builder.addCase(durationUnitThunk.rejected, (state, action) => {
      return rejectedThunkState([]);
    });
  },
});

export const durationUnitThunk = createAsyncThunk(
  `${sliceName}/durationUnitThunk`,
  async () => {
    const response = await getReferenceDataOptions(ReferenceDataDomain.DURATION_UNIT)();
    return response;
  },
  {
    condition: (_, { getState, extra }) => {
      return commonThunkCondition(getState()["AppData"]?.DurationUnit);
    },
  }
);

export default durationUnitSlice.reducer;
