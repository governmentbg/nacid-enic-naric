import { useFormContext } from "react-hook-form";
import * as React from "react";
import { useTranslation } from "react-i18next";
import {
  AlertSpg,
  AsyncCallArgs,
  CircularLoader,
  GridContainer,
  GridItem,
  isArrayEmpty,
  isArrayNotEmpty,
  OptionTableCell,
  TableButton,
  TableCellFlag,
  useAsyncCall,
  useReloadWatcherReader,
} from "@duosoftbg/nacid-components";
import { useEffect, useState } from "react";
import { Table, TableBody, TableContainer, TableHead, Tooltip } from "@mui/material";
import TableRow from "@mui/material/TableRow";
import TableCell from "@mui/material/TableCell";
import {
  getUniversityExaminations,
  getUniversityExaminationSubsectionData,
} from "../../../../../../../../../../../axios/api/services";
import { DoneOutline } from "@mui/icons-material";
import { HistorySection, ReloadWatcherObject } from "@duosoftbg/nacid-backoffice-components";
import useAppDispatch from "../../../../../../../../../../../hooks/redux/base/useAppDispatch";
import { UniExaminationControlActions } from "../../../../../../../../../../../store/redux/slice/ComponentsControl/uniExaminationControl";
import { toast } from "react-toastify";

const UniExamHistoryTable = ({ applicationId, universityId, appUniExaminationId }) => {
  const { t } = useTranslation();
  const dispatch = useAppDispatch();
  const { asyncCall } = useAsyncCall();
  const { reset, getValues, setValue } = useFormContext();
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(false);
  const [uniExaminationHistory, setUniExaminationHistory] = useState(null);
  const { reloadWatcher } = useReloadWatcherReader(
    ReloadWatcherObject.UniExamination.change(applicationId, universityId),
  );

  useEffect(() => {
    const asyncCallArgs: AsyncCallArgs = {
      promise: getUniversityExaminations(universityId),
      onSuccess: (response) => {
        setUniExaminationHistory(response);
        setLoading(false);
        setError(false);
      },
      onError: () => {
        setUniExaminationHistory(null);
        setError(true);
        setLoading(false);
      },
    };
    asyncCall(asyncCallArgs);
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [universityId, reloadWatcher]);

  const handleCopyUniExamination = (uniExaminationId) => {
    const asyncCallArgs: AsyncCallArgs = {
      promise: getUniversityExaminationSubsectionData(applicationId, uniExaminationId),
      withGlobalBackdrop: true,
      onSuccess: (response) => {
        const id = getValues("id");
        reset(response);
        setValue("id", id, { shouldDirty: true });
        setValue("copyId", Math.random(), { shouldDirty: true });
        toast.success(t("m.copy.exam.data.success"));
      },
    };
    asyncCall(asyncCallArgs);
  };

  const handleOpenViewModal = (uniExaminationId) => {
    dispatch(UniExaminationControlActions.openViewUniExaminationModal({ uniExaminationId }));
  };

  if (loading) {
    return <CircularLoader />;
  }

  if (error) {
    return <AlertSpg severity="error">{t("m.error.serverFetchingError")}</AlertSpg>;
  }

  return (
    <>
      {isArrayNotEmpty(uniExaminationHistory) && (
        <GridContainer spacing={3} mt={0}>
          <GridItem sm={12} md={12}>
            <TableContainer>
              <Table>
                <TableHead>
                  <TableRow>
                    <TableCell>{t("l.table.head.number")}</TableCell>
                    <TableCell>{t("l.date")}</TableCell>
                    <TableCell>{t("l.table.head.isRecognized")}</TableCell>
                    <TableCell>{t("l.notes")}</TableCell>
                    <TableCell></TableCell>
                  </TableRow>
                </TableHead>

                <TableBody>
                  {uniExaminationHistory.map((historyRecord, index) => (
                    <TableRow key={"historyRecord-row-" + historyRecord.id}>
                      <TableCell>{index + 1}</TableCell>
                      <TableCell>{historyRecord.examinationDate}</TableCell>
                      <TableCellFlag flagValue={historyRecord.isRecognized} />
                      <TableCell>{historyRecord.notes}</TableCell>
                      <OptionTableCell>
                        {historyRecord.id === appUniExaminationId && (
                          <Tooltip title={t("l.uniExamination.current")}>
                            <DoneOutline />
                          </Tooltip>
                        )}
                        {historyRecord.id !== appUniExaminationId && (
                          <>
                            <TableButton
                              type={"view"}
                              onClick={() => {
                                handleOpenViewModal(historyRecord.id);
                              }}
                            />
                            <TableButton
                              type={"copy"}
                              title={"l.btn.copy.uniExamination"}
                              onClick={() => handleCopyUniExamination(historyRecord.id)}
                            />
                          </>
                        )}
                      </OptionTableCell>
                    </TableRow>
                  ))}
                </TableBody>
              </Table>
            </TableContainer>
          </GridItem>
        </GridContainer>
      )}
      {isArrayEmpty(uniExaminationHistory) && (
        <div style={{ marginTop: "10px" }}>
          <AlertSpg severity="info">{t("m.empty.list")}</AlertSpg>
        </div>
      )}
    </>
  );
};

const UniExamListSection = ({ applicationId }) => {
  const { getValues } = useFormContext();
  const university = getValues("university");
  const appUniExaminationId = getValues("id");

  return (
    <HistorySection sectionLabel={"t.applicationUniExaminationHistory.details"}>
      <UniExamHistoryTable
        applicationId={applicationId}
        universityId={university.id}
        appUniExaminationId={appUniExaminationId}
      />
    </HistorySection>
  );
};
export default UniExamListSection;
