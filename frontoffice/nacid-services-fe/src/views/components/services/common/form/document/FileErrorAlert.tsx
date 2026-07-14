import { BoxSpg, getFieldError, GridItem, shouldShowFieldError } from "@duosoftbg/nacid-components";
import { Alert } from "@mui/material";

const FileErrorAlert = ({ methods }) => {
  if (
    !shouldShowFieldError("file.fileId", methods.formState, methods.getFieldState) &&
    !getFieldError("file", methods.getFieldState)
  ) {
    return null;
  }
  return (
    <GridItem sm={12} md={12}>
      <Alert severity={"error"}>
        {shouldShowFieldError("file.fileId", methods.formState, methods.getFieldState) ? (
          <BoxSpg>{getFieldError("file.fileId", methods.getFieldState)}</BoxSpg>
        ) : null}
        {getFieldError("file", methods.getFieldState) ? (
          <BoxSpg>{getFieldError("file", methods.getFieldState)}</BoxSpg>
        ) : null}
      </Alert>
    </GridItem>
  );
};
export default FileErrorAlert;
