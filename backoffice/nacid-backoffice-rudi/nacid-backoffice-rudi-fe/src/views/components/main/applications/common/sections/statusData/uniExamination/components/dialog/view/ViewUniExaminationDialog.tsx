import {
  ActionTextButton,
  AlertSpg,
  AsyncCallArgs,
  BlockText,
  concatNotEmptyBy,
  EmptyTableInfo,
  GridContainer,
  GridItem,
  GridSkeleton,
  isArrayEmpty,
  isArrayNotEmpty,
  isNotEmpty,
  OptionTableCell,
  sortOptionsArray,
  TableButton,
  useAsyncCall,
  ViewDialog,
  ViewSection,
} from "@duosoftbg/nacid-components";
import useAppDispatch from "../../../../../../../../../../../hooks/redux/base/useAppDispatch";
import useAppSelector from "../../../../../../../../../../../hooks/redux/base/useAppSelector";
import React, { useEffect, useState } from "react";
import { useTranslation } from "react-i18next";
import { getUniversityExamination } from "../../../../../../../../../../../axios/api/services";
import { UniExaminationControlActions } from "../../../../../../../../../../../store/redux/slice/ComponentsControl/uniExaminationControl";
import { Visibility } from "@mui/icons-material";
import {
  LibraryComponentsControlActions,
  ProcessEnvironments,
  TrainingForm,
} from "@duosoftbg/nacid-backoffice-components";
import { Table, TableBody, TableContainer, TableHead } from "@mui/material";
import TableRow from "@mui/material/TableRow";
import TableCell from "@mui/material/TableCell";

const MainDataSectionContent = ({ uniExamination }) => {
  const { t } = useTranslation();

  const uniExaminationTrainingForms = uniExamination.universityExaminationTrainingForms;

  const sortedTrainingForms = isArrayNotEmpty(uniExaminationTrainingForms)
    ? uniExaminationTrainingForms.map((uniForm) => uniForm.trainingForm).sort(sortOptionsArray("index"))
    : [];

  const otherTrainingFormNote = uniExaminationTrainingForms.find(
    (uniForm) => uniForm.trainingForm.id === TrainingForm.OTHER,
  );

  return (
    <ViewSection label={"t.main.data"}>
      <GridContainer spacing={3} mt={0}>
        {uniExamination?.id && (
          <GridItem sm={6} md={4}>
            <BlockText label={"l.identifier"} text={uniExamination.id} />
          </GridItem>
        )}
        {uniExamination?.examinationDate && (
          <GridItem sm={6} md={4}>
            <BlockText label={"l.examinationDate"} text={uniExamination.examinationDate} />
          </GridItem>
        )}
        {uniExamination?.userCreated && (
          <GridItem sm={6} md={4}>
            <BlockText label={"l.uniExamination.userCreated"} text={uniExamination.userCreated} />
          </GridItem>
        )}

        <GridItem sm={6} md={4}>
          <BlockText
            label={"l.uniExamination.isRecognized"}
            text={uniExamination.isRecognized ? t("l.yes") : t("l.no")}
          />
        </GridItem>

        <GridItem sm={6} md={4}>
          <BlockText
            label={"l.uniExamination.isCommunicated"}
            text={uniExamination.isCommunicated ? t("l.yes") : t("l.no")}
          />
        </GridItem>

        <GridItem sm={6} md={4}>
          <BlockText
            label={"l.uniExamination.isJointDegree"}
            text={uniExamination.isJointDegree ? t("l.yes") : t("l.no")}
          />
        </GridItem>

        {uniExamination?.trainingLocation?.id && (
          <GridItem sm={6} md={4}>
            <BlockText label={"l.uniExamination.trainingLocation"} text={uniExamination.trainingLocation.name} />
          </GridItem>
        )}

        {isArrayNotEmpty(uniExamination?.universityExaminationTrainingForms) && (
          <GridItem sm={6} md={4}>
            <BlockText
              label={"l.uniExamination.trainingForm"}
              text={concatNotEmptyBy(", ")(...sortedTrainingForms.map((form) => form.name))}
            />
          </GridItem>
        )}

        {otherTrainingFormNote?.notes && (
          <GridItem sm={6} md={4}>
            <BlockText label={"l.uniExamination.otherTrainingFormNote"} text={otherTrainingFormNote?.notes} />
          </GridItem>
        )}

        {uniExamination?.notes && (
          <GridItem sm={12} md={12}>
            <BlockText label={"l.notes"} text={uniExamination.notes} />
          </GridItem>
        )}
      </GridContainer>
    </ViewSection>
  );
};

const UniversityDataSectionContent = ({ uniExamination }) => {
  return (
    <ViewSection label={"t.university.data"}>
      <GridContainer spacing={3} mt={0}>
        {uniExamination.university?.bgName && (
          <GridItem md={4}>
            <BlockText label={"l.university.bg.name"} text={uniExamination.university?.bgName} />
          </GridItem>
        )}
        {uniExamination.university?.orgName && (
          <GridItem md={4}>
            <BlockText label={"l.university.original.name"} text={uniExamination.university?.orgName} />
          </GridItem>
        )}
        {uniExamination.university?.country?.name && (
          <GridItem md={2}>
            <BlockText label={"l.country"} text={uniExamination.university?.country?.name} />
          </GridItem>
        )}
        {uniExamination.university?.address?.city && (
          <GridItem md={2}>
            <BlockText label={"l.city"} text={uniExamination.university?.address?.city} />
          </GridItem>
        )}
      </GridContainer>
      <GridContainer spacing={0} mt={0}>
        <GridItem sm={12} md={12}>
          <ActionTextButton
            labelCode={"l.university.view"}
            onClick={() => {
              window.open(
                `${ProcessEnvironments.Module.BackOffice.Core}/nomenclatures/universities/view?id=${uniExamination?.university?.id}`,
                "_blank",
              );
            }}
            icon={<Visibility />}
          />
        </GridItem>
      </GridContainer>
    </ViewSection>
  );
};

const CompetentInstitutionDataSectionContent = ({ uniExamination }) => {
  const { t } = useTranslation();
  const dispatch = useAppDispatch();

  const competentInstitutions = uniExamination?.competentInstitutions;

  const handleOpenViewModal = (competentInstitutionId) => {
    dispatch(
      LibraryComponentsControlActions.competentInstitutionControlActions.openModal({
        id: competentInstitutionId,
        modalType: "view",
      }),
    );
  };

  return (
    <ViewSection label={"t.national.competentInstitution.data"}>
      <GridContainer spacing={3} mt={0}>
        <GridItem sm={12} md={12}>
          <TableContainer>
            <Table>
              <TableHead>
                <TableRow>
                  <TableCell style={{ width: 35 }} />
                  <TableCell>{t("l.table.head.name")}</TableCell>
                  <TableCell>{t("l.table.head.country")}</TableCell>
                  <TableCell>{t("l.table.head.city")}</TableCell>
                  <TableCell></TableCell>
                </TableRow>
              </TableHead>
              {isArrayNotEmpty(competentInstitutions) && (
                <TableBody>
                  {competentInstitutions.map((institution, index) => (
                    <TableRow key={`institution-row-${institution.id}`}>
                      <TableCell>{index + 1}</TableCell>
                      <TableCell>{institution.name}</TableCell>
                      <TableCell>{institution.country?.name}</TableCell>
                      <TableCell>{institution.address?.city}</TableCell>
                      <OptionTableCell>
                        <TableButton type={"view"} onClick={() => handleOpenViewModal(institution.id)} />
                      </OptionTableCell>
                    </TableRow>
                  ))}
                </TableBody>
              )}
            </Table>
            {isArrayEmpty(competentInstitutions) && (
              <EmptyTableInfo severity="info">{t("m.empty.list")}</EmptyTableInfo>
            )}
          </TableContainer>
        </GridItem>
      </GridContainer>
    </ViewSection>
  );
};

const UniExaminationViewContent = ({ uniExaminationId }) => {
  const { t } = useTranslation();
  const { asyncCall } = useAsyncCall();
  const [uniExamination, setUniExamination] = useState(null);
  const [error, setError] = useState(false);

  useEffect(() => {
    if (isNotEmpty(uniExaminationId)) {
      const asyncCallArgs: AsyncCallArgs = {
        promise: getUniversityExamination(uniExaminationId),
        onSuccess: (response) => {
          setUniExamination(response);
          setError(false);
        },
        onError: () => {
          setError(true);
        },
      };
      asyncCall(asyncCallArgs);
    }
    // eslint-disable-next-line
  }, [uniExaminationId]);

  if (error) {
    return (
      <AlertSpg mt={3} severity="error">
        {t("m.error.serverFetchingError")}
      </AlertSpg>
    );
  }
  return (
    <>
      {!uniExamination && <GridSkeleton length={15} />}
      {uniExamination && (
        <>
          <UniversityDataSectionContent uniExamination={uniExamination} />
          <MainDataSectionContent uniExamination={uniExamination} />
          <CompetentInstitutionDataSectionContent uniExamination={uniExamination} />
        </>
      )}
    </>
  );
};

const ViewUniExaminationDialog = () => {
  const dispatch = useAppDispatch();

  const { open, uniExaminationId } = useAppSelector((state) => {
    return state.ComponentsControl.uniExaminationControl.modals.view;
  });

  const handleClose = () => {
    dispatch(UniExaminationControlActions.closeViewUniExaminationModal({}));
  };

  if (!open) {
    return null;
  }

  return (
    <ViewDialog
      open={open}
      onClose={handleClose}
      title={"l.viewUniExaminationDialog.title"}
      dialogActionsSpacing={{ pr: 3 }}
    >
      <UniExaminationViewContent uniExaminationId={uniExaminationId} />
    </ViewDialog>
  );
};

export default ViewUniExaminationDialog;
