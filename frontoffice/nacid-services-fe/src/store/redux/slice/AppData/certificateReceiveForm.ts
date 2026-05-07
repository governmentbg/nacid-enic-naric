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

const sliceName = "appData/certificateReceiveForm";
const certificateReceiveFormSlice = createSlice({
  name: sliceName,
  initialState: initialThunkState([]),
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(certificateReceiveFormThunk.pending, (state, action) => {
      return pendingThunkState(state);
    });
    builder.addCase(certificateReceiveFormThunk.fulfilled, (state, action) => {
      const { data } = action.payload;
      return fulfilledThunkState(data);
    });
    builder.addCase(certificateReceiveFormThunk.rejected, (state, action) => {
      return rejectedThunkState([]);
    });
  },
});

export const certificateReceiveFormThunk = createAsyncThunk(
  `${sliceName}/certificateReceiveFormThunk`,
  async () => {
    const response = await getReferenceDataOptions(ReferenceDataDomain.CERTIFICATE_RECEIVE_FORM)();
    return response;
  },
  {
    condition: (_, { getState, extra }) => {
      return commonThunkCondition(getState()["AppData"]?.CertificateReceiveForm);
    },
  }
);

export default certificateReceiveFormSlice.reducer;
