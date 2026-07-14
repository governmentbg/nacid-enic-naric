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

const sliceName = "appData/publicAccessInfoForm";
const publicAccessInfoFormSlice = createSlice({
  name: sliceName,
  initialState: initialThunkState([]),
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(publicAccessInfoFormThunk.pending, (state, action) => {
      return pendingThunkState(state);
    });
    builder.addCase(publicAccessInfoFormThunk.fulfilled, (state, action) => {
      const { data } = action.payload;
      return fulfilledThunkState(data);
    });
    builder.addCase(publicAccessInfoFormThunk.rejected, (state, action) => {
      return rejectedThunkState([]);
    });
  },
});

export const publicAccessInfoFormThunk = createAsyncThunk(
  `${sliceName}/publicAccessInfoFormThunk`,
  async () => {
    const response = await getReferenceDataOptions(ReferenceDataDomain.PUBLIC_ACCESS_INFO_FORM)();
    return response;
  },
  {
    condition: (_, { getState, extra }) => {
      return commonThunkCondition(getState()["AppData"]?.PublicAccessInfoForm);
    },
  }
);

export default publicAccessInfoFormSlice.reducer;
