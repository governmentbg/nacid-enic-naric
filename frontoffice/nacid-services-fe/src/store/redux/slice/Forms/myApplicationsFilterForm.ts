import { createSlice } from "@reduxjs/toolkit";
import { initialMyApplicationsFilterValues } from "../../../../init/initialMyApplicationsFilterValues";

const myApplicationsFilterFormSlice = createSlice({
  name: "MyApplicationsFilterFormSlice",
  initialState: initialMyApplicationsFilterValues,
  reducers: {
    updateFilter: (state, action) => {
      const filterValues = { ...action.payload };
      if (filterValues.status === "") {
        filterValues.status = null;
      }
      if (filterValues.applicationSubtype === "") {
        filterValues.applicationSubtype = null;
      }
      return filterValues;
    },
    clearFilter: (state) => {
      return {
        ...initialMyApplicationsFilterValues,
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
        state.order = initialMyApplicationsFilterValues.order;
      }
      state.orderBy = action.payload;
    },
  },
});

export const { updateFilter, clearFilter, changePageSize, changeOrderBy, changePage } =
  myApplicationsFilterFormSlice.actions;
export default myApplicationsFilterFormSlice.reducer;
