import { Chip } from "@mui/material";
import { FilePresent } from "@mui/icons-material";
import { BoxSpg } from "@duosoftbg/nacid-components";
import { useFormContext, useWatch } from "react-hook-form";

const FileDetails = () => {
  const { getValues } = useFormContext();

  useWatch({ name: "file" });

  return (
    <BoxSpg mt={3}>
      {getValues().file.fileName !== "" ? <Chip icon={<FilePresent />} label={getValues().file.fileName} /> : null}
    </BoxSpg>
  );
};
export default FileDetails;
