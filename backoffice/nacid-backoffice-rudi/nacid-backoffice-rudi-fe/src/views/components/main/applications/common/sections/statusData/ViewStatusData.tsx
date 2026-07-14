import { useTranslation } from "react-i18next";
import React, { Fragment, useEffect } from "react";
import {
  AccordionItemBox,
  AccordionSummaryStld,
  DividerSpg,
  GridContainer,
  isArrayNotEmpty,
  LabeledDataItem,
  TextSection,
} from "@duosoftbg/nacid-components";
import { Accordion, AccordionDetails, Typography } from "@mui/material";
import { ExpandMore } from "@mui/icons-material";
import useAppSelector from "../../../../../../../hooks/redux/base/useAppSelector";
import { educationLevelsThunk, ViewAttachmentsListTable } from "@duosoftbg/nacid-backoffice-components";
import useAppDispatch from "../../../../../../../hooks/redux/base/useAppDispatch";

const ViewStatusData = () => {
  const { t } = useTranslation();
  const dispatch = useAppDispatch();

  const viewData = useAppSelector((state) => {
    return state["ViewData"];
  });

  useEffect(() => {
    dispatch(educationLevelsThunk());
  }, [dispatch]);

  const thunkStateEduLevels = useAppSelector((state) => {
    return state.ThunkData.educationLevels.data;
  });

  const selectEduLevel = (id) => {
    if (isArrayNotEmpty(thunkStateEduLevels)) {
      let foundOption = thunkStateEduLevels.find((element) => element.id === id);
      return foundOption?.name;
    }
  };

  const application = viewData.data?.application;
  const legalReason = viewData.data?.legalReason?.name;
  const submittedDocs = viewData.data?.submittedDocs;
  const applicationRecognizedDetails = viewData.data?.applicationRecognizedDetails;
  const recognizedQualification = applicationRecognizedDetails?.recognizedQualification;
  const profGroup = applicationRecognizedDetails?.profGroup?.name;
  const profGroupEducationArea = applicationRecognizedDetails?.profGroup?.educationArea?.name;
  const recognizedEduLevel = applicationRecognizedDetails?.recognizedEduLevel;
  const recognizedSpecialities = viewData.data?.recognizedSpecialities;
  const statusName = application?.status?.name;
  const docflowStatusName = application?.docflowStatus?.name;
  const trainingCourse = viewData.data?.trainingCourse;
  const trainingCourseUniversityExaminations = trainingCourse?.trainingCourseUniversityExaminations;
  const trainingCourseProgramExamination = trainingCourse?.programExamination;
  const trainingLocations = trainingCourse?.trainingLocations;
  const diplomaExamination = trainingCourse?.diplomaExamination;

  return (
    <AccordionItemBox mt={1}>
      <Accordion defaultExpanded={false}>
        <AccordionSummaryStld expandIcon={<ExpandMore />}>
          <Typography variant={"h4"}>{t("l.status")}</Typography>
        </AccordionSummaryStld>
        {(statusName || docflowStatusName) && (
          <AccordionDetails>
            <TextSection label={"l.status"} withDivider>
              <GridContainer>
                <LabeledDataItem labelCode={"t.applicationStatus.details"} data={statusName} />
                <LabeledDataItem labelCode={"l.table.head.docflowStatusName"} data={docflowStatusName} />
                <LabeledDataItem labelCode={"l.legal.reason"} data={legalReason} />
                <LabeledDataItem labelCode={"l.submittedDocs"} data={submittedDocs} />
                <LabeledDataItem labelCode={"l.recognized.educationLevel"} data={selectEduLevel(recognizedEduLevel)} />
                <LabeledDataItem labelCode={"l.recognized.profGroup"} data={profGroup} />
                <LabeledDataItem labelCode={"l.profGroup.educationArea"} data={profGroupEducationArea} />
                <LabeledDataItem labelCode={"l.recognized.recognizedQualification"} data={recognizedQualification} />
                <LabeledDataItem
                  labelCode={"l.recognized.specialities"}
                  data={recognizedSpecialities.map((rs) => rs?.speciality).join(", ")}
                />
              </GridContainer>
            </TextSection>
          </AccordionDetails>
        )}
        {trainingCourseUniversityExaminations && trainingCourseUniversityExaminations.length > 0 && (
          <AccordionDetails>
            <TextSection label={"t.appSubSections.uniExam"} withDivider>
              {trainingCourseUniversityExaminations.map((examination, index) => (
                <Fragment key={"uni-exam-" + index}>
                  <GridContainer>
                    <LabeledDataItem
                      labelCode={"l.university.bg.name"}
                      data={examination.universityExamination?.university?.bgName}
                    />
                    <LabeledDataItem
                      labelCode={"l.university.original.name"}
                      data={examination.universityExamination?.university?.orgName}
                    />
                    <LabeledDataItem
                      labelCode={"l.country"}
                      data={examination.universityExamination?.university?.country?.name}
                    />
                    <LabeledDataItem
                      labelCode={"l.city"}
                      data={examination.universityExamination?.university?.address?.city}
                    />
                    <LabeledDataItem
                      labelCode={"l.examinationDate"}
                      data={examination?.universityExamination?.examinationDate}
                    />
                    <LabeledDataItem
                      labelCode={"l.uniExamination.isRecognized"}
                      data={examination?.universityExamination?.isRecognized ? t("l.yes") : t("l.no")}
                    />
                    <LabeledDataItem
                      labelCode={"l.uniExamination.isCommunicated"}
                      data={examination?.universityExamination?.isCommunicated ? t("l.yes") : t("l.no")}
                    />
                    <LabeledDataItem
                      labelCode={"l.uniExamination.isJointDegree"}
                      data={examination?.universityExamination?.isJointDegree ? t("l.yes") : t("l.no")}
                    />
                    <LabeledDataItem
                      labelCode={"l.uniExamination.trainingLocation"}
                      data={examination?.universityExamination?.trainingLocation?.name}
                    />
                    <LabeledDataItem
                      labelCode={"l.uniExamination.trainingForm"}
                      data={examination?.universityExamination?.universityExaminationTrainingForms
                        .map((tf) => tf?.trainingForm?.name)
                        .join(", ")}
                    />
                    <LabeledDataItem
                      labelCode={"l.uniExamination.competentInstitutions"}
                      data={examination?.universityExamination?.competentInstitutions.map((ci) => ci?.name).join(", ")}
                    />
                    <LabeledDataItem
                      sm={12}
                      md={12}
                      labelCode={"l.notes"}
                      data={examination?.universityExamination?.notes}
                    />
                  </GridContainer>
                  {examination?.universityExamination?.attachedDocs && (
                    <GridContainer>
                      <ViewAttachmentsListTable attachments={examination?.universityExamination?.attachedDocs} />
                    </GridContainer>
                  )}
                  <DividerSpg />
                </Fragment>
              ))}
            </TextSection>
          </AccordionDetails>
        )}
        <AccordionDetails>
          <TextSection label={"t.appSubSections.programExam"} withDivider>
            <GridContainer>
              <LabeledDataItem
                labelCode={"l.program.isLegitimate"}
                data={trainingCourseProgramExamination?.isLegitimate ? t("l.yes") : t("l.no")}
              />
              <LabeledDataItem
                labelCode={"l.program.programType"}
                data={trainingCourseProgramExamination?.programType?.name}
              />
            </GridContainer>
          </TextSection>
        </AccordionDetails>
        <AccordionDetails>
          <TextSection label={"t.appSubSections.trainingLocationExam"} withDivider>
            <GridContainer>
              <LabeledDataItem
                sm={12}
                md={12}
                labelCode={"l.trainingLocationExam.isLegitimate"}
                data={trainingCourse?.trainingLocationExamination?.isLegitimate ? t("l.yes") : t("l.no")}
              />
            </GridContainer>
            <GridContainer>
              {trainingLocations.map((location, index) => (
                <Fragment key={"tl-" + index}>
                  <LabeledDataItem
                    labelCode={"l.trainingLocationExam.locationPlace"}
                    data={
                      location?.city ? location?.country?.name + " (" + location?.city + ")" : location?.country?.name
                    }
                  />
                  <LabeledDataItem
                    labelCode={"l.trainingLocationExam.trainingInstitution"}
                    data={location?.examinationTrainingInstitution?.name}
                  />
                </Fragment>
              ))}
            </GridContainer>
          </TextSection>
        </AccordionDetails>
        {diplomaExamination && (
          <AccordionDetails>
            <TextSection label={"t.appSubSections.diplomaExam"} withDivider>
              <GridContainer>
                {diplomaExamination?.competentInstitution?.name &&
                  diplomaExamination?.competentInstitution?.country?.name && (
                    <LabeledDataItem
                      sm={12}
                      md={12}
                      labelCode={"l.diplomaExam.competentInstitution"}
                      data={
                        diplomaExamination?.competentInstitution?.name +
                        " / " +
                        diplomaExamination?.competentInstitution?.country?.name
                      }
                    />
                  )}
                <LabeledDataItem labelCode={"l.examinationDate"} data={diplomaExamination?.examinationDate} />
                <LabeledDataItem
                  sm={12}
                  md={12}
                  labelCode={"l.diplomaExam.isInstitutionCommunicated"}
                  data={diplomaExamination?.isInstitutionCommunicated ? t("l.yes") : t("l.no")}
                />
                <LabeledDataItem
                  sm={12}
                  md={12}
                  labelCode={"l.diplomaExam.isUniversityCommunicated"}
                  data={diplomaExamination?.isUniversityCommunicated ? t("l.yes") : t("l.no")}
                />
                <LabeledDataItem
                  sm={12}
                  md={12}
                  labelCode={"l.diplomaExam.isStateApproved"}
                  data={diplomaExamination?.isStateApproved ? t("l.yes") : t("l.no")}
                />
                <LabeledDataItem
                  sm={12}
                  md={12}
                  labelCode={"l.diplomaExam.isFoundInRegister"}
                  data={diplomaExamination?.isFoundInRegister ? t("l.yes") : t("l.no")}
                />
                <LabeledDataItem
                  sm={12}
                  md={12}
                  labelCode={"l.diplomaExam.isAuthentic"}
                  data={diplomaExamination?.isAuthentic ? t("l.yes") : t("l.no")}
                />
                <LabeledDataItem sm={12} md={12} labelCode={"l.notes"} data={diplomaExamination?.notes} />
              </GridContainer>
              {diplomaExamination?.attachedDocs && (
                <GridContainer>
                  <ViewAttachmentsListTable attachments={diplomaExamination?.attachedDocs} />
                </GridContainer>
              )}
            </TextSection>
          </AccordionDetails>
        )}
      </Accordion>
    </AccordionItemBox>
  );
};

export default ViewStatusData;
