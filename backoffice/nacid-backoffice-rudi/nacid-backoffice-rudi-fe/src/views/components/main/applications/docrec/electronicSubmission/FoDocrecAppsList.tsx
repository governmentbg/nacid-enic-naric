import React, { Fragment } from "react";
import { Box, Grid } from "@mui/material";
import { GridSpg, TableSkeleton } from "@duosoftbg/nacid-components";
import FoDocrecAppsListTable from "./FoDocrecAppsListTable";

const FoDocrecAppsList = ({ records, total, isLoading, onPageOrOrderChange, blockTable, group, filterFn }) => {
  const showTable = () => {
    return (
      <Fragment>
        <FoDocrecAppsListTable
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

export default FoDocrecAppsList;
