import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import {
  commonThunkCondition,
  fulfilledThunkState,
  initialThunkState,
  pendingThunkState,
  ReferenceDataDomain,
  rejectedThunkState,
} from "@duosoftbg/nacid-components";
import { CoreApiServicesBase } from "@duosoftbg/nacid-backoffice-components";

const sliceName = "appData/legalNatures";
const legalNaturesSlice = createSlice({
  name: sliceName,
  initialState: initialThunkState([]),
  reducers: {},
  extraReducers: (builder) => {
    builder.addCase(legalNaturesThunk.pending, (state, action) => {
      return pendingThunkState(state);
    });
    builder.addCase(legalNaturesThunk.fulfilled, (state, action) => {
      const { data } = action.payload;
      return fulfilledThunkState(data);
    });
    builder.addCase(legalNaturesThunk.rejected, (state, action) => {
      return rejectedThunkState([]);
    });
  },
});

export const legalNaturesThunk = createAsyncThunk(
  `${sliceName}/legalNaturesThunk`,
  async () => {
    const response = await CoreApiServicesBase.getReferenceDataOptions(ReferenceDataDomain.LEGAL_NATURE_TYPE)();
    return { data: response };
  },
  {
    condition: (_, { getState, extra }) => {
      return commonThunkCondition(getState()["AppData"]?.legalNatures);
    },
  },
);

export default legalNaturesSlice.reducer;
