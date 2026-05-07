import {
  FormSection,
  GridContainer,
  GridItem,
  GridSpg,
  InputFormField,
  NomenclatureAutocompleteFormField,
  ScrollableAsyncFormAutocomplete,
  TextareaFormField,
  YearFormField,
} from "@duosoftbg/nacid-components";
import React, { useEffect } from "react";
import { AppType, educationLevelsThunk } from "@duosoftbg/nacid-backoffice-components";
import { useFormContext, useWatch } from "react-hook-form";
import { useSelector } from "react-redux";
import { getUniversitiesAutocomplete } from "../../../../../../../../axios/api/services";

type PreviousDiplomaTypeSectionProps = {
  appType: AppType;
  titleSection?: string;
};

const PreviousDiplomaSection = ({
  titleSection = "t.base.previous.diploma.details",
}: PreviousDiplomaTypeSectionProps) => {
  return (
    <>
      <FormSection label={titleSection}>
        <GridSpg container spacing={1}>
          <GridSpg item xs={12}>
            <GridContainer spacing={4} mt={0}>
              <PrevDiplomaUniversityFields />
              <PrevDiplomaEduLevelFields />
              <GraduationDateFields />
              <GridItem sm={4} md={4}>
                <InputFormField fieldName={"prevDiplomaSpeciality"} labelCode={"l.prevDiplomaSpeciality"} />
              </GridItem>
              <GridItem sm={12} md={12}>
                <TextareaFormField fieldName={"prevDiplomaNotes"} labelCode={"l.prevDiplomaNotes"} />
              </GridItem>
            </GridContainer>
          </GridSpg>
        </GridSpg>
      </FormSection>
    </>
  );
};

const GraduationDateFields = () => {
  const diplomaDate = useWatch({ name: "diplomaDate" });

  return (
    <GridItem sm={4} md={4}>
      <YearFormField
        fieldName={"prevDiplomaGraduationDate"}
        labelCode={"l.prevDiplomaGraduationDate"}
        maxDate={diplomaDate ? new Date(diplomaDate.substr(diplomaDate.length - 4) - 1, 0, 1) : new Date()}
      />
    </GridItem>
  );
};

const PrevDiplomaEduLevelFields = () => {
  const { getValues } = useFormContext();

  const educationLevelsThunkState = useSelector((state) => {
    return state["ThunkData"].educationLevels;
  });

  return (
    <GridItem sm={4} md={4}>
      <NomenclatureAutocompleteFormField
        required={false}
        initialValue={getValues("prevDiplomaEduLevel.id")}
        fieldName={"prevDiplomaEduLevel.id"}
        labelCode={"l.prevDiplomaEduLevel"}
        thunkFn={educationLevelsThunk}
        thunkState={educationLevelsThunkState}
      />
    </GridItem>
  );
};

const PrevDiplomaUniversityFields = () => {
  const { getValues, setValue } = useFormContext();

  const prevDiplomaUniversityId = useWatch({ name: "prevDiplomaUniversity.id" });

  useEffect(() => {
    if (!getValues("prevDiplomaUniversity.id")) {
      setValue("prevDiplomaUniversity.country", "");
      setValue("prevDiplomaUniversity.city", "");
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [prevDiplomaUniversityId]);

  return (
    <>
      <GridItem sm={4} md={4}>
        <ScrollableAsyncFormAutocomplete
          onlyActive
          fieldName={"prevDiplomaUniversity.id"}
          selectedOption={getValues("prevDiplomaUniversity")}
          setOptionText={(option) => option.name}
          getOptionLabel={(option) => option.id + ""}
          setInputOnSelect={(option) => {
            if (option?.country) {
              setValue("prevDiplomaUniversity.country", option.country);
            }
            if (option?.city) {
              setValue("prevDiplomaUniversity.city", option.city);
            }
            return option.name;
          }}
          autocompleteFn={getUniversitiesAutocomplete}
          label={"l.prevDiplomaUniversity"}
          reduceOptionObject={false}
        />
      </GridItem>
      <GridItem sm={4} md={4}>
        <InputFormField
          fieldName={"prevDiplomaUniversity.country"}
          labelCode={"l.prevDiplomaUniversity.country"}
          isDisabled={true}
        />
      </GridItem>
      <GridItem sm={4} md={4}>
        <InputFormField
          fieldName={"prevDiplomaUniversity.city"}
          labelCode={"l.prevDiplomaUniversity.city"}
          isDisabled={true}
        />
      </GridItem>
    </>
  );
};

export default PreviousDiplomaSection;
