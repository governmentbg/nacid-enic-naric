import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import {
  fulfilledThunkState,
  initialThunkState,
  pendingThunkState,
  rejectedThunkState,
  commonThunkCondition,
} from "@duosoftbg/nacid-components";
import { getCommissionCalendarApplicationStatuses } from "../../../../axios/api/services";

const sliceName = "appData/commissionCalendarApplicationsStatuses";
const commissionCalendarApplicationsStatusesSlice = createSlice({
  name: sliceName,
  initialState: initialThunkState([]),
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(commissionCalendarApplicationsStatusesThunk.pending, (state, action) => {
      return pendingThunkState(state);
    });
    builder.addCase(commissionCalendarApplicationsStatusesThunk.fulfilled, (state, action) => {
      const { data } = action.payload;
      return fulfilledThunkState(data);
    });
    builder.addCase(commissionCalendarApplicationsStatusesThunk.rejected, (state, action) => {
      return rejectedThunkState([]);
    });
  },
});

export const commissionCalendarApplicationsStatusesThunk = createAsyncThunk(
  `${sliceName}/commissionCalendarApplicationsStatusesThunk`,
  async () => {
    const response = await getCommissionCalendarApplicationStatuses()();
    return { data: response };
  },
  {
    condition: (_, { getState, extra }) => {
      return commonThunkCondition(getState()["AppData"]?.commissionCalendarApplicationsStatuses);
    },
  },
);

export default commissionCalendarApplicationsStatusesSlice.reducer;
