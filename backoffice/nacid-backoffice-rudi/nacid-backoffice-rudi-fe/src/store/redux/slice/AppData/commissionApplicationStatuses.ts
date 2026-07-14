import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { getCommissionApplicationStatuses } from "../../../../axios/api/services";
import {
  fulfilledThunkState,
  initialThunkState,
  pendingThunkState,
  rejectedThunkState,
  commonThunkCondition,
} from "@duosoftbg/nacid-components";

const sliceName = "appData/commissionApplicationStatuses";
const commissionApplicationStatusesSlice = createSlice({
  name: sliceName,
  initialState: initialThunkState([]),
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(commissionApplicationStatusesThunk.pending, (state, action) => {
      return pendingThunkState(state);
    });
    builder.addCase(commissionApplicationStatusesThunk.fulfilled, (state, action) => {
      const { data } = action.payload;
      return fulfilledThunkState(data);
    });
    builder.addCase(commissionApplicationStatusesThunk.rejected, (state, action) => {
      return rejectedThunkState([]);
    });
  },
});

export const commissionApplicationStatusesThunk = createAsyncThunk(
  `${sliceName}/commissionApplicationStatusesThunk`,
  async () => {
    const response = await getCommissionApplicationStatuses()();
    return { data: response };
  },
  {
    condition: (_, { getState, extra }) => {
      return commonThunkCondition(getState()["AppData"]?.commissionApplicationStatuses);
    },
  },
);

export default commissionApplicationStatusesSlice.reducer;
