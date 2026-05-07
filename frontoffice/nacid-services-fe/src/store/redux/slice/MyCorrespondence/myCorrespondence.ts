import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { fulfilledThunkState, initialThunkState, rejectedThunkState, THUNK_STATUS } from "@duosoftbg/nacid-components";
import { filterMyCorrespondence } from "../../../../services/myCorrespondenceCalls";

const initialState = {
  filter: {},
  list: initialThunkState([]),
};

export const filterCorrespondence = createAsyncThunk(`filterCorrespondence/fetchStatus`, async (filter: any) => {
  const response = await filterMyCorrespondence(filter)();
  return response;
});

const correspondenceListSlice = createSlice({
  name: "CorrespondenceListSlice",
  initialState: initialState,
  reducers: {
    setFilter: (state, action) => {
      state.filter = action.payload;
    },
  },
  extraReducers: (builder) => {
    builder.addCase(filterCorrespondence.fulfilled, (state, action) => {
      const { data } = action.payload;
      state.list = fulfilledThunkState(data);
    });
    builder.addCase(filterCorrespondence.pending, (state, action) => {
      state.list.status = THUNK_STATUS.PENDING;
    });
    builder.addCase(filterCorrespondence.rejected, (state, action) => {
      state.list = rejectedThunkState([]);
    });
  },
});

export const { setFilter } = correspondenceListSlice.actions;
export default correspondenceListSlice.reducer;
