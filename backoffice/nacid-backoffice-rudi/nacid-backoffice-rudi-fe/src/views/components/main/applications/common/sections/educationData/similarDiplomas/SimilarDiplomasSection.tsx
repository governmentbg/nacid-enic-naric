import TableRow from "@mui/material/TableRow";
import TableCell from "@mui/material/TableCell";
import { Table, TableBody, TableContainer, TableHead, Typography } from "@mui/material";
import React, { Fragment, useEffect, useState } from "react";
import {
  AlertSpg,
  AsyncCallArgs,
  FormSection,
  GridContainer,
  GridItem,
  GridSpg,
  isArrayNotEmpty,
  isEmpty,
  OptionTableCell,
  TableButton,
  useAsyncCall,
} from "@duosoftbg/nacid-components";
import { useTranslation } from "react-i18next";
import { AppSubTypeCode } from "@duosoftbg/nacid-backoffice-components";
import { getSimilarDiplomas } from "../../../../../../../../axios/api/services";
import { useWatch } from "react-hook-form";

const SimilarDiplomasSection = ({
  isEfiling = false,
  applicationIdPointer = "applicationId",
  diplomaDatePointer = "diplomaDate",
  diplomaCountryNamePointer = "primaryUniversity.university.country.name",
  eduLevelPointer = "originalEduLevelName",
  eduLevelTranslatedPointer = "originalEduLevelTranslated",
  ownerCivilIdPointer = "diplomaOwnerCivilId",
  diplomaOwnerEanPointer = "diplomaOwnerEan",
  ownerFirstNamePointer = "diplomaOwnerFirstName",
  ownerMiddleNamePointer = "diplomaOwnerMiddleName",
  ownerLastNamePointer = "diplomaOwnerLastName",
  primaryUniversityNamePointer = "primaryUniversity.university.bgName",
  specialitiesPointer = "trainingCourseSpecialities",
  birthDatePointer = "diplomaOwnerBirthDate",
  birthCountryPointer = "diplomaOwnerBirthCountry",
}) => {
  const { t } = useTranslation();
  const { asyncCall } = useAsyncCall();
  const [error, setError] = useState(false);
  const [similarDiplomas, setSimilarDiplomas] = useState(null);

  const applicationId = useWatch({ name: applicationIdPointer });
  const diplomaDate = useWatch({ name: diplomaDatePointer });
  const countryName = useWatch({ name: diplomaCountryNamePointer });
  const eduLevel = useWatch({ name: eduLevelPointer });
  const eduLevelTranslated = useWatch({ name: eduLevelTranslatedPointer });
  const civilId = useWatch({ name: ownerCivilIdPointer });
  const diplomaOwnerEan = useWatch({ name: diplomaOwnerEanPointer });
  const ownerFirstName = useWatch({ name: ownerFirstNamePointer });
  const ownerMiddleName = useWatch({ name: ownerMiddleNamePointer });
  const ownerLastName = useWatch({ name: ownerLastNamePointer });
  const birthDate = useWatch({ name: birthDatePointer });
  const birthCountry = useWatch({ name: birthCountryPointer });
  const primaryUniversityName = useWatch({ name: primaryUniversityNamePointer });
  const trainingCourseSpecialities = useWatch({ name: specialitiesPointer });

  useEffect(() => {
    const asyncCallArgs: AsyncCallArgs = {
      promise: getSimilarDiplomas(
        applicationId,
        diplomaDate,
        isEfiling && isEmpty(countryName) ? primaryUniversityName.split(", ")[2] : countryName,
        eduLevel,
        eduLevelTranslated,
        civilId,
        ownerFirstName,
        ownerLastName,
        birthDate,
        birthCountry,
        diplomaOwnerEan,
      ),
      onSuccess: (response) => {
        setSimilarDiplomas(response);
        setError(false);
      },
      onError: () => {
        setSimilarDiplomas(null);
        setError(true);
      },
    };
    asyncCall(asyncCallArgs);
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [
    applicationId,
    diplomaDate,
    countryName,
    primaryUniversityName,
    eduLevel,
    eduLevelTranslated,
    civilId,
    ownerFirstName,
    ownerLastName,
    birthDate,
    birthCountry,
    diplomaOwnerEan,
  ]);

  if (error) {
    return (
      <FormSection label={"t.similar.diplomas"}>
        <AlertSpg mt={3} severity="error">
          {t("m.error.serverFetchingError")}
        </AlertSpg>
      </FormSection>
    );
  }

  if (isArrayNotEmpty(similarDiplomas)) {
    return (
      <FormSection label={"t.similar.diplomas"}>
        <GridSpg container spacing={1}>
          <GridSpg item xs={12}>
            <GridContainer spacing={4} mt={0}>
              <GridItem sm={12} md={12}>
                <TableContainer>
                  <Table>
                    <TableHead>
                      <TableRow>
                        <TableCell>{t("l.table.head.number")}</TableCell>
                        <TableCell>{t("l.similarDiploma.number")}</TableCell>
                        <TableCell>{t("l.similarDiploma.owner")}</TableCell>
                        <TableCell>{t("l.similarDiploma.country")}</TableCell>
                        <TableCell>{t("l.similarDiploma.university")}</TableCell>
                        <TableCell>{t("l.similarDiploma.eduLevel")}</TableCell>
                        <TableCell>{t("l.similarDiploma.speciality")}</TableCell>
                        <TableCell>{t("l.similarDiploma.graduationYear")}</TableCell>
                        <TableCell></TableCell>
                      </TableRow>
                    </TableHead>

                    <TableBody>
                      {similarDiplomas.map((similarDiploma, index) => (
                        <TableRow key={"similar-diploma-" + similarDiploma.apnId}>
                          <TableCell>{index + 1}</TableCell>
                          <TableCell>{similarDiploma?.number}</TableCell>
                          <TableCell>
                            <Typography style={civilId === similarDiploma?.diplomaOwnerCivilId ? { color: "red" } : {}}>
                              {similarDiploma?.diplomaOwnerCivilId}
                            </Typography>
                            <Typography
                              style={
                                ownerFirstName?.toLowerCase() === similarDiploma?.diplomaOwnerFirstName?.toLowerCase()
                                  ? { color: "red" }
                                  : {}
                              }
                            >
                              {similarDiploma?.diplomaOwnerFirstName}
                            </Typography>
                            <Typography
                              style={
                                ownerMiddleName?.toLowerCase() === similarDiploma?.diplomaOwnerMiddleName?.toLowerCase()
                                  ? { color: "red" }
                                  : {}
                              }
                            >
                              {similarDiploma?.diplomaOwnerMiddleName}
                            </Typography>
                            <Typography
                              style={
                                ownerLastName?.toLowerCase() === similarDiploma?.diplomaOwnerLastName?.toLowerCase()
                                  ? { color: "red" }
                                  : {}
                              }
                            >
                              {similarDiploma?.diplomaOwnerLastName}
                            </Typography>
                            {similarDiploma?.diplomaOwnerEan && (
                              <Typography
                                style={diplomaOwnerEan === similarDiploma?.diplomaOwnerEan ? { color: "red" } : {}}
                              >
                                {"ЕАН: " + similarDiploma?.diplomaOwnerEan}
                              </Typography>
                            )}
                          </TableCell>
                          <TableCell>
                            <Typography
                              style={
                                countryName?.toLowerCase() === similarDiploma?.country?.toLowerCase() ||
                                primaryUniversityName?.split(", ")[2]?.toLowerCase() ===
                                  similarDiploma?.country?.toLowerCase()
                                  ? { color: "red" }
                                  : {}
                              }
                            >
                              {similarDiploma?.country}
                            </Typography>
                          </TableCell>
                          <TableCell>
                            <Typography
                              style={
                                primaryUniversityName === similarDiploma?.university ||
                                primaryUniversityName?.split(", ")[0] === similarDiploma?.university
                                  ? { color: "red" }
                                  : {}
                              }
                            >
                              {similarDiploma?.university}
                            </Typography>
                          </TableCell>
                          <TableCell>
                            <Typography
                              style={
                                eduLevel?.toLowerCase() === similarDiploma?.eduLevel?.toLowerCase()
                                  ? { color: "red" }
                                  : {}
                              }
                            >
                              {similarDiploma?.eduLevel ? similarDiploma?.eduLevel : ""}
                            </Typography>
                            <Typography
                              style={
                                eduLevelTranslated?.toLowerCase() === similarDiploma?.eduLevelTranslated?.toLowerCase()
                                  ? { color: "red" }
                                  : {}
                              }
                            >
                              {similarDiploma?.eduLevelTranslated ? "(" + similarDiploma?.eduLevelTranslated + ")" : ""}
                            </Typography>
                          </TableCell>
                          <TableCell>
                            {similarDiploma?.specialities?.map((spec) => (
                              <Fragment key={"spec-fr-" + Math.random()}>
                                <span
                                  key={"spec" + Math.random()}
                                  style={
                                    trainingCourseSpecialities?.some((x) => x.speciality === spec.speciality)
                                      ? { color: "red" }
                                      : {}
                                  }
                                >
                                  {spec.speciality}
                                </span>
                                {spec.originalSpeciality && (
                                  <span
                                    key={"orig-spec" + Math.random()}
                                    style={
                                      trainingCourseSpecialities?.some(
                                        (x) => x?.originalSpeciality === spec?.originalSpeciality,
                                      )
                                        ? { color: "red" }
                                        : {}
                                    }
                                  >
                                    {" (" + spec?.originalSpeciality + ")"}
                                  </span>
                                )}
                                <br />
                              </Fragment>
                            ))}
                          </TableCell>
                          <TableCell>
                            <Typography
                              style={
                                // eslint-disable-next-line eqeqeq
                                diplomaDate?.split(".")[2] == similarDiploma?.graduationYear ? { color: "red" } : {}
                              }
                            >
                              {similarDiploma?.graduationYear}
                            </Typography>
                          </TableCell>
                          <OptionTableCell>
                            <TableButton
                              type={"view"}
                              to={generateUrl(similarDiploma?.appSubTypeCode, similarDiploma.apnId)}
                              target={"_blank"}
                            />
                          </OptionTableCell>
                        </TableRow>
                      ))}
                    </TableBody>
                  </Table>
                </TableContainer>
              </GridItem>
            </GridContainer>
          </GridSpg>
        </GridSpg>
      </FormSection>
    );
  } else {
    return null;
  }
};

const urlsConfig = {
  [AppSubTypeCode.SAR]: `/sar-applications/edit/{applicationId}`,
  [AppSubTypeCode.DOCREC]: `/docrec-applications/edit/{applicationId}`,
  [AppSubTypeCode.UDIREC]: `/udirec-applications/edit/{applicationId}/`,
};

const generateUrl = (appSubTypeCode, applicationId) => {
  return urlsConfig[appSubTypeCode].replace("{applicationId}", applicationId);
};

export default SimilarDiplomasSection;
