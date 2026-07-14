import { GridItem, NomenclatureAutocompleteFormField, InputFormField, AlertSpg } from "@duosoftbg/nacid-components";
import useAppSelector from "../../../../../../../hooks/redux/base/useAppSelector";
import { profGroupThunk } from "../../../../../../../store/redux/slice/AppData/profGroup";
import { useFormContext, useWatch } from "react-hook-form";
import { useEffect } from "react";
import { useTranslation } from "react-i18next";

const DocProfGroupFormFields = () => {
  const { t } = useTranslation();
  const { setValue } = useFormContext();

  const thunkStateProfGroup = useAppSelector((state) => {
    return state.AppData.ProfGroup;
  });

  const profGroupId = useWatch({ name: "gainedLevelProfGroup.id" });

  useEffect(() => {
    if (profGroupId && profGroupId !== "" && thunkStateProfGroup.data.length > 0) {
      const foundNomValue = thunkStateProfGroup.data.find((pg) => pg.id === profGroupId);

      if (foundNomValue) {
        setValue("gainedLevelProfGroup", foundNomValue);
      }
    }
    if (!profGroupId || profGroupId === "") {
      setValue("gainedLevelProfGroup.educationArea.name", "");
    }
  }, [profGroupId, thunkStateProfGroup, setValue]);

  return (
    <>
      <GridItem>
        <NomenclatureAutocompleteFormField
          onlyActive
          required={false}
          fieldName={"gainedLevelProfGroup.id"}
          labelCode={"l.gainedLevelProfGroup"}
          thunkState={thunkStateProfGroup}
          thunkFn={profGroupThunk}
          initialValue={profGroupId}
        />
      </GridItem>
      <GridItem>
        <InputFormField
          isDisabled={true}
          fieldName={"gainedLevelProfGroup.educationArea.name"}
          labelCode={"l.gainedLevelEducationArea"}
        />
      </GridItem>
      <GridItem sm={12} md={12}>
        <AlertSpg severity={"info"}>{t("m.docDegrees.education.fields.info")}</AlertSpg>
      </GridItem>
    </>
  );
};
export default DocProfGroupFormFields;
