import { createSlice } from "@reduxjs/toolkit";
import { initialMyCorrespondenceFilterValues } from "../../../../init/initialMyCorrespondenceFilterValues";

const myCorrespondenceFilterFormSlice = createSlice({
  name: "MyCorrespondenceFilterFormSlice",
  initialState: initialMyCorrespondenceFilterValues,
  reducers: {
    updateFilter: (state, action) => {
      const filterValues = { ...action.payload };
      return filterValues;
    },
    clearFilter: (state) => {
      return {
        ...initialMyCorrespondenceFilterValues,
        pageSize: state.pageSize,
        order: state.order,
        orderBy: state.orderBy,
      };
    },
    changePage: (state, action) => {
      state.page = action.payload;
    },
    changePageSize: (state, action) => {
      state.pageSize = action.payload;
    },
    changeOrderBy: (state, action) => {
      const currentOrderBy = state.orderBy;

      if (currentOrderBy === action.payload && state.order === "desc") {
        state.order = "asc";
      } else if (currentOrderBy === action.payload && state.order === "asc") {
        state.order = "desc";
      } else {
        state.order = initialMyCorrespondenceFilterValues.order;
      }
      state.orderBy = action.payload;
    },
  },
});

export const { updateFilter, clearFilter, changePageSize, changeOrderBy, changePage } =
  myCorrespondenceFilterFormSlice.actions;
export default myCorrespondenceFilterFormSlice.reducer;
