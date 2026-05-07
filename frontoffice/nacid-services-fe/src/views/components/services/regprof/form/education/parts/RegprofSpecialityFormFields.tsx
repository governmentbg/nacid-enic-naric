import { AlertSpg, GridContainer, GridItem, GridSpg } from "@duosoftbg/nacid-components";
import { Chip, IconButton } from "@mui/material";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPlus } from "@fortawesome/free-solid-svg-icons";
import { Delete } from "@mui/icons-material";
import React from "react";
import { useTranslation } from "react-i18next";
import SpecialityAutocompleteFormField from "../../../../common/form/education/parts/SpecialityAutocompleteFormField";

const RegprofSpecialityFormFields = ({
  addSpeciality,
  removeSpeciality,
  singleSpecialityFieldName,
  singleSpecialityIdFieldName,
  specialitiesList,
  autocompleteFn,
  additionalParams = null,
}) => {
  const { t } = useTranslation();

  return (
    <React.Fragment>
      <GridContainer mt={2} spacing={4}>
        <GridItem xs={11} sm={11} md={11.5}>
          <SpecialityAutocompleteFormField
            required={true}
            autocompleteFn={autocompleteFn}
            specialityField={singleSpecialityFieldName}
            specialityIdField={singleSpecialityIdFieldName}
            labelCode={"l.speciality"}
            additionalParams={additionalParams}
          />
        </GridItem>
        <GridSpg item xs={0.5}>
          <IconButton title={t("l.btn.add")} color={"primary"} onClick={addSpeciality}>
            <FontAwesomeIcon style={{ fontSize: 20 }} icon={faPlus} />
          </IconButton>
        </GridSpg>
      </GridContainer>
      <GridSpg container mt={2}>
        <GridSpg item xs={12} sm={12} md={12} pr={4}>
          {specialitiesList.map((spec, index) => (
            <Chip
              sx={{ mr: 2, mt: 2, maxWidth: "inherit" }}
              key={spec.id && spec.id !== "" ? spec.id : spec.name}
              label={spec.name}
              onDelete={() => removeSpeciality(index)}
              deleteIcon={<Delete sx={{ pl: 5 }} />}
              variant="outlined"
            />
          ))}
        </GridSpg>
      </GridSpg>
      <GridContainer mt={0} spacing={4}>
        <GridItem sm={12} md={12}>
          <AlertSpg severity={"info"}>{t("m.speciality.multiple.info")}</AlertSpg>
        </GridItem>
      </GridContainer>
    </React.Fragment>
  );
};
export default RegprofSpecialityFormFields;
