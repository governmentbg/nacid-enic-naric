import { GridItem, NomenclatureAutocompleteFormField, useExternalFormField } from "@duosoftbg/nacid-components";
import {
  bolognaCyclesThunk,
  LibraryComponentsControlActions,
  SaveBolognaCycleDialog,
} from "@duosoftbg/nacid-backoffice-components";
import React, { useState } from "react";
import { useFormContext } from "react-hook-form";
import { useDispatch, useSelector } from "react-redux";
import { useTranslation } from "react-i18next";
import { IconButton, Tooltip } from "@mui/material";
import AddIcon from "@mui/icons-material/Add";

const BolognaCycleFields = ({ addNomenclaturesOption = true }) => {
  const { t } = useTranslation();
  const dispatch = useDispatch();
  const { getValues } = useFormContext();

  const bolognaCyclesThunkState = useSelector((state) => {
    return state["ThunkData"].bolognaCycles;
  });

  const [modalBolognaCycleFieldId, setModalBolognaCycleFieldId] = useState<string>("");
  const bolognaCycleTempDataKey = "SBCD";
  useExternalFormField({ key: bolognaCycleTempDataKey, pointer: "bolognaCycle.id" });
  useExternalFormField({ key: bolognaCycleTempDataKey, pointer: "accessedBolognaCycle.id" });

  const handleOpenBCModal = (id, modalType) => {
    setModalBolognaCycleFieldId("bolognaCycle.id");
    const payload = { id, modalType };
    dispatch(LibraryComponentsControlActions.bolognaCycleActions.openModal(payload));
  };

  const handleOpenABCModal = (id, modalType) => {
    setModalBolognaCycleFieldId("accessedBolognaCycle.id");
    const payload = { id, modalType };
    dispatch(LibraryComponentsControlActions.bolognaCycleActions.openModal(payload));
  };

  return (
    <>
      {addNomenclaturesOption && (
        <SaveBolognaCycleDialog
          resetBolognaCycleExternalField={true}
          fieldId={modalBolognaCycleFieldId}
          tempFormDataKey={bolognaCycleTempDataKey}
        />
      )}
      <GridItem sm={6} md={6}>
        {addNomenclaturesOption && (
          <div style={{ width: "100%", position: "relative" }}>
            <div style={{ width: "calc(100% - 50px)" }}>
              <NomenclatureAutocompleteFormField
                onlyActive
                required={false}
                initialValue={getValues("bolognaCycle.id")}
                fieldName={"bolognaCycle.id"}
                labelCode={"l.bolognaCycle"}
                thunkFn={bolognaCyclesThunk}
                thunkState={bolognaCyclesThunkState}
              />
            </div>
            <div style={{ width: "50px", position: "absolute", right: -2, top: -2 }}>
              <Tooltip title={t("l.create.new")}>
                <IconButton color="primary" onClick={() => handleOpenBCModal("", "edit")}>
                  <AddIcon />
                </IconButton>
              </Tooltip>
            </div>
          </div>
        )}
        {!addNomenclaturesOption && (
          <NomenclatureAutocompleteFormField
            onlyActive
            required={false}
            initialValue={getValues("bolognaCycle.id")}
            fieldName={"bolognaCycle.id"}
            labelCode={"l.bolognaCycle"}
            thunkFn={bolognaCyclesThunk}
            thunkState={bolognaCyclesThunkState}
          />
        )}
      </GridItem>
      <GridItem sm={6} md={6}>
        {addNomenclaturesOption && (
          <div style={{ width: "100%", position: "relative" }}>
            <div style={{ width: "calc(100% - 50px)" }}>
              <NomenclatureAutocompleteFormField
                onlyActive
                required={false}
                initialValue={getValues("accessedBolognaCycle.id")}
                fieldName={"accessedBolognaCycle.id"}
                labelCode={"l.accessedBolognaCycle"}
                thunkFn={bolognaCyclesThunk}
                thunkState={bolognaCyclesThunkState}
              />
            </div>
            <div style={{ width: "50px", position: "absolute", right: -2, top: -2 }}>
              <Tooltip title={t("l.create.new")}>
                <IconButton color="primary" onClick={() => handleOpenABCModal("", "edit")}>
                  <AddIcon />
                </IconButton>
              </Tooltip>
            </div>
          </div>
        )}
        {!addNomenclaturesOption && (
          <NomenclatureAutocompleteFormField
            onlyActive
            required={false}
            initialValue={getValues("accessedBolognaCycle.id")}
            fieldName={"accessedBolognaCycle.id"}
            labelCode={"l.accessedBolognaCycle"}
            thunkFn={bolognaCyclesThunk}
            thunkState={bolognaCyclesThunkState}
          />
        )}
      </GridItem>
    </>
  );
};
export default BolognaCycleFields;
