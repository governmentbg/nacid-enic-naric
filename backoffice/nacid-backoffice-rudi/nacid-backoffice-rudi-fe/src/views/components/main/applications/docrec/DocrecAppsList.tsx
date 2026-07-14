import React, { Fragment } from "react";
import { Box, Grid } from "@mui/material";
import { GridSpg, TableSkeleton } from "@duosoftbg/nacid-components";
import DocrecAppsListTable from "./DocrecAppsListTable";

const DocrecAppsList = ({
  records,
  total,
  isLoading,
  onPageOrOrderChange,
  blockTable,
  group,
  filterFn,
  applicationGroup,
}) => {
  const showTable = () => {
    return (
      <Fragment>
        <DocrecAppsListTable
          records={records}
          total={total}
          onPageOrOrderChange={onPageOrOrderChange}
          blockTable={blockTable}
          group={group}
          applicationGroup={applicationGroup}
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

export default DocrecAppsList;
