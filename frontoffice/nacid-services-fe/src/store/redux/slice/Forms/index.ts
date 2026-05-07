import { combineReducers } from "@reduxjs/toolkit";
import heRecognitionForm from "./heRecognitionForm";
import docDegreesForm from "./docDegreesForm";
import regprofForm from "./regprofForm";
import uniChecksForm from "./uniChecksForm";
import docDeliveryForm from "./docDeliveryForm";
import biblioReferenceForm from "./biblioReferenceForm";
import officialNotesForm from "./officialNotesForm";
import inquiryForm from "./inquiryForm";
import signalForm from "./signalForm";
import suggestionForm from "./suggestionForm";
import myApplicationsFilterForm from "./myApplicationsFilterForm";
import publicAccessForm from "./publicAccessForm";
import myCorrespondenceFilterForm from "./myCorrespondenceFilterForm";

const formsReducers = combineReducers({
  OfficialNotesForm: officialNotesForm,
  InquiryForm: inquiryForm,
  DocDeliveryForm: docDeliveryForm,
  BiblioReferenceForm: biblioReferenceForm,
  HERecognitionForm: heRecognitionForm,
  DocDegreesForm: docDegreesForm,
  RegprofForm: regprofForm,
  UniChecksForm: uniChecksForm,
  SuggestionForm: suggestionForm,
  SignalForm: signalForm,
  PublicAccessForm: publicAccessForm,
  MyApplicationsFilterForm: myApplicationsFilterForm,
  MyCorrespondenceFilterForm: myCorrespondenceFilterForm,
});

export default formsReducers;
