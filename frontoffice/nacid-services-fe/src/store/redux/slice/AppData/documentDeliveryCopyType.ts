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

const sliceName = "appData/documentDeliveryCopyType";
const documentDeliveryCopyTypeSlice = createSlice({
  name: sliceName,
  initialState: initialThunkState([]),
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(documentDeliveryCopyTypeThunk.pending, (state, action) => {
      return pendingThunkState(state);
    });
    builder.addCase(documentDeliveryCopyTypeThunk.fulfilled, (state, action) => {
      const { data } = action.payload;
      return fulfilledThunkState(data);
    });
    builder.addCase(documentDeliveryCopyTypeThunk.rejected, (state, action) => {
      return rejectedThunkState([]);
    });
  },
});

export const documentDeliveryCopyTypeThunk = createAsyncThunk(
  `${sliceName}/documentDeliveryCopyTypeThunk`,
  async () => {
    const response = await getReferenceDataOptions(ReferenceDataDomain.DOCUMENT_DELIVERY_COPY_TYPE)();
    return response;
  },
  {
    condition: (_, { getState, extra }) => {
      return commonThunkCondition(getState()["AppData"]?.DocumentDeliveryCopyType);
    },
  }
);

export default documentDeliveryCopyTypeSlice.reducer;
