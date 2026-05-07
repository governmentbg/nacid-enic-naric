import { GridItem, NomenclatureAutocompleteFormField, useExternalFormField } from "@duosoftbg/nacid-components";
import {
  europeanQualificationsThunk,
  LibraryComponentsControlActions,
  SaveEuropeanQualificationsFrameworkDialog,
} from "@duosoftbg/nacid-backoffice-components";
import React, { useState } from "react";
import { useFormContext } from "react-hook-form";
import { useDispatch, useSelector } from "react-redux";
import { useTranslation } from "react-i18next";
import { IconButton, Tooltip } from "@mui/material";
import AddIcon from "@mui/icons-material/Add";

const EuropeanQualificationsFields = ({ addNomenclaturesOption = true }) => {
  const { t } = useTranslation();
  const dispatch = useDispatch();
  const { getValues } = useFormContext();

  const europeanQualificationsThunkState = useSelector((state) => {
    return state["ThunkData"].europeanQualifications;
  });

  const [modalEuropeanQualificationFieldId, setModalEuropeanQualificationFieldId] = useState<string>("");
  const europeanQualificationsTempDataKey = "SEQFD";
  useExternalFormField({ key: europeanQualificationsTempDataKey, pointer: "europeanQualificationFramework.id" });
  useExternalFormField({
    key: europeanQualificationsTempDataKey,
    pointer: "accessedEuropeanQualificationFramework.id",
  });

  const handleOpenEQModal = (id, modalType) => {
    setModalEuropeanQualificationFieldId("europeanQualificationFramework.id");
    const payload = { id, modalType };
    dispatch(LibraryComponentsControlActions.europeanQualificationsFrameworkControlActions.openModal(payload));
  };

  const handleOpenAEQModal = (id, modalType) => {
    setModalEuropeanQualificationFieldId("accessedEuropeanQualificationFramework.id");
    const payload = { id, modalType };
    dispatch(LibraryComponentsControlActions.europeanQualificationsFrameworkControlActions.openModal(payload));
  };

  return (
    <>
      {addNomenclaturesOption && (
        <SaveEuropeanQualificationsFrameworkDialog
          resetEuropeanQualificationExternalField={true}
          fieldId={modalEuropeanQualificationFieldId}
          tempFormDataKey={europeanQualificationsTempDataKey}
        />
      )}
      <GridItem sm={6} md={6}>
        {addNomenclaturesOption && (
          <div style={{ width: "100%", position: "relative" }}>
            <div style={{ width: "calc(100% - 50px)" }}>
              <NomenclatureAutocompleteFormField
                onlyActive
                required={false}
                initialValue={getValues("europeanQualificationFramework.id")}
                fieldName={"europeanQualificationFramework.id"}
                labelCode={"l.europeanQualificationFramework"}
                thunkFn={europeanQualificationsThunk}
                thunkState={europeanQualificationsThunkState}
              />
            </div>
            <div style={{ width: "50px", position: "absolute", right: -2, top: -2 }}>
              <Tooltip title={t("l.create.new")}>
                <IconButton color="primary" onClick={() => handleOpenEQModal("", "edit")}>
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
            initialValue={getValues("europeanQualificationFramework.id")}
            fieldName={"europeanQualificationFramework.id"}
            labelCode={"l.europeanQualificationFramework"}
            thunkFn={europeanQualificationsThunk}
            thunkState={europeanQualificationsThunkState}
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
                initialValue={getValues("accessedEuropeanQualificationFramework.id")}
                fieldName={"accessedEuropeanQualificationFramework.id"}
                labelCode={"l.accessedEuropeanQualificationFramework"}
                thunkFn={europeanQualificationsThunk}
                thunkState={europeanQualificationsThunkState}
              />
            </div>
            <div style={{ width: "50px", position: "absolute", right: -2, top: -2 }}>
              <Tooltip title={t("l.create.new")}>
                <IconButton color="primary" onClick={() => handleOpenAEQModal("", "edit")}>
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
            initialValue={getValues("accessedEuropeanQualificationFramework.id")}
            fieldName={"accessedEuropeanQualificationFramework.id"}
            labelCode={"l.accessedEuropeanQualificationFramework"}
            thunkFn={europeanQualificationsThunk}
            thunkState={europeanQualificationsThunkState}
          />
        )}
      </GridItem>
    </>
  );
};
export default EuropeanQualificationsFields;
