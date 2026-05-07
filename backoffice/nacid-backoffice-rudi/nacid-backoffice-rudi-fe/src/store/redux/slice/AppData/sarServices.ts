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

const sliceName = "appData/sarServices";
const sarServicesSlice = createSlice({
  name: sliceName,
  initialState: initialThunkState([]),
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(sarServicesThunk.pending, (state, action) => {
      return pendingThunkState(state);
    });
    builder.addCase(sarServicesThunk.fulfilled, (state, action) => {
      const { data } = action.payload;
      return fulfilledThunkState(data);
    });
    builder.addCase(sarServicesThunk.rejected, (state, action) => {
      return rejectedThunkState([]);
    });
  },
});

export const sarServicesThunk = createAsyncThunk(
  `${sliceName}/sarServicesThunk`,
  async () => {
    const response = await CoreApiServicesBase.getReferenceDataOptions(ReferenceDataDomain.SAR_APPLICATION_TYPE)();
    return { data: response };
  },
  {
    condition: (_, { getState, extra }) => {
      return commonThunkCondition(getState()["AppData"]?.sarServices);
    },
  },
);

export default sarServicesSlice.reducer;
