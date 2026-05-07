import { useEffect, useState } from "react";
import { useFormContext, Controller } from "react-hook-form";
import {
  FormControlWrapper,
  shouldShowFieldError,
  getFieldError,
  ScrollableAsyncAutocomplete,
} from "@duosoftbg/nacid-components";

const SelectOrWriteAutocompleteFormField = ({
  label,
  autocompleteFn,
  setOptionText,
  getOptionLabel,
  setInputOnSelect,
  isSmall = true,
  inputMinSearchLength = 0,
  additionalParams = null,
  textFieldName,
  idFieldName = null,
  selectedOption = null,
  required = false,
  sortColumn = "name",
}) => {
  const [selected, setSelected] = useState(null);
  const { control, formState, getFieldState, setValue } = useFormContext();

  useEffect(() => {
    setSelected(selectedOption);
  }, [selectedOption]);

  const handleInputChange = (option) => {
    setValue(textFieldName, option, { shouldDirty: true });
    if (idFieldName != null) {
      setValue(idFieldName, "");
    }
    setSelected({ id: "", name: option });
  };

  return (
    <FormControlWrapper
      errorText={
        shouldShowFieldError(textFieldName, formState, getFieldState)
          ? getFieldError(textFieldName, getFieldState)
          : null
      }
    >
      <Controller
        name={textFieldName}
        control={control}
        render={() => (
          <ScrollableAsyncAutocomplete
            freeSolo={true}
            label={label}
            selectedOption={selected}
            onChangeSelected={(option) => {
              setValue(textFieldName, option !== null ? option.name : "", { shouldDirty: true });
              if (idFieldName != null) {
                setValue(idFieldName, option !== null ? option.id : "");
              }
              setSelected(option);
            }}
            onRemoveInputValue={() => {
              setValue(textFieldName, "", { shouldDirty: true });
              if (idFieldName != null) {
                setValue(idFieldName, "");
              }
              setSelected(null);
            }}
            onInputChange={handleInputChange}
            serviceFn={autocompleteFn}
            isSmall={isSmall}
            setOptionText={setOptionText}
            getOptionLabel={getOptionLabel}
            setInputOnSelect={setInputOnSelect}
            inputMinSearchLength={inputMinSearchLength}
            additionalParams={additionalParams}
            required={required}
            sortColumn={sortColumn}
          />
        )}
      />
    </FormControlWrapper>
  );
};
export default SelectOrWriteAutocompleteFormField;
