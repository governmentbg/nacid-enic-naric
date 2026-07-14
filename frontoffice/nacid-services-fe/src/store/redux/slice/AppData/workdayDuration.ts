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

const sliceName = "appData/workdayDuration";
const workdayDurationSlice = createSlice({
  name: sliceName,
  initialState: initialThunkState([]),
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(workdayDurationThunk.pending, (state, action) => {
      return pendingThunkState(state);
    });
    builder.addCase(workdayDurationThunk.fulfilled, (state, action) => {
      const { data } = action.payload;
      return fulfilledThunkState(data);
    });
    builder.addCase(workdayDurationThunk.rejected, (state, action) => {
      return rejectedThunkState([]);
    });
  },
});

export const workdayDurationThunk = createAsyncThunk(
  `${sliceName}/workdayDurationThunk`,
  async () => {
    const response = await getReferenceDataOptions(ReferenceDataDomain.WORKDAY_DURATION)();
    return response;
  },
  {
    condition: (_, { getState, extra }) => {
      return commonThunkCondition(getState()["AppData"]?.WorkdayDuration);
    },
  }
);

export default workdayDurationSlice.reducer;
