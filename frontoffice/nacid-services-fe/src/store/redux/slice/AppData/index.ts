import { combineReducers } from "@reduxjs/toolkit";
import servicesDescriptions from "./servicesDescriptions";
import loggedUser from "./loggedUser";
import receiveResult from "./receiveResult";
import countriesData from "./countriesData";
import foreignIdType from "./foreignIdType";
import recognitionAim from "./recognitionAim";
import durationUnit from "./durationUnit";
import educationForm from "./educationForm";
import copyType from "./copyType";
import workdayDuration from "./workdayDuration";
import degreeRank from "./degreeRank";
import profExperienceDocType from "./profExperienceDocType";
import language from "./language";
import graduationDocType from "./graduationDocType";
import profGroup from "./profGroup";
import cfgEduLevel from "./cfgEduLevel";
import cfgGraduationWay from "./cfgGraduationWay";
import nationalUniversities from "./nationalUniversities";
import humanitarianStatus from "./humanitarianStatus";
import cfgServiceType from "./cfgServiceType";
import documentDeliveryCopyType from "./documentDeliveryCopyType";
import cfgRecognitionCategory from "./cfgRecognitionCategory";
import publicAccessInfoForm from "./publicAccessInfoForm";
import certificateReceiveForm from "./certificateReceiveForm";
import servicesPageContent from "./servicesPageContent";

const appDataReducers = combineReducers({
  ServicesDescriptions: servicesDescriptions,
  LoggedUser: loggedUser,
  ReceiveResult: receiveResult,
  CountriesData: countriesData,
  ForeignIdType: foreignIdType,
  RecognitionAim: recognitionAim,
  DurationUnit: durationUnit,
  EducationForm: educationForm,
  CopyType: copyType,
  DocumentDeliveryCopyType: documentDeliveryCopyType,
  WorkdayDuration: workdayDuration,
  DegreeRank: degreeRank,
  ProfExperienceDocType: profExperienceDocType,
  Language: language,
  GraduationDocType: graduationDocType,
  ProfGroup: profGroup,
  CfgEduLevel: cfgEduLevel,
  CfgRecognitionCategory: cfgRecognitionCategory,
  CfgGraduationWay: cfgGraduationWay,
  CfgServiceType: cfgServiceType,
  NationalUniversities: nationalUniversities,
  HumanitarianStatus: humanitarianStatus,
  PublicAccessInfoForm: publicAccessInfoForm,
  CertificateReceiveForm: certificateReceiveForm,
  ServicesPageContent: servicesPageContent,
});

export default appDataReducers;
