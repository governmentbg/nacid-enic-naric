import {
  AlertSpg,
  BoxSpg,
  DividerSpg,
  ErrorMessage,
  FormSection,
  GridSkeleton,
  GridSpg,
  InputFormField,
  useAsyncCall,
  useExternalFormField,
  useReloadWatcherReader,
} from "@duosoftbg/nacid-components";
import { useFormContext } from "react-hook-form";
import React, { useEffect, useState } from "react";
import {
  AppType,
  CoreApiServicesBase,
  LibraryComponentsControlActions,
  PersonSectionMenuButton,
  PersonView,
  ReloadWatcherObject,
  PersonSearchForm,
  LoadPersonDataProps,
} from "@duosoftbg/nacid-backoffice-components";
import { useTranslation } from "react-i18next";
import { Link } from "@mui/material";
import useAppDispatch from "../../../../../../../../../../hooks/redux/base/useAppDispatch";

type DiplomaOwnerSectionProps = {
  appType: AppType;
  pointer: string;
  titleSection?: string;
  onlyViewMode?: boolean;
  viewDataComponent?: React.ReactNode;
  searchFormDefaultValues?: any;
  loadPersonData?: LoadPersonDataProps;
  withEan?: boolean;
};

const DiplomaOwnerSection = ({
  appType,
  pointer,
  titleSection = "l.diplomaOwner",
  onlyViewMode = false,
  viewDataComponent = null,
  searchFormDefaultValues = null,
  loadPersonData = null,
  withEan = false,
}: DiplomaOwnerSectionProps) => {
  const { t } = useTranslation();
  const { asyncCall } = useAsyncCall();
  const [person, setPerson] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(false);

  const { getValues } = useFormContext();
  const tempDataKey = `${appType}-${getValues("applicationId")}`;
  const diplomaOwnerId = useExternalFormField({ key: tempDataKey, pointer: pointer });
  const { reloadWatcher } = useReloadWatcherReader(ReloadWatcherObject.Person.id(diplomaOwnerId));

  useEffect(() => {
    if (diplomaOwnerId) {
      asyncCall({
        promise: CoreApiServicesBase.getPerson(diplomaOwnerId),
        processResponseErrors: false,
        onSuccess: (response) => {
          setPerson(response);
          setLoading(false);
          setError(false);
        },
        onError: () => {
          setError(true);
          setLoading(false);
        },
      });
    } else {
      setLoading(false);
      setPerson(null);
    }
  }, [asyncCall, diplomaOwnerId, reloadWatcher]);

  if (loading) {
    return (
      <FormSection label={titleSection}>
        <GridSkeleton length={15} />
      </FormSection>
    );
  }

  if (error) {
    return (
      <FormSection label={titleSection}>
        <AlertSpg mt={3} severity="error">
          {t("m.error.serverFetchingError")}
        </AlertSpg>
      </FormSection>
    );
  }

  return (
    <FormSection label={titleSection}>
      {viewDataComponent}
      {!person && (
        <EmptyDiplomaOwner
          tempDataKey={tempDataKey}
          diplomaOwnerIdPointer={pointer}
          searchFormDefaultValues={searchFormDefaultValues}
          loadPersonData={loadPersonData}
        />
      )}
      {person && (
        <>
          <DiplomaOwner
            diplomaOwnerIdPointer={pointer}
            tempDataKey={tempDataKey}
            person={person}
            onlyViewMode={onlyViewMode}
            loadPersonData={loadPersonData}
          />
          {withEan && <DiplomaOwnerEan />}
        </>
      )}
    </FormSection>
  );
};

const DiplomaOwner = ({ person, onlyViewMode, tempDataKey, diplomaOwnerIdPointer, loadPersonData }) => {
  return (
    <GridSpg container spacing={1}>
      <GridSpg item xs={onlyViewMode ? 12 : 11}>
        <PersonView person={person} />
      </GridSpg>
      {!onlyViewMode && (
        <GridSpg item xs={1}>
          <PersonSectionMenuButton
            personId={person.id}
            personPointer={diplomaOwnerIdPointer}
            tempDataKey={tempDataKey}
            loadPersonData={loadPersonData}
          />
        </GridSpg>
      )}
    </GridSpg>
  );
};

const EmptyDiplomaOwner = ({ tempDataKey, diplomaOwnerIdPointer, searchFormDefaultValues, loadPersonData }) => {
  const { t } = useTranslation();
  const dispatch = useAppDispatch();
  const handleClick = () => {
    dispatch(
      LibraryComponentsControlActions.personControlActions.openSearchPersonModal({
        personPointer: diplomaOwnerIdPointer,
        tempDataKey,
        searchFormValues: PersonSearchForm.transformData(searchFormDefaultValues),
        loadPersonData,
      }),
    );
  };

  return (
    <>
      <BoxSpg mt={2}>
        <Link onClick={handleClick} underline="hover" fontSize={13} style={{ cursor: "pointer" }}>
          {t("l.btn.addNewDiplomaOwner")}
        </Link>
        <ErrorMessage pointer="diplomaOwner" />
      </BoxSpg>
    </>
  );
};

const DiplomaOwnerEan = () => {
  return (
    <>
      <DividerSpg mt={3} />

      <GridSpg container spacing={1}>
        <GridSpg item xs={12} mt={4}>
          <InputFormField fieldName={"diplomaOwnerEan"} labelCode={"l.diplomaOwnerEan"} />
        </GridSpg>
      </GridSpg>
    </>
  );
};
export default DiplomaOwnerSection;
