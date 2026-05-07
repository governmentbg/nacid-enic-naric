package bg.duosoft.nacidservicesbe.validation.utils;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 20.12.2022
 * Time: 18:27
 */
public interface ValidationMessageCodes {

    String REQUIRED_CODE = "validation.field.required";
    String INVALID_CODE = "validation.field.invalid";
    String START_YEAR_BIG_CODE = "validation.start.year.bigger";
    String END_YEAR_BIGGER_THAN_DIPLOMA_YEAR_CODE = "validation.end.year.bigger.than.diploma.year";
    String START_YEAR_BIGGER_THAN_DIPLOMA_YEAR_CODE = "validation.start.year.bigger.than.diploma.year";
    String SELECT_CODE = "validation.field.select";
    String EMPTY_VALUE_IN_ARRAY_CODE = "validation.field.empty.value.in.array";
    String BAD_VALUE_IN_ARRAY_CODE = "validation.field.bad.value.in.array";
    String SELECT_FILE_CODE = "validation.attachment.file.required";
    String INVALID_FILE_CODE = "validation.attachment.file.invalid";
    String INVALID_LENGTH_MAX_50 = "validation.charCount.invalid.50";
    String REQUIRED_PREVIOUS_INQ_OR_CITATIONS_CODE = "validation.previousInquiryNum.or.CITINGS";
    String PREVIOUS_INQ_ERROR_OR_BAD_CODE = "validation.previousInquiryNum.errorOrBad";
    String PREVIOUS_INQ_INVALID_CODE = "validation.previousInquiryNum.invalid";
    String ADD_BIBLIOGRAPHIC_ENTRY = "validation.add.bibliographic.details";
    String RESULT_RECEIVE_CAN_NOT_BE_ELECTRONIC = "validation.result.receive.electronic.error";
    String UNI_NAME_INVALID_CODE = "validation.university.name.invalid";

}
