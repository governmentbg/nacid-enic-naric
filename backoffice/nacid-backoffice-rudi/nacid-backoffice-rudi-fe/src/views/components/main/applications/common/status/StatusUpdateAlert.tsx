import { CollapsibleAlert } from "@duosoftbg/nacid-components";
import { useWatch } from "react-hook-form";

const StatusUpdateAlert = ({ flagField = "isStatusUpdated" }) => {
  const isStatusUpdated = useWatch({ name: flagField });

  if (isStatusUpdated) {
    return <CollapsibleAlert message={"m.status.change.success"} />;
  }

  return null;
};
export default StatusUpdateAlert;
