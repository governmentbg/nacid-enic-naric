import { GridItem, InputFormField, NomenclatureAutocompleteFormField, useAsyncCall } from "@duosoftbg/nacid-components";
import { selectProfGroup } from "../../../../../../axios/api/services";
import React, { useEffect } from "react";
import { useFormContext, useWatch } from "react-hook-form";
import useAppSelector from "../../../../../../hooks/redux/base/useAppSelector";
import { profGroupsThunk } from "../../../../../../store/redux/slice/AppData/profGroups";

const ProfGroupFields = ({ fieldName = "profGroupId", profGroupLabel = "l.profGroup" }) => {
  const { getValues, setValue } = useFormContext();
  const { asyncCall } = useAsyncCall();

  const profGroupsThunkState = useAppSelector((state) => {
    return state.AppData.profGroups;
  });

  const profGroupId = useWatch({ name: fieldName });

  useEffect(() => {
    if (!profGroupId) {
      setValue("profGroup.educationAreaName", "");
    } else {
      asyncCall({
        promise: selectProfGroup(profGroupId),
        processResponseErrors: false,
        onSuccess: (response) => {
          setValue("profGroup.educationAreaName", response.educationArea.name);
        },
        onError: () => {
          setValue("profGroup.educationAreaName", "");
        },
      });
    }
    // eslint-disable-next-line
  }, [profGroupId]);

  return (
    <>
      <GridItem sm={6} md={6}>
        <NomenclatureAutocompleteFormField
          required={false}
          initialValue={getValues(fieldName)}
          fieldName={fieldName}
          labelCode={profGroupLabel}
          thunkFn={profGroupsThunk}
          thunkState={profGroupsThunkState}
        />
      </GridItem>
      <GridItem sm={6} md={6}>
        <InputFormField
          fieldName={"profGroup.educationAreaName"}
          labelCode={"l.profGroup.educationArea"}
          isDisabled={true}
        />
      </GridItem>
    </>
  );
};
export default ProfGroupFields;
