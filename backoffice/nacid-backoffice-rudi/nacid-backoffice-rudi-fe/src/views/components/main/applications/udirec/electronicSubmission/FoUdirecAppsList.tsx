import React, { Fragment } from "react";
import { Box, Grid } from "@mui/material";
import { GridSpg, TableSkeleton } from "@duosoftbg/nacid-components";
import FoUdirecAppsListTable from "./FoUdirecAppsListTable";

const FoUdirecAppsList = ({ records, total, isLoading, onPageOrOrderChange, blockTable, group, filterFn }) => {
  const showTable = () => {
    return (
      <Fragment>
        <FoUdirecAppsListTable
          records={records}
          total={total}
          onPageOrOrderChange={onPageOrOrderChange}
          blockTable={blockTable}
          group={group}
        />
      </Fragment>
    );
  };

  return (
    <Box>
      <Grid container spacing={1}>
        <GridSpg item xs={12}>
          {isLoading ? <TableSkeleton /> : showTable()}
        </GridSpg>
      </Grid>
    </Box>
  );
};

export default FoUdirecAppsList;
