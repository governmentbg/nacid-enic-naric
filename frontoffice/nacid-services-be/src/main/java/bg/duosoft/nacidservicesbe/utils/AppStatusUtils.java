package bg.duosoft.nacidservicesbe.utils;

import bg.duosoft.nacidfrontofficedto.services.common.application.FoApplicationStatus;

import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Raya
 * Date: 11.01.2023
 * Time: 13:27
 */
public class AppStatusUtils {

    //TODO change to disallowed statuses and change repo method, because when statuses get done, there will be many more available.
    // Plus submitted statuses do not make sense, because there is no dossier number at that time
    public static List<String> getCheckupAllowedStatusCodes(){
        List<String> allowedCheckupStatusCodes = Arrays.asList(FoApplicationStatus.SUBMITTED.getCode(), FoApplicationStatus.SUBMITTED_WITH_SIGNATURE.getCode(), FoApplicationStatus.ACCEPTED.getCode());
        return allowedCheckupStatusCodes;
    }
}
