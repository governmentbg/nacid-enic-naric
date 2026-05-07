import {
  SelectFormField,
  useAsyncCall,
  useExternalFormField,
  useReloadWatcherReader,
} from "@duosoftbg/nacid-components";
import React, { useEffect, useState } from "react";
import { useTranslation } from "react-i18next";
import useAppDispatch from "../../../../../../hooks/redux/base/useAppDispatch";
import { IconButton, Tooltip } from "@mui/material";
import AddIcon from "@mui/icons-material/Add";
import { getUniversityFaculties } from "../../../../../../axios/api/services";
import { LibraryComponentsControlActions } from "@duosoftbg/nacid-backoffice-components";

const FacultySelectField = ({ tempDataKey, tempDataPointer, universityId }) => {
  const { t } = useTranslation();
  const dispatch = useAppDispatch();
  const { asyncCall } = useAsyncCall();
  const [facultyOptions, setFacultyOptions] = useState([]);

  useExternalFormField({ key: tempDataKey, pointer: tempDataPointer });

  const { reloadWatcher: gdtWatcher } = useReloadWatcherReader({
    key: tempDataKey,
    pointer: tempDataPointer,
  });

  useEffect(() => {
    if (universityId) {
      asyncCall({
        promise: getUniversityFaculties(universityId),
        processResponseErrors: false,
        onSuccess: (response) => {
          setFacultyOptions(response);
        },
      });
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [asyncCall, universityId, gdtWatcher]);

  const handleClickAdd = () => {
    dispatch(
      LibraryComponentsControlActions.facultyCreationActions.openFacultyCreationModal({
        tempDataKey,
        tempDataPointer,
        universityId,
      }),
    );
  };

  return (
    <>
      <div style={{ width: "100%", position: "relative" }}>
        <div style={{ width: "calc(100% - 50px)" }}>
          <SelectFormField
            required={false}
            isDisabled={false}
            fieldName={tempDataPointer}
            labelCode={"l.faculty"}
            addEmptyOption={true}
            selectOptions={facultyOptions}
          />
        </div>
        <div style={{ width: "50px", position: "absolute", right: -2, top: -2 }}>
          <Tooltip title={t("l.add.faculty")}>
            <IconButton color="primary" onClick={handleClickAdd}>
              <AddIcon />
            </IconButton>
          </Tooltip>
        </div>
      </div>
    </>
  );
};
export default FacultySelectField;
