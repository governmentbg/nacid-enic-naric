import {
  BoxSpg,
  DateFormField,
  GridContainer,
  GridItem,
  InputFormField,
  SelectFormField,
  BoxedContent,
} from "@duosoftbg/nacid-components";
import React, { useEffect } from "react";
import { useFormContext, useWatch } from "react-hook-form";
import { Typography } from "@mui/material";
import WorkingPeriodArrayFormFields from "./WorkingPeriodArrayFormFields";
import { useTranslation } from "react-i18next";
import useAppDispatch from "../../../../../../../hooks/redux/base/useAppDispatch";
import useAppSelector from "../../../../../../../hooks/redux/base/useAppSelector";
import { profExperienceDocTypeThunk } from "../../../../../../../store/redux/slice/AppData/profExperienceDocType";

const ExperienceDocumentFormFields = ({ index }) => {
  const { t } = useTranslation();
  const dispatch = useAppDispatch();
  const { getValues } = useFormContext();

  const thunkStateDocType = useAppSelector((state) => {
    return state.AppData.ProfExperienceDocType;
  });

  useEffect(() => {
    dispatch(profExperienceDocTypeThunk());
  }, [dispatch]);

  useWatch({ name: "experienceSelected" });

  const renderContent = () => {
    return (
      <>
        {index === 0 && (
          <GridContainer>
            <Typography variant={"h4"} color={"primary"}>
              {t("t.regprof.experience.experienceDocument")}
            </Typography>
          </GridContainer>
        )}
        <BoxSpg mt={index === 0 ? 4 : 0}>
          <GridContainer>
            <GridItem>
              <SelectFormField
                required={true}
                fieldName={`experience.experienceDocuments.${index}.type.id`}
                labelCode={"l.regprof.experience.experienceDocument.type"}
                selectOptions={thunkStateDocType.data.map((option) => {
                  return { value: option.id, text: option.name, active: option.isActive };
                })}
                addEmptyOption={true}
              />
            </GridItem>
            <GridItem>
              <InputFormField
                fieldName={`experience.experienceDocuments.${index}.documentNumber`}
                labelCode={"l.regprof.experience.experienceDocument.documentNumber"}
              />
            </GridItem>
          </GridContainer>
          <GridContainer>
            <GridItem>
              <DateFormField
                fieldName={`experience.experienceDocuments.${index}.documentDate`}
                labelCode={"l.regprof.experience.experienceDocument.documentDate"}
              />
            </GridItem>
            <GridItem>
              <InputFormField
                required={true}
                fieldName={`experience.experienceDocuments.${index}.institutionName`}
                labelCode={"l.regprof.experience.experienceDocument.institutionName"}
              />
            </GridItem>
          </GridContainer>

          <WorkingPeriodArrayFormFields documentIndex={index} />
        </BoxSpg>
      </>
    );
  };

  if (!getValues().experienceSelected) {
    return null;
  }
  if (index === 0) {
    return <BoxedContent>{renderContent()}</BoxedContent>;
  }
  return renderContent();
};
export default ExperienceDocumentFormFields;
