import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { fulfilledThunkState, initialThunkState, rejectedThunkState, THUNK_STATUS } from "@duosoftbg/nacid-components";
import { filterMyApplications } from "../../../../services/myApplicationsCalls";

const initialState = {
  filter: {},
  list: initialThunkState([]),
};

export const filterApplications = createAsyncThunk(`filterApplications/fetchStatus`, async (filter: any) => {
  const response = await filterMyApplications(filter)();
  return response;
});

const appsListSlice = createSlice({
  name: "AppsListSlice",
  initialState: initialState,
  reducers: {
    setFilter: (state, action) => {
      state.filter = action.payload;
    },
  },
  extraReducers: (builder) => {
    builder.addCase(filterApplications.fulfilled, (state, action) => {
      const { data } = action.payload;
      state.list = fulfilledThunkState(data);
    });
    builder.addCase(filterApplications.pending, (state, action) => {
      state.list.status = THUNK_STATUS.PENDING;
    });
    builder.addCase(filterApplications.rejected, (state, action) => {
      state.list = rejectedThunkState([]);
    });
  },
});

export const { setFilter } = appsListSlice.actions;
export default appsListSlice.reducer;
