import {
  DividerSpg,
  GridContainer,
  GridItem,
  GridSpg,
  InputFormField,
  useAsyncCall,
  useExternalFormField,
  useReloadWatcherReader,
} from "@duosoftbg/nacid-components";
import { UniversityViewContent } from "@duosoftbg/nacid-backoffice-components";
import React, { useEffect, useState } from "react";
import { useFormContext, useWatch } from "react-hook-form";
import { selectUniversityById } from "../../../../../../../../axios/api/services";
import UniversitySectionMenuButton from "./components/button/UniversitySectionMenuButton";
import FacultySelectField from "../../../components/FacultySelectField";

const SecondaryUniversityFragment = ({
  secondaryUniversityPointer,
  secondaryUniversityIdPointer,
  tempDataKey,
  baseUniversityId,
  showTranslationFields = true,
  showContactFields = true,
}) => {
  const [universityIsLoading, setUniversityIsLoading] = useState(true);
  const { setValue, getValues } = useFormContext();
  const { asyncCall } = useAsyncCall();
  const universityNameTranslated = useWatch({ name: `${secondaryUniversityPointer}.universityNameTranslated` });
  const secondaryUniId = useWatch({ name: secondaryUniversityIdPointer });

  const secondaryUniversityId = useExternalFormField({ key: tempDataKey, pointer: secondaryUniversityIdPointer });

  const { reloadWatcher: secondaryUniversityWatcher } = useReloadWatcherReader({
    key: tempDataKey,
    pointer: secondaryUniversityIdPointer,
  });

  useEffect(() => {
    if (secondaryUniId) {
      //change uni
      let secondaryUniversities = getValues().secondaryUniversities;
      if (
        secondaryUniversities.filter((e) => e.university.id === secondaryUniId).length > 1 ||
        secondaryUniId === baseUniversityId
      ) {
        setValue(secondaryUniversityIdPointer, null);
        setValue(secondaryUniversityPointer, null);
      } else {
        asyncCall({
          promise: selectUniversityById(secondaryUniId),
          onSuccess: (response) => {
            if (!universityNameTranslated) {
              setValue(`${secondaryUniversityPointer}.universityNameTranslated`, response?.bgName);
            }
            setValue(`${secondaryUniversityPointer}.university`, response);
          },
        });
      }
    } else {
      //delete uni
      setValue(secondaryUniversityIdPointer, null);
      setValue(secondaryUniversityPointer, null);
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [secondaryUniId]);

  if (secondaryUniId) {
    return (
      <GridSpg container spacing={1}>
        <GridSpg item xs={universityIsLoading ? 12 : 11}>
          <UniversityViewContent
            universityId={secondaryUniversityId}
            showAllFields={false}
            withViewSections={false}
            reload={secondaryUniversityWatcher}
            loaderType={"skeleton"}
            setUniversityIsLoadingFalse={() => {
              setUniversityIsLoading(false);
            }}
          />
          {showTranslationFields && (
            <GridContainer spacing={4} mt={0}>
              <GridItem sm={12} md={12}>
                <InputFormField
                  fieldName={`${secondaryUniversityPointer}.universityNameTranslated`}
                  labelCode={"l.applicationUniversityName"}
                  required={true}
                />
              </GridItem>
              <GridItem sm={12} md={12}>
                <FacultySelectField
                  universityId={secondaryUniId}
                  tempDataPointer={`${secondaryUniversityPointer}.faculty.id`}
                  tempDataKey={"secondaryUniTDK"}
                />
              </GridItem>
            </GridContainer>
          )}
          {showContactFields && (
            <GridContainer spacing={4} mt={0}>
              <GridItem sm={12} md={12}>
                <InputFormField
                  fieldName={`${secondaryUniversityPointer}.universityContact`}
                  labelCode={"l.university.universityContact"}
                />
              </GridItem>
            </GridContainer>
          )}
          <DividerSpg my={4} />
        </GridSpg>
        {secondaryUniversityId && !universityIsLoading && (
          <GridSpg item xs={1}>
            <UniversitySectionMenuButton
              universityId={secondaryUniversityId}
              universityIdPointer={secondaryUniversityIdPointer}
              tempDataKey={tempDataKey}
              withRemove
            />
          </GridSpg>
        )}
      </GridSpg>
    );
  } else {
    return null;
  }
};
export default SecondaryUniversityFragment;
