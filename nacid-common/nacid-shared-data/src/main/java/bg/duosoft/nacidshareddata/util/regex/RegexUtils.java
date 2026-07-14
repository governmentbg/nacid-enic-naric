package bg.duosoft.nacidshareddata.util.regex;

public class RegexUtils {

    public static final String NAME_VALIDATION_REGEX = "^[\u0400-\u04FF\\-'(). \"]+$";
    public static final String NAME_CYR_OR_LAT_VALIDATION_REGEX = "^[A-Za-z\u0400-\u04FF\\-'(). \"\\/]+$";
    public static final String EMAIL_REGEX = "^[A-Za-z0-9\\._-]+@([A-Za-z0-9][A-Za-z0-9-]*[A-Za-z0-9]\\.)+([A-Za-z]+\\.)?([A-Za-z]+)$";
    public static final String PASSWORD_REGEX = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,100}$";
    public static final String USERNAME_REGEX = "^(?=.{5,30}$)(?![_.])(?!.*[_.]{2})[a-z0-9._-]+(?<![_.])$";
    public static final String YEAR_VALIDATION_REGEX = "[12]\\d{3}";
    public static final String NUMBER_VALIDATION_REGEX = "\\d*";
    public static final String DECIMAL_NUMBER_VALIDATION_REGEX = "^\\d*\\.?\\d{0,2}$";
    public static final String DECIMAL_NUMBER_3_DIGIT_SCALE_REGEX = "^\\d*(\\.\\d{1,3})?$";
    public static final String POST_CODE_VALIDATION_REGEX = "^(([a-zA-Z0-9\\-' .\"\\/]+))$";
    public static final String STUDENT_EAN_REGEX = "[ABCDEFGHKLMNPQRSTUVWXYZ][ABCDEFGHKLMNPQRSTUVWXYZ]\\d{5}";
}
