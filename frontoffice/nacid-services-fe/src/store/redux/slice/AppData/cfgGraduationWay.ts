import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import {
  fulfilledThunkState,
  initialThunkState,
  pendingThunkState,
  rejectedThunkState,
  commonThunkCondition,
} from "@duosoftbg/nacid-components";
import { getCfgGraduationWays } from "../../../../services/coreServicesCalls";

const sliceName = "appData/cfgGraduationWay";
const cfgGraduationWaySlice = createSlice({
  name: sliceName,
  initialState: initialThunkState([]),
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(cfgGraduationWayThunk.pending, (state, action) => {
      return pendingThunkState(state);
    });
    builder.addCase(cfgGraduationWayThunk.fulfilled, (state, action) => {
      const { data } = action.payload;
      return fulfilledThunkState(data);
    });
    builder.addCase(cfgGraduationWayThunk.rejected, (state, action) => {
      return rejectedThunkState([]);
    });
  },
});

export const cfgGraduationWayThunk = createAsyncThunk(
  `${sliceName}/cfgGraduationWayThunk`,
  async () => {
    const response = await getCfgGraduationWays()();
    return response;
  },
  {
    condition: (_, { getState, extra }) => {
      return commonThunkCondition(getState()["AppData"]?.CfgGraduationWay);
    },
  }
);

export default cfgGraduationWaySlice.reducer;
