import { AlertSpg, useAsyncCall, useExternalFormField, useReloadWatcherReader } from "@duosoftbg/nacid-components";
import { useFormContext, useWatch } from "react-hook-form";
import { AppType } from "@duosoftbg/nacid-backoffice-components";
import React, { useEffect, useState } from "react";
import BaseUniversityFormSection from "./BaseUniversityFormSection";
import SecondaryUniversitiesFormSection from "./SecondaryUniversityFormSection";
import { useParams } from "react-router-dom";
import { useTranslation } from "react-i18next";
import { checkEduDataUnfilledUniversities } from "../../../../../../../../axios/api/services";
import EFiledUniversitiesTable from "./components/table/EFiledUniversitiesTable";

type BaseUniversitySectionProps = {
  appType: AppType;
  baseUniversityIdPointer?: string;
  titleSection?: string;
  viewDataComponent?: React.ReactNode;
  searchFormDefaultValues?: any;
  showSecondaryUniversities?: boolean;
  showTranslationFields?: boolean;
  showContactFields?: boolean;
  checkUnfilledUniversities?: boolean;
  showManualTempUniName?: boolean;
};

const UniversitySection = ({
  appType,
  baseUniversityIdPointer = "baseUniversityId",
  titleSection = "t.base.university.details." + appType,
  viewDataComponent = null,
  searchFormDefaultValues = null,
  showSecondaryUniversities = true,
  showTranslationFields = true,
  showContactFields = true,
  checkUnfilledUniversities = false,
  showManualTempUniName = true,
}: BaseUniversitySectionProps) => {
  const { getValues } = useFormContext();
  const tempDataKey = `${appType}-${getValues("applicationId")}`;

  const baseUniversityId = useExternalFormField({ key: tempDataKey, pointer: baseUniversityIdPointer });
  const efilingId = useWatch({ name: "efilingId" });

  const { reloadWatcher: baseUniversityWatcher } = useReloadWatcherReader({
    key: tempDataKey,
    pointer: baseUniversityIdPointer,
  });

  return (
    <>
      {checkUnfilledUniversities && efilingId && <MissingUnisTable />}
      <BaseUniversityFormSection
        baseUniversityWatcher={baseUniversityWatcher}
        baseUniversityIdPointer={baseUniversityIdPointer}
        baseUniversityId={baseUniversityId}
        titleSection={titleSection}
        tempDataKey={tempDataKey}
        viewDataComponent={viewDataComponent}
        searchFormDefaultValues={searchFormDefaultValues}
        showTranslationFields={showTranslationFields}
        showContactFields={showContactFields}
        showManualTempUniName={showManualTempUniName}
      />
      {showSecondaryUniversities && (
        <SecondaryUniversitiesFormSection
          tempDataKey={tempDataKey}
          baseUniversityId={baseUniversityId}
          showTranslationFields={showTranslationFields}
          showContactFields={showContactFields}
        />
      )}
    </>
  );
};

const MissingUnisTable = () => {
  const { asyncCall } = useAsyncCall();
  const { id } = useParams();
  const { t } = useTranslation();
  const primaryUniversityId = useWatch({ name: "primaryUniversity.id" });
  const [eFiledUnisToDisplay, setEFiledUnisToDisplay] = useState(false);

  const { reloadWatcher: submitFormWatcher } = useReloadWatcherReader({
    key: "check-unfilled-unis",
    pointer: "check-unfilled-unis",
  });

  useEffect(() => {
    asyncCall({
      promise: checkEduDataUnfilledUniversities(id),
      onSuccess: (response) => {
        setEFiledUnisToDisplay(response);
      },
    });
  }, [id, primaryUniversityId, submitFormWatcher, asyncCall]);

  if (eFiledUnisToDisplay) {
    return (
      <>
        <AlertSpg severity="warning">{t("m.unfilled.universities")}</AlertSpg>
        <EFiledUniversitiesTable universitiesData={eFiledUnisToDisplay} />
      </>
    );
  } else {
    return null;
  }
};
export default UniversitySection;
