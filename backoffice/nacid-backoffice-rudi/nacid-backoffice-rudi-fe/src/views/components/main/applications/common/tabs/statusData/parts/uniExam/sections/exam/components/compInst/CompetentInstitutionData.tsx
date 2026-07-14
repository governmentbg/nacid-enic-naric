import { ArrayFormField, GridContainer, GridItem, TempFormDataActions } from "@duosoftbg/nacid-components";
import React, { useEffect } from "react";
import { selectCompetentInstitutionByCountry } from "../../../../../../../../../../../../../axios/api/services";
import { useFormContext } from "react-hook-form";
import { useTranslation } from "react-i18next";
import Typography from "@mui/material/Typography";
import CompetentInstitutionMenuButton from "../../../../../common/button/CompetentInstitutionMenuButton";
import useAppDispatch from "../../../../../../../../../../../../../hooks/redux/base/useAppDispatch";
import useAppSelector from "../../../../../../../../../../../../../hooks/redux/base/useAppSelector";
import { LibraryComponentsControlActions, ReloadWatcherObject } from "@duosoftbg/nacid-backoffice-components";

const CompetentInstitutionData = ({ tempDataKey, competentInstitutionPointer }) => {
  const { t } = useTranslation();
  const dispatch = useAppDispatch();
  const { getValues } = useFormContext();

  const competentInstitutionId = useAppSelector((state) => {
    return state.TempFormData[tempDataKey]?.[competentInstitutionPointer];
  });

  const handleOpenViewModal = (competentInstitutionId) => {
    dispatch(
      LibraryComponentsControlActions.competentInstitutionControlActions.openModal({
        id: competentInstitutionId,
        modalType: "view",
      }),
    );
  };

  const onSelectRecord = (option) => {
    dispatch(
      TempFormDataActions.setTempData({
        key: tempDataKey,
        pointer: competentInstitutionPointer,
        data: option?.id,
      }),
    );
  };

  const onAddRecord = () => {
    dispatch(
      TempFormDataActions.removeTempData({
        key: tempDataKey,
        pointer: competentInstitutionPointer,
      }),
    );
  };

  useEffect(() => {
    return () => {
      dispatch(
        TempFormDataActions.removeTempData({
          key: tempDataKey,
          pointer: competentInstitutionPointer,
        }),
      );
    };
    // eslint-disable-next-line
  }, []);

  return (
    <GridContainer mt={0}>
      <GridItem sm={12} md={12} pt={4}>
        <Typography variant={"h6"} color={"primary"}>
          {t("t.national.competentInstitution.data")}
        </Typography>
      </GridItem>
      <GridItem sm={12} md={12}>
        <div style={{ width: "100%", position: "relative" }}>
          <div style={{ width: "calc(100% - 40px)" }}>
            <ArrayFormField
              fieldName={"competentInstitutions"}
              listLabel={"l.selected.competentInstitutions"}
              autocompleteLabel={"l.uniExamination.competentInstitutions"}
              autocompleteFn={() => selectCompetentInstitutionByCountry(getValues("university.country.id"))}
              onSelect={onSelectRecord}
              onAdd={onAddRecord}
              fetchObject={ReloadWatcherObject.CompetentInstitution.change()}
              initialValue={competentInstitutionId ? competentInstitutionId : undefined}
              onItemClick={(competentInstitutionId) => handleOpenViewModal(competentInstitutionId)}
            />
          </div>
          <div style={{ width: "40px", position: "absolute", right: -2, top: -8 }}>
            <CompetentInstitutionMenuButton competentInstitutionId={competentInstitutionId} />
          </div>
        </div>
      </GridItem>
    </GridContainer>
  );
};
export default CompetentInstitutionData;
