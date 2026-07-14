import { AutocompleteFormField, GridItem, isNotEmpty } from "@duosoftbg/nacid-components";
import { useState } from "react";
import { useWatch } from "react-hook-form";

const DocumentTypeAutocompleteField = ({ docTypes, methods }) => {
  const [openAutocomplete, setOpenAutocomplete] = useState(false);
  const [inputValue, setInputValue] = useState("");

  const docTypeId = useWatch({ name: "attachmentType.id" });
  const docTypeName = useWatch({ name: "attachmentType.name" });

  const handleChangeDocType = (e, option) => {
    if (option) {
      methods.setValue("attachmentType.id", option.id);
      methods.setValue("attachmentType.name", option.name);
      setInputValue(option.name);
    } else {
      methods.setValue("attachmentType.id", "");
      methods.setValue("attachmentType.name", "");
      setInputValue("");
    }
  };

  const handleInputChange = (e: React.SyntheticEvent, value) => {
    if (isNotEmpty(e)) {
      switch (e.type) {
        case "change":
          setInputValue(value);
          break;
        case "blur":
          let val = docTypeId;
          const selectedOption = docTypes.find((element) => element.id === value || element.id + "" === value);
          if (val + "" === value && selectedOption) {
            setInputValue(selectedOption.name);
          } else {
            setInputValue("");
            methods.setValue("attachmentType.id", "");
            methods.setValue("attachmentType.name", "");
          }
          break;
      }
    }
  };

  return (
    <GridItem sm={12} md={12}>
      <AutocompleteFormField
        fieldName={"attachmentType.id"}
        label={"l.attachment.attachmentType"}
        value={{ id: docTypeId, name: docTypeName }}
        inputValue={inputValue}
        options={docTypes}
        isOptionEqualToValue={(option, val) => option.id === val.id}
        getOptionLabel={(option) => option.id + ""}
        setOptionText={(option) => option.name}
        useOptionTextAsFilter
        open={openAutocomplete}
        onOpenChange={setOpenAutocomplete}
        onChange={handleChangeDocType}
        onInputChange={handleInputChange}
        isSmall
      />
    </GridItem>
  );
};
export default DocumentTypeAutocompleteField;
