import {
  BoxSpg,
  FormSection,
  GridContainer,
  GridItem,
  GridSpg,
  InputFormField,
  isNotEmpty,
  useAsyncCall,
} from "@duosoftbg/nacid-components";
import { useFormContext, useWatch } from "react-hook-form";
import { UniversityViewContent } from "@duosoftbg/nacid-backoffice-components";
import React, { useEffect, useState } from "react";
import { selectUniversityById } from "../../../../../../../../axios/api/services";
import UniversitySectionMenuButton from "./components/button/UniversitySectionMenuButton";
import { useTranslation } from "react-i18next";
import useAppDispatch from "../../../../../../../../hooks/redux/base/useAppDispatch";
import { UniversityControlActions } from "../../../../../../../../store/redux/slice/ComponentsControl/baseUniversityControl";
import { UniversitySearchForm } from "../../../../../../../../utils/helpers";
import { Link } from "@mui/material";
import FacultySelectField from "../../../components/FacultySelectField";

const BaseUniversityFormSection = ({
  titleSection,
  baseUniversityId,
  baseUniversityIdPointer,
  tempDataKey,
  baseUniversityWatcher,
  viewDataComponent = null,
  searchFormDefaultValues = null,
  showTranslationFields = true,
  showContactFields = true,
  showManualTempUniName = true,
}) => {
  const [universityIsLoading, setUniversityIsLoading] = useState(true);
  const { setValue, getValues } = useFormContext();
  const { asyncCall } = useAsyncCall();
  const universityNameTranslated = getValues("primaryUniversity.universityNameTranslated");
  const manualTempUniName = useWatch({ name: "manualTempUniName" });
  const efilingId = useWatch({ name: "efilingId" });
  const baseUniId = useWatch({ name: baseUniversityIdPointer });

  useEffect(() => {
    if (baseUniId) {
      asyncCall({
        promise: selectUniversityById(baseUniId),
        onSuccess: (response) => {
          if (!universityNameTranslated) {
            if (manualTempUniName) {
              setValue("primaryUniversity.universityNameTranslated", manualTempUniName);
            } else {
              setValue("primaryUniversity.universityNameTranslated", response?.bgName);
            }
          }
          setValue("primaryUniversity.university", response);
        },
      });
    } else {
      setValue("primaryUniversity", null);
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [baseUniId]);

  return (
    <FormSection label={titleSection}>
      {viewDataComponent}
      <GridSpg container spacing={1}>
        <GridSpg item xs={universityIsLoading ? 12 : 11}>
          {!baseUniversityId && (
            <EmptyUniversityComponent
              tempDataKey={tempDataKey}
              universityIdPointer={baseUniversityIdPointer}
              searchFormDefaultValues={searchFormDefaultValues}
              showManualTempUniName={showManualTempUniName && !efilingId}
            />
          )}
          {baseUniversityId && (
            <>
              <UniversityViewContent
                universityId={baseUniversityId}
                showAllFields={false}
                withViewSections={false}
                reload={baseUniversityWatcher}
                loaderType={"skeleton"}
                setUniversityIsLoadingFalse={() => {
                  setUniversityIsLoading(false);
                }}
              />
              {showTranslationFields && (
                <GridContainer spacing={4} mt={0}>
                  <GridItem sm={12} md={12}>
                    <InputFormField
                      fieldName={`primaryUniversity.universityNameTranslated`}
                      labelCode={"l.applicationUniversityName"}
                      required={true}
                    />
                  </GridItem>
                  <GridItem sm={12} md={12}>
                    <FacultySelectField
                      universityId={baseUniversityId}
                      tempDataPointer={"primaryUniversity.faculty.id"}
                      tempDataKey={"primaryUniTDK"}
                    />
                  </GridItem>
                </GridContainer>
              )}
              {showContactFields && (
                <GridContainer spacing={4} mt={0}>
                  <GridItem sm={12} md={12}>
                    <InputFormField
                      fieldName={`primaryUniversity.universityContact`}
                      labelCode={"l.university.universityContact"}
                    />
                  </GridItem>
                </GridContainer>
              )}
            </>
          )}
        </GridSpg>
        {baseUniversityId && !universityIsLoading && (
          <GridSpg item xs={1}>
            <UniversitySectionMenuButton
              universityId={baseUniversityId}
              universityIdPointer={baseUniversityIdPointer}
              tempDataKey={tempDataKey}
              withRemove={showManualTempUniName}
            />
          </GridSpg>
        )}
      </GridSpg>
    </FormSection>
  );
};

const EmptyUniversityComponent = ({
  tempDataKey,
  universityIdPointer,
  searchFormDefaultValues,
  showManualTempUniName = false,
}) => {
  const { t } = useTranslation();
  const { setValue } = useFormContext();
  const dispatch = useAppDispatch();
  const [isManual, setIsManual] = useState(false);
  const uniId = useWatch({ name: universityIdPointer });
  const manualTempUniName = useWatch({ name: "manualTempUniName" });

  useEffect(() => {
    if (isNotEmpty(manualTempUniName)) {
      setIsManual(true);
    }
    // eslint-disable-next-line
  }, []);

  useEffect(() => {
    if (isNotEmpty(uniId)) {
      setIsManual(false);
      setValue("manualTempUniName", null);
    }
  }, [setValue, uniId]);

  const handleClick = () => {
    dispatch(
      UniversityControlActions.openSearchUniversityModal({
        universityIdPointer: universityIdPointer,
        tempDataKey,
        searchFormValues: UniversitySearchForm.transformData(searchFormDefaultValues),
      }),
    );
  };

  const handleClickManual = () => {
    setIsManual(true);
  };

  return (
    <>
      <BoxSpg mt={2}>
        <Link onClick={handleClick} underline="hover" fontSize={13} style={{ cursor: "pointer" }}>
          {t("l.btn.selectUniversity")}
        </Link>
      </BoxSpg>
      {showManualTempUniName && !uniId && (
        <>
          <BoxSpg mt={2}>
            <Link onClick={handleClickManual} underline="hover" fontSize={13} style={{ cursor: "pointer" }}>
              {t("l.btn.manualInputUniversity")}
            </Link>
          </BoxSpg>
          {isManual && (
            <GridContainer spacing={4} mt={0}>
              <GridItem sm={12} md={12}>
                <InputFormField fieldName={"manualTempUniName"} labelCode={"l.manualTempUniName"} />
              </GridItem>
            </GridContainer>
          )}
        </>
      )}
    </>
  );
};
export default BaseUniversityFormSection;
