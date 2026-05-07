import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import {
  fulfilledThunkState,
  initialThunkState,
  pendingThunkState,
  rejectedThunkState,
  commonThunkCondition,
  ReferenceDataDomain,
} from "@duosoftbg/nacid-components";
import { CoreApiServicesBase } from "@duosoftbg/nacid-backoffice-components";

const sliceName = "appData/commissionCalendarStatuses";
const commissionCalendarStatusesSlice = createSlice({
  name: sliceName,
  initialState: initialThunkState([]),
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(commissionCalendarStatusesThunk.pending, (state, action) => {
      return pendingThunkState(state);
    });
    builder.addCase(commissionCalendarStatusesThunk.fulfilled, (state, action) => {
      const { data } = action.payload;
      return fulfilledThunkState(data);
    });
    builder.addCase(commissionCalendarStatusesThunk.rejected, (state, action) => {
      return rejectedThunkState([]);
    });
  },
});

export const commissionCalendarStatusesThunk = createAsyncThunk(
  `${sliceName}/commissionCalendarStatusesThunk`,
  async () => {
    const response = await CoreApiServicesBase.getReferenceDataOptions(ReferenceDataDomain.COMMISSION_SESSION_STATUS)();
    return { data: response };
  },
  {
    condition: (_, { getState, extra }) => {
      return commonThunkCondition(getState()["AppData"]?.commissionCalendarStatuses);
    },
  },
);

export default commissionCalendarStatusesSlice.reducer;
