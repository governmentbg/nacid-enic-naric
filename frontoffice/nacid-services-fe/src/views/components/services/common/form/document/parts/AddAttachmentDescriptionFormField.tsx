import { GridItem, InputFormField } from "@duosoftbg/nacid-components";
import { useWatch } from "react-hook-form";

const AddAttachmentDescriptionFormField = () => {
  const attachmentTypeId = useWatch({ name: "attachmentType.id" });

  return (
    <GridItem sm={12} md={12}>
      <InputFormField
        required={attachmentTypeId && attachmentTypeId !== null ? false : true}
        fieldName={"description"}
        labelCode={"l.attachment.description"}
      />
    </GridItem>
  );
};
export default AddAttachmentDescriptionFormField;
