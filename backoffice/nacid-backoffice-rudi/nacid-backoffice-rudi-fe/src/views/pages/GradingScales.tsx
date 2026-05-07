import { useTranslation } from "react-i18next";
import {
  AsyncCallArgs,
  BoxedHeader,
  BoxSpg,
  CardSpg,
  CountrySelectField,
  DependencyAutocompleteFormField1Param,
  GridContainer,
  GridItem,
  initializeYup,
  NacidTableSimple,
  useAsyncCall,
  useReactHookForm,
  YearFormField,
} from "@duosoftbg/nacid-components";
import CardContent from "@mui/material/CardContent/CardContent";
import { useEffect, useState } from "react";
import useAppSelector from "../../hooks/redux/base/useAppSelector";
import { countryDataThunk } from "../../store/redux/slice/AppData/countryData";
import useAppDispatch from "../../hooks/redux/base/useAppDispatch";
import { FormProvider, useFormContext, useWatch } from "react-hook-form";
import ArrayFormControl from "../components/common/ArrayFormControl";
import SubjectFormFields from "../components/common/SubjectFormFields";
import { calculateGrade, getGradeData, getGradeScaleInfo, getDiplomaPdf } from "../../axios/api/services";
import { Button, TableBody, TableCell, TableRow, Tooltip, Typography } from "@mui/material";
import { GridRowsProp } from "@mui/x-data-grid";
import * as yup from "yup";
import React from "react";
import PageWrapper from "../components/common/layout/PageWrapper";

const GradingScales = () => {
  const { t } = useTranslation();
  const dispatch = useAppDispatch();
  const [rows, setRows] = useState<GridRowsProp>([]);
  const [diplomaDetails, setDiplomaDetails] = useState<any>();

  const countryDataState = useAppSelector((state) => {
    return state.AppData.countryData;
  });
  const initialEducationSubject = {
    subject: {
      name: "",
    },
    grade: "",
  };

  const initValues = {
    country: {
      id: "",
    },
    scala: {
      id: "",
    },
    year: "",
    educationSubject: [initialEducationSubject],
  };

  const valuesSchema = () => {
    initializeYup(yup);
    return yup.object({
      country: yup.object({
        id: yup.string().required(),
      }),
      scala: yup.mixed().when(["country.id", "year"], {
        is: (countryId, year) => countryId && year,
        then: yup.object({
          id: yup.string().nullable().required(),
        }),
      }),
      year: yup.string().required(),
      educationSubject: yup.array().of(
        yup.object().shape({
          subject: yup.object({
            name: yup.string().required(),
          }),
          grade: yup.string().required(),
        }),
      ),
    });
  };

  const { methods, handleSubmit } = useReactHookForm({
    defaultValues: initValues,
    validationSchema: valuesSchema,
  });

  useEffect(() => {
    dispatch(countryDataThunk());
  }, [dispatch]);

  const onSubmit = async (data) => {
    const postData = {
      countryCode: data.country.id,
      year: data.year,
      scalaId: data.scala.id,
      subjects: data.educationSubject.map((s) => ({
        subjectName: s.subject.name,
        subjectGrade: s.grade,
      })),
    };
    setDiplomaDetails(postData);
    const response = await calculateGrade(postData)();
    const responseWithId = response.map((row, index) => ({ id: index + 1, ...row }));
    setRows(responseWithId);
  };

  const headCells = [
    { id: "subjectName", label: t("h.subject.name"), sortable: false },
    { id: "subjectGrade", label: t("h.subject.grade.origin"), sortable: false },
    { id: "subjectGradeBg", label: t("h.subject.grade.bg"), sortable: false },
    { id: "subjectGradeBgText", label: t("h.subject.grade.bg.text"), sortable: false },
  ];

  const gradeFormater = (grade) => {
    let a = parseFloat(grade);
    return a ? a.toFixed(2) : t("l.subject.grade.nan");
  };

  const handleOpenPdf = async (data) => {
    try {
      const response = await getDiplomaPdf(data)();
      const blob = new Blob([response.data], {
        type: response?.headers?.["content-type"] || "application/pdf",
      });
      const objectUrl = URL.createObjectURL(blob);
      const frameId = "diploma.pdf";
      const newWindow = window.open("", "pdf-preview-tab");

      if (newWindow) {
        newWindow.document.write(`
            <head>      
              <title>${t("l.btn.pdf")}</title>
            </head>
            <body style="margin:0">
              <iframe id={frameId} src="${objectUrl}" width="100%" height="100%" style="border:none;"></iframe>
            </body>
        `);
        newWindow.document.close();
      }

      newWindow.onload = () => {
        const iframe = newWindow.document.getElementById(frameId);
        if (iframe) {
          iframe.onload = () => {
            setTimeout(() => URL.revokeObjectURL(objectUrl), 10000);
          };
        } else {
          setTimeout(() => URL.revokeObjectURL(objectUrl), 10000);
        }
      };
    } catch (error) {
      console.error("Error generating PDF:", error);
    }
  };

  return (
    <BoxSpg mt={10}>
      <PageWrapper title={t("t.home.page")} helmetTitle={t("t.home.page")}>
        <FormProvider {...methods}>
          <form onSubmit={handleSubmit(onSubmit)}>
            <CardSpg my={4} style={{ overflow: "visible" }}>
              <CardContent style={{ padding: 24, position: "relative" }}>
                <BoxedHeader labelCode="t.dipolma.data.title"></BoxedHeader>
                <GridContainer>
                  <GridItem>
                    <CountrySelectField
                      countryRequired={true}
                      countryField={"country"}
                      countryLabel={"l.home.page.countryLabel"}
                      countriesThunkState={countryDataState}
                      countriesThunk={countryDataThunk}
                    ></CountrySelectField>
                  </GridItem>
                  <GridItem>
                    <YearFormField
                      required={true}
                      fieldName={"year"}
                      labelCode={"l.home.page.yearLabel"}
                      yearDropdownItemNumber={15}
                    />
                  </GridItem>
                  <GridItem>
                    <ScalaSelect />
                  </GridItem>
                </GridContainer>
                <ArrayFormControl
                  field={"educationSubject"}
                  initialValues={initialEducationSubject}
                  renderFormFields={(index, key) => {
                    return <SubjectFormFields index={index} key={key} />;
                  }}
                  addBtnLabelCode={"l.btn.home.page.add"}
                />
                <BoxSpg>
                  <Typography align={"right"}>
                    <Button type={"submit"} variant={"contained"}>
                      {t("l.btn.saveData")}
                    </Button>
                  </Typography>
                </BoxSpg>
              </CardContent>
            </CardSpg>
          </form>
        </FormProvider>
        {rows && rows.length > 0 && (
          <CardSpg my={4} style={{ overflow: "visible" }}>
            <CardContent style={{ padding: 24, position: "relative" }}>
              <NacidTableSimple headCells={headCells}>
                <TableBody>
                  {rows.map((row) => (
                    <TableRow key={row.id}>
                      <TableCell>{row.subjectName}</TableCell>
                      <TableCell sx={{ textAlign: "right" }}>{row.subjectGrade}</TableCell>
                      <TableCell sx={{ textAlign: "right" }}>{gradeFormater(row.subjectGradeBg)}</TableCell>
                      <TableCell>{row.subjectGradeBgText}</TableCell>
                    </TableRow>
                  ))}
                </TableBody>
              </NacidTableSimple>
              <CardContent style={{ paddingTop: 24, paddingRight: 0 }}>
                <BoxSpg>
                  <Typography align={"right"}>
                    <Button
                      type={"button"}
                      variant={"contained"}
                      onClick={() => {
                        handleOpenPdf(diplomaDetails);
                      }}
                    >
                      {t("l.btn.pdf")}
                    </Button>
                  </Typography>
                </BoxSpg>
              </CardContent>
            </CardContent>
          </CardSpg>
        )}
      </PageWrapper>
    </BoxSpg>
  );
};

const ScalaSelect = () => {
  const { t } = useTranslation();
  const { getValues } = useFormContext();
  const watchScalaId = useWatch({ name: "scala.id" });
  const [tooltipInfoData, setTooltipInfoData] = useState<Array<{ gradeRange: string; gradeBgEquivalence: string }>>([]);
  const { asyncCall } = useAsyncCall();

  useEffect(() => {
    setTooltipInfoData([]);
    const scalaID = getValues("scala.id" as const);
    const asyncCallArgs: AsyncCallArgs = {
      promise: getGradeScaleInfo(scalaID),
      onSuccess: (response) => {
        setTooltipInfoData(response);
      },
    };
    scalaID ? asyncCall(asyncCallArgs) : setTooltipInfoData([]);
    // eslint-disable-next-line
  }, [watchScalaId]);
  return (
    <Tooltip
      title={
        tooltipInfoData.length > 0 ? (
          <div>
            <table className="toolTipStyle">
              <style>{`
                .toolTipStyle table {
                  width: 100%;
                  border-collapse: collapse;
                }

                .toolTipStyle th {
                  padding: 3px;
                  border: 1px solid #333;
                  text-align: center;
                  font-size: 9px;
                  font-style: italic;
                  font-weight: normal;
                  color: #FFFFFF;
                  background-color: #006c48;
                }
                .toolTipStyle td {
                  padding: 3px;
                  border: 1px solid #333;
                  text-align: center;
                  font-size: 9px;
                  font-style: italic;
                  background-color: #f0f0f0;
                  }
              `}</style>
              <thead>
                <tr>
                  <th>{t("h.subject.grade.origin")}</th>
                  <th>{t("h.subject.grade.bg")}</th>
                </tr>
              </thead>
              <tbody>
                {tooltipInfoData.map((s, index) => (
                  <tr key={index}>
                    <td>{s.gradeRange}</td>
                    <td>{s.gradeBgEquivalence}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        ) : (
          ""
        )
      }
      disableHoverListener={tooltipInfoData.length === 0}
      disableFocusListener={tooltipInfoData.length === 0}
      disableTouchListener={tooltipInfoData.length === 0}
      placement="top-end"
      followCursor
      componentsProps={{
        tooltip: {
          sx: {
            backgroundColor: "#E1EFE1",
            color: "#121212",
            fontSize: "12px",
            textAlign: "justify",
          },
        },
      }}
    >
      <div>
        <DependencyAutocompleteFormField1Param
          fieldId={"scala.id"}
          labelCode={"l.home.page.scalaLabel"}
          required={true}
          disabled={false}
          onlyActive={true}
          initialValue={getValues("scala.id")}
          selectOptions={() => {
            return getGradeData(getValues("country.id"), getValues("year"));
          }}
          watchField={"country.id"}
          reloadOptionsWatchField={"year"}
        />
      </div>
    </Tooltip>
  );
};

export default GradingScales;
