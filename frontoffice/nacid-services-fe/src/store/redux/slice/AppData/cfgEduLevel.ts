import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import {
  fulfilledThunkState,
  initialThunkState,
  pendingThunkState,
  rejectedThunkState,
  commonThunkCondition,
} from "@duosoftbg/nacid-components";
import { getCfgEduLevels } from "../../../../services/coreServicesCalls";

const sliceName = "appData/cfgEduLevel";
const cfgEduLevelSlice = createSlice({
  name: sliceName,
  initialState: initialThunkState([]),
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(cfgEduLevelThunk.pending, (state, action) => {
      return pendingThunkState(state);
    });
    builder.addCase(cfgEduLevelThunk.fulfilled, (state, action) => {
      const { data } = action.payload;
      return fulfilledThunkState(data);
    });
    builder.addCase(cfgEduLevelThunk.rejected, (state, action) => {
      return rejectedThunkState([]);
    });
  },
});

export const cfgEduLevelThunk = createAsyncThunk(
  `${sliceName}/cfgEduLevelThunk`,
  async () => {
    const response = await getCfgEduLevels()();
    return response;
  },
  {
    condition: (_, { getState, extra }) => {
      return commonThunkCondition(getState()["AppData"]?.CfgEduLevel);
    },
  }
);

export default cfgEduLevelSlice.reducer;
